package chapter_2;

public class AppleFancyFormatter implements AppleFormatter{
    @Override
    public String accept(Apple a) {
        String characteristics = a.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristics + " " + a.getColor() + " apple" ;
    }
}
