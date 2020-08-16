package chapter_5_working_with_streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.*;
import static java.util.Arrays.asList;


public class Main {
    public static void main(String[] args) {
        List<Dish> menu = asList(
                new Dish("pork", false, true,800, Dish.Type.MEAT),
                new Dish("beef", false, true,700, Dish.Type.MEAT),
                new Dish("chicken", false, true,400, Dish.Type.MEAT),
                new Dish("french fries", true, false, 530, Dish.Type.OTHER),
                new Dish("rice", true, false, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, false, 120, Dish.Type.OTHER),
                new Dish("pizza", true, false, 550, Dish.Type.OTHER),
                new Dish("prawns", false, true, 300, Dish.Type.FISH),
                new Dish("salmon", false, true, 450, Dish.Type.FISH) );
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

        Dish pork = new Dish("pork", false, true, 800, Dish.Type.MEAT);

        //Filtering with a predicate
        System.out.println("Filtering using predicates");
        menu.stream().filter(Dish::isVegetarian).forEach(System.out::println);

        // Filtering elements:
        // Finding any that match
        System.out.println("finding any vegetarian dish, but will also return the first one found: ");
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(System.out::println);

        // Finding the first element
        // Difference between find first and find any is when using paralllelism:
        // If which element is returned is of no concern, then use findAny because it's less constraining
        // when using parallel streams
        System.out.println("Getting first meat based: ");
        menu.stream()
                .filter(Dish::isMeatBased)
                .findFirst()
                .ifPresent(System.out::println);

        // REDUCING streams
        int sumOfAllCalories =
        menu.stream()
                .map(Dish::getCalories)
                .reduce(0, (a,b) -> a + b);
        System.out.println("Sum of ALL calories in the menu: " + sumOfAllCalories);

        int noOfDishesUsingMapAndReduce =
                menu.stream().map(d -> 1).reduce(0, (a,b) -> a+b );
        System.out.println("Counting no of elements in a stream using reduce: " + noOfDishesUsingMapAndReduce);

        // Testing Pythagorean triples
        Stream<int[]> pythagoreanTriple =
            IntStream.rangeClosed(1, 100).boxed()
                    .flatMap(a ->
                            IntStream.rangeClosed(a,100)
                            .filter(b -> sqrt(a*a + b*b) % 1 == 0)
                            .mapToObj(b -> new int [] {a,b, (int)sqrt(a*a + b*b)})
                            );
        pythagoreanTriple.limit(5).forEach(t-> System.out.println(t[0] + "," + t[1] + "," + t[2]));

        //Generating Strreams from multiple sources:
        //1. From values
        System.out.println("OUTPUTING VALUES from STREAM made from VALUES");
        Stream<String> stringStream = Stream.of("Java 8", "Lambdas", "Examples");
        stringStream.forEach(System.out::println);

        //2. From arrays:
        System.out.println("Stream from arrays");
        int [] numberInts = {1,2,3,4,5,6,6,8};
        int sum = Arrays.stream(numberInts).sum();

        //3. From functions
        Stream.iterate(0, n -> n+2)
                .limit(10)
                .forEach(System.out::println);

        // Fibonnaci series using Streams
        Stream.iterate(new int[] {0,1}, t -> new int[] {t[1], t[1] + t[0]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        // Generating Streams:
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        //
    }

}
