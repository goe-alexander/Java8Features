package chapter_2;

import java.util.List;

public class PrettyPrint {
    public static void prettyPrintApple(List<Apple> apples, AppleFormatter appleFormatter){
        for(Apple a : apples){
            appleFormatter.accept(a);
        }
    }
}
