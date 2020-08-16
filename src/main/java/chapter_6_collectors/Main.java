package chapter_6_collectors;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        List<NewDish> menu = asList(
                new NewDish("pork", false, 800, NewDish.Type.MEAT),
                new NewDish("beef", false, 800, NewDish.Type.MEAT),
                new NewDish("chicken", false, 400, NewDish.Type.MEAT),
                new NewDish("french fries", true, 530, NewDish.Type.OTHER),
                new NewDish("rice", true, 350, NewDish.Type.OTHER),
                new NewDish("season fruit", true, 120, NewDish.Type.OTHER),
                new NewDish("pizza", true, 550, NewDish.Type.OTHER),
                new NewDish("prawns", false, 300, NewDish.Type.FISH),
                new NewDish("salmon", false, 450, NewDish.Type.FISH),
                new NewDish("Cod", false, 550, NewDish.Type.FISH));

        System.out.println("Number of Dishes: " + menu.stream().collect(counting()));
        System.out.println("Total calories in menu: " + menu.stream().collect(summingInt(NewDish::getCalories)));
        System.out.println("Average calories in menu: " + menu.stream().collect(averagingInt(NewDish::getCalories)));

        // Max and Min collectors
        Comparator<NewDish> caloriesComparator = Comparator.comparingInt(NewDish::getCalories);
        Optional<NewDish> mostCaloriesDish = menu.stream().collect(maxBy(caloriesComparator));
        mostCaloriesDish.ifPresent(System.out::println);

        // avg collectors
        double avgCalories = menu.stream().collect(averagingInt(NewDish::getCalories));
        System.out.println("Average Calories: " + avgCalories);

        // IntSummaryStatistics
        IntSummaryStatistics menuStats = (IntSummaryStatistics) menu.stream().collect(summarizingInt(NewDish::getCalories));
        System.out.println(menuStats.toString());

        // Joining Strings
        String shortMenu = menu.stream().map(NewDish::getName).collect(joining(", "));
        System.out.println("Short menu: " + shortMenu);

        // General reducing operation
        int totalCaloriesReduced = menu.stream().collect(reducing(0, NewDish::getCalories, (i, j) -> i + j));
        System.out.println("General Reducing total calories " + totalCaloriesReduced);


        // Grouping by type:
        Map<NewDish.Type, List<NewDish>> dishedByType = menu.stream().collect(groupingBy(NewDish::getType));
        dishedByType.entrySet().forEach(entry -> System.out.println("Type :" + entry.getKey() + " -> Count: " + entry.getValue().size()));

        // Multilevel grouping
        System.out.println("\n Dishes by Tpe and Caloric Profile: ");
        Map<NewDish.Type, Map<CaloricLevel, List<NewDish>>> dishesByTypeAndCaloricLevel =
                menu.stream().collect(
                        groupingBy(NewDish::getType,
                                groupingBy(dish -> {
                                    if (dish.getCalories() <= 200) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 300) return CaloricLevel.NORMAL;
                                    else if (dish.getCalories() <= 300) return CaloricLevel.NORMAL;
                                    else if (dish.getCalories() <= 500) return CaloricLevel.FAT;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.FAT_AS_FUCK;
                                    else return CaloricLevel.BIG_CHUNGUS;
                                })
                        ));
        dishesByTypeAndCaloricLevel.entrySet().forEach(System.out::println);

        // Passing generic colectors to grouping colectors
        Map<NewDish.Type, Optional<NewDish>> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(NewDish::getType,
                                maxBy(Comparator.comparingInt(NewDish::getCalories))));

        System.out.println("\n Dishes by Type Using with Most calories: ");
        mostCaloricByType.entrySet().forEach(System.out::println);

        //Adapting the colector result to return a different type:
        Map<NewDish.Type, NewDish> mostCalByType =
                menu.stream()
                        .collect(groupingBy(NewDish::getType,
                                collectingAndThen(maxBy(Comparator.comparingInt(NewDish::getCalories)), Optional::get)
                        ));
        System.out.println("\n Call by type using collecting and then ");
        mostCalByType.entrySet().forEach(System.out::println);

        // Mapping collectors used on groupign ones
        Map<NewDish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(groupingBy(NewDish::getType, mapping(dish -> {
                    if (dish.getCalories() <= 200) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 300) return CaloricLevel.NORMAL;
                    else if (dish.getCalories() <= 300) return CaloricLevel.NORMAL;
                    else if (dish.getCalories() <= 500) return CaloricLevel.FAT;
                    else if (dish.getCalories() <= 700) return CaloricLevel.FAT_AS_FUCK;
                    else return CaloricLevel.BIG_CHUNGUS;
                }, toSet())));
        System.out.println("\n Call by type using collecting and then ");
        caloricLevelsByType.entrySet().forEach(System.out::println);


        // Partitioning streams:
        Map<Boolean, List<NewDish>> vegetarianSplitMenu =
                menu.stream().collect(partitioningBy(NewDish::isVegetarian));
        System.out.println("\n Partitionned collector, is vegetarian: ");
        vegetarianSplitMenu.entrySet().forEach(System.out::println);

        // Partition vegetarian dishes by type
        Map<Boolean, Map<NewDish.Type, List<NewDish>>> partVegTypeDish =
                menu.stream().collect(partitioningBy(NewDish::isVegetarian, groupingBy(NewDish::getType, toList())));
        System.out.println("\n Partitionned collector, is vegetarian by type: ");
        partVegTypeDish.entrySet().forEach(System.out::println);

        // most caloric among vegeterian and non
        System.out.println("\n Most caloric dish partitioned by vegetarian: ");
        Map<Boolean, NewDish> mostCaloricPartByVeg =
            menu
                .stream()
                .collect(partitioningBy(NewDish::isVegetarian, collectingAndThen(maxBy(Comparator.comparingInt(NewDish::getCalories)), Optional::get)));
        mostCaloricPartByVeg.entrySet().forEach(System.out::println);

        System.out.println("Partitioned prime number to 100");
        pantritionPrimes(100).entrySet().forEach(System.out::println);
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double)candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> pantritionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                        .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

}
