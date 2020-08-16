package chapter_2;

public class AppleHeavyWeightPredicate implements ApplePredicateInterface {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
