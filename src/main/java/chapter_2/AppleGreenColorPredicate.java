package chapter_2;

public class AppleGreenColorPredicate implements ApplePredicateInterface {
    @Override
    public boolean test(Apple apple) {
        return "green".equalsIgnoreCase(apple.getColor());
    }
}
