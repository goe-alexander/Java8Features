package chapter_3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*
* ANY INTERFACE WHICH HAS ONLY ONE ABSTRACT METHOD DEFINED IS A FUNCTIONAL INTERFACE
* Most common examples include: Comparator and Runnable
* */
public class LambdaAndFunctionalInterfaceExample {
    public static String processFile(FunctionalInterfaceBufferedReader fnBr) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return fnBr.process(br);
        }
    }


    //        Using the functional interface to FUNCTION to apply methods on collections
    public static <T, R> List<R> mapMyCollections(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        list.forEach(elem -> result.add(f.apply(elem)));
        return result;
    }

    public static void main(String[] args){
        Runnable r = () -> System.out.println("HelloWorld from inside a lambda Runnable");
        r.run();

        // Using our own Functional Interface
        try {
            String oneLine = processFile((BufferedReader br) -> br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Using the FUNCTION functional interface to apply methods on a collection
        List<Integer> lengths = mapMyCollections(Arrays.asList("Lamdas", "are", "the", "shit"), (String s) -> s.length());
        lengths.forEach(System.out::println);
    }
}
