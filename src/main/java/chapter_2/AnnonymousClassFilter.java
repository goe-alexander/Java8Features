package chapter_2;

import java.util.ArrayList;
import java.util.List;

public class AnnonymousClassFilter {

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate){
        List<Apple> result = new ArrayList<Apple>();
        for(Apple a : inventory){
            if(predicate.test(a)){
                result.add(a);
            }

        }
        return result;
    }

    public static void main(String args[]){
        List<Apple> inventory = new ArrayList<>();
        inventory.add(Apple.builder().color("green").weight(150).build());
        inventory.add(Apple.builder().color("red").weight(25).build());
        inventory.add(Apple.builder().color("red").weight(150).build());
        // Annonymous class, old way:
        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equalsIgnoreCase(apple.getColor());
            }
        });

        // Annonymous function, LAMBDA, new way:
        // Inteface is not specified yet.
        //List<Apple> lambdaRedApples = filterApples(inventory, (Apple a) -> "red".equalsIgnoreCase(a.getColor()));
        redApples.forEach(System.out::println);
    }
}
