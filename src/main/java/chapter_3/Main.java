package chapter_3;

import chapter_2.Apple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleFunction;
import java.util.stream.Collectors;

public class Main {
    class first {

        public void test() throws IOException, ArrayIndexOutOfBoundsException {

        }
    }

    class second extends first {
        public void test() throws IOException {
            throw new IOException("second");
        }
    }

    class third extends second {
        public void test() throws IOException {

        }
    }

    public static double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b-a) / 2.0;
    }

    public static void main(String[] args) {
        int a = -2;
        int b = 4;
        DoubleFunction<Double> doubleFunc = x -> x + 10;
        //calling integral function to calculate what is the area below the graph:
        System.out.println("Integral equation: ");
        System.out.println(integrate(doubleFunc, 3, 7));

        System.out.println("substraction: " + (b -a) );
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
        Apple greenApple = Apple.builder()
                .color("green")
                .type("ionatan")
                .weight(200)
                .build();
        Apple redApple = Apple.builder()
                .color("red")
                .type("diana")
                .weight(400)
                .build();
        System.out.println(c.equals(redApple));
        List<Apple> inventory = new ArrayList<>();
        inventory.add(Apple.builder().color("green").weight(150).type("ionatan").build());
        inventory.add(Apple.builder().color("red").weight(25).type("vara").build());
        inventory.add(Apple.builder().color("red").weight(150).type("toamna").build());
        System.out.println("Initial List: ");
        inventory.forEach(apple -> {
            System.out.println(apple.getType());
        });
        inventory.sort(Comparator
                .comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));
        System.out.println("After sorting List: ");
        inventory.forEach(apple -> {
            System.out.println(apple.getType());
        });

        inventory.sort(Comparator.comparing(Apple::getWeight));
        inventory.forEach(apple -> System.out.println(apple.getWeight()));
//Processing file example
/*        try {
            List<String> foundLines = new ArrayList<String>();
            Files.list(Paths.get("C:\\Users\\alexandru.neagoe\\Desktop\\Work\\Ebike\\Tasks\\OB-7637\\4DaysLogs\\access_logs_30.05.2019_whole_week")).
                    forEach(file -> {
                        System.out.println("Found file: " + file.getFileName());
                        processFile(file.toAbsolutePath(), foundLines);
                    });
            System.out.println("Found no Of lines: " + foundLines.size());
            Files.write(Paths.get("C:\\Users\\alexandru.neagoe\\Git\\Java8Features\\src\\main\\java\\chapter_3\\resultAccessLog.txt"), foundLines);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    public static void processFile(Path currentFile, List<String> foundLines) {
        try {

            Files.lines(currentFile).forEach(line -> {
                //System.out.println("## " + line);
                if (line.contains("User not authenticated")) {
                    foundLines.add(line);
                }
            });
        } catch (Exception e) {

        }
    }

}
