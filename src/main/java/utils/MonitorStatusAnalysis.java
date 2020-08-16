package utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MonitorStatusAnalysis {

    private static final String REV_PROXY_1 = "139.15.251.91";
    private static final String REV_PROXY_2 = "139.15.251.92";
    private static final String REV_PROXY_3 = "139.15.250.29";

    public static void main(String[] args) {
        System.out.println(Objects.hash(null, null));
        Path loadBalancerContet = Paths.get("C:\\Users\\alexandru.neagoe\\Git\\Java8Features\\src\\main\\java\\utils\\loadBalancer.txt");
        try {
            List<MonitorStatus> proxy_1 = new ArrayList<>();
            List<MonitorStatus> proxy_2 = new ArrayList<>();
            List<MonitorStatus> proxy_3 = new ArrayList<>();

            String loadBalancerLogs = new String(Files.readAllBytes(loadBalancerContet));
            String[] entries = loadBalancerLogs.split("\\r?\\n");
            List<String> entryList = Arrays.asList(entries);
            List<String> statusDown =
                    entryList
                            .stream()
                            .filter(entry -> entry.contains("monitor status down"))
                            .collect(Collectors.toList());
            List<String> statusUp =
                    entryList
                            .stream()
                            .filter(entry -> entry.contains("monitor status up"))
                            .collect(Collectors.toList());

            // get a list of how many seconds was the monitor down for
            List<MonitorStatus> downMonitor = addDurationAndTimestamp(statusUp, "DOWN");
            // get List of how many seconds the monitor was up
            List<MonitorStatus> upMonitor = addDurationAndTimestamp(statusDown, "UP");
            System.out.println("initial Down sum: " + downMonitor.stream().mapToInt(MonitorStatus::getDuration).sum());

            proxy_1.addAll(extractProxyList(downMonitor, REV_PROXY_1));
            proxy_1.addAll(extractProxyList(upMonitor, REV_PROXY_1));
            proxy_2.addAll(extractProxyList(downMonitor, REV_PROXY_2));
            proxy_2.addAll(extractProxyList(upMonitor, REV_PROXY_2));
            proxy_3.addAll(extractProxyList(downMonitor, REV_PROXY_3));
            proxy_3.addAll(extractProxyList(upMonitor, REV_PROXY_3));

            System.out.println("Proxy 1 up Time " + REV_PROXY_1 + " : " + proxy_1.stream()
                                                                .filter(entry -> entry.getStatus().equalsIgnoreCase("UP"))
                                                                .map(MonitorStatus::getDuration)
                                                                .reduce(0, Integer::sum));
            System.out.println("Proxy 1 down Time " + REV_PROXY_1 + " : " + proxy_1.stream()
                                                                .filter(entry -> entry.getStatus().equalsIgnoreCase("Down"))
                                                                .map(MonitorStatus::getDuration)
                                                                .reduce(0, Integer::sum));
            System.out.println("Proxy 2 up Time " + REV_PROXY_2 + " : " + proxy_2.stream()
                                                                            .filter(entry -> entry.getStatus().equalsIgnoreCase("UP"))
                                                                            .map(MonitorStatus::getDuration)
                                                                            .reduce(0, Integer::sum));
            System.out.println("Proxy 2 down Time " + REV_PROXY_2 + " : " + proxy_2.stream()
                                                                            .filter(entry -> entry.getStatus().equalsIgnoreCase("DOWN"))
                                                                            .map(MonitorStatus::getDuration)
                                                                            .reduce(0, Integer::sum));
            System.out.println("Proxy 3 up Time " + REV_PROXY_3 + " : " + proxy_3.stream()
                                                                            .filter(entry -> entry.getStatus().equalsIgnoreCase("UP"))
                                                                            .map(MonitorStatus::getDuration)
                                                                            .reduce(0, Integer::sum));
            System.out.println("Proxy 3 down Time " + REV_PROXY_3 + " : " + proxy_3.stream()
                                                                            .filter(entry -> entry.getStatus().equalsIgnoreCase("DOWN"))
                                                                            .map(MonitorStatus::getDuration)
                                                                            .reduce(0, Integer::sum));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<MonitorStatus> extractProxyList(List<MonitorStatus> inputList, String ipProxy){
        return inputList
                .stream()
                .filter(monitorStatus -> monitorStatus.getIp().equalsIgnoreCase(ipProxy))
                .collect(Collectors.toList());
    }

    private static List<MonitorStatus> addDurationAndTimestamp(List<String> input, String status) {
        List<MonitorStatus> monitorStatuses = new ArrayList<>();
        Pattern pTimestamp = Pattern.compile("(\\d{2}:\\d{2}:\\d{2})");
        Pattern pIP = Pattern.compile("(\\d{3}.\\d{2}.\\d{3}.\\d{2})");
        Pattern pTimeSec = Pattern.compile("(\\d+sec)");
        Pattern pTimeMin = Pattern.compile("(\\d+min)");
        Pattern pTimeHour = Pattern.compile("(\\d+hr)");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        input.forEach(entry -> {
            Matcher timestamp = pTimestamp.matcher(entry);
            Matcher ip = pIP.matcher(entry);
            Matcher timeSec = pTimeSec.matcher(entry);
            Matcher timeMin = pTimeMin.matcher(entry);
            Matcher timeHour = pTimeHour.matcher(entry);

            if (timestamp.find() && ip.find() && timeSec.find() && timeMin.find() && timeHour.find()) {
                String secString = timeSec.group(0);
                String minString = timeMin.group(0);
                String hourString = timeHour.group(0);
                String IP = ip.group(0);


                Integer timeSecValue = Integer.valueOf(secString.substring(0, secString.indexOf("sec")));
                Integer timeMinValue = Integer.valueOf(minString.substring(0, minString.indexOf("min"))) * 60;
                Integer timeHourValue = Integer.valueOf(hourString.substring(0, hourString.indexOf("hr"))) * 3600;
                Integer totalTime = timeSecValue + timeMinValue + timeHourValue;

                if(totalTime > 10000){
                    System.out.println("Outlier found: " + entry);
                }

                try {
                    MonitorStatus ms =
                            MonitorStatus
                                    .builder()
                                    .timestamp(timeFormat.parse(timestamp.group(0)))
                                    .ip(IP)
                                    .duration(totalTime)
                                    .status(status)
                                    .build();

                    monitorStatuses.add(ms);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        return monitorStatuses;
    }
}
