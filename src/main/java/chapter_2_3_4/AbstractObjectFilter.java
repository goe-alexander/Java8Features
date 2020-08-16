package chapter_2_3_4;

import chapter_2.Apple;

import java.util.ArrayList;
import java.util.List;

public class AbstractObjectFilter {
    public static <T> List<T> filterList(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        list.forEach(e -> {
            if(p.test(e)) {
                result.add(e);
            }
        });
        return result;
    }

    public static void main(String[] args){
        List<Apple> inventory = new ArrayList<>();
        inventory.add(Apple.builder().color("green").weight(150).build());
        inventory.add(Apple.builder().color("red").weight(125).build());
        inventory.add(Apple.builder().color("Red").weight(155).build());

        List<Apple> redApples = filterList(inventory, (Apple a) -> "red".equalsIgnoreCase(a.getColor()));
        redApples.forEach(System.out::println);

        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(6);
        numbers.add(3);
        numbers.add(11);

        List<Integer> evenNumbers = filterList(numbers, (Integer i) -> i % 2 == 0);
        evenNumbers.forEach(System.out::println);
    }
}
