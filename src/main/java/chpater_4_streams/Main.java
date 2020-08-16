package chpater_4_streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

public class Main {
    public static void main(String[] args) {
        List<Dish> menu = asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 800, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
        menu.forEach(System.out::println);

        List<String> threeHighCaloricDishes =
                menu.stream()
                    .filter(d -> d.getCalories() > 300 )
                    .map(Dish::getName)
                    .limit(3)
                    .collect(Collectors.toList());
        threeHighCaloricDishes.forEach(System.out::println);
        String someEmail = "ceva , ceva2, altceva, etc";
        List<String> emailList = asList(someEmail.split(", ?"));

        Dish pork = new Dish("pork", false, 800, Dish.Type.MEAT);

        //Filtering with a predicate
        System.out.println("Filtering using predicates");
        menu.stream().filter(Dish::isVegetarian).forEach(System.out::println);

         // getting mapping of elemetns from 2 arrays
        List<Integer> numbers1 = asList(1,2,3);
        List<Integer> numbers2 = asList(3,4);

        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream().map(j -> new int[] {i,j}))
                        .filter(pair -> stream(pair).sum() % 3 == 0)
                        .collect(Collectors.toList());

        System.out.println("pair size: " + pairs.size());

    }

}
