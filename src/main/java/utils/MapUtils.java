package utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    // Testing java 8 mapMerge
    public static void main (String[] args){
        Map<Integer, Long> map1 = new HashMap<>();
        Map<Integer, Long> map2 = new HashMap<>();

        map1.put(2, 100L);
        map2.put(2, 100L);

        map2.forEach(
                (k, v) -> map1.merge(k, v, (left, right) -> new Long(left + right)));

        map1.forEach((k,v) -> {
            System.out.println(k + " : " + v);
        });
    }
}
