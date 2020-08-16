package chpater_5_5_stream_exercises;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Main {
    public static void main(String[] args) {
        // Defining traders
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Trader> allTraders = Arrays.asList(raoul, mario, alan, brian);
        // Defining transactions
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1. Find all transactions in the year 2011 and sort them by value (small to high)
        System.out.println("1. Find all transactions in the year 2011 and sort them by value (small to high)");
        List<Transaction> _2011Trans =
                transactions
                        .stream()
                        .filter(t -> t.getYear() == 2011)
                        .sorted(comparing(Transaction::getValue))
                        .collect(Collectors.toList());
        _2011Trans.forEach(System.out::println);

        // 2. What are all the unique cities where traders work
        System.out.println("2. What are all the unique cities where traders work");
        List<String> uniqueCities = allTraders
                                            .stream()
                                            .map(Trader::getCity)
                                            .distinct()
                                            .collect(Collectors.toList());
        uniqueCities.forEach(System.out::println);

        //3. Find all traders from Cambridge and sort them by name:
        System.out.println("3. Find all traders from Cambridge and sort them by name:");
        allTraders
                .stream()
                .filter(t -> t.getCity() == "Cambridge")
                .sorted(comparing(Trader::getName))
                .forEach(System.out::println);

        // 4. Return a string of all traders' names sorted alphabetically.
        System.out.println("4. Return a string of all traders' names sorted alphabetically.");
            allTraders
                    .stream()
                    .sorted(comparing(Trader::getName))
                    .map(Trader::getName)
                    .reduce((a,b) -> a + b)
                    .ifPresent(System.out::println);

        //5. Are any traders based in Milan ?
        System.out.println("5. Are any traders based in Milan: ");
        allTraders
                .stream()
                .filter(t -> t.getCity() == "Milan")
                .findAny()
                .ifPresent(t -> System.out.println("yes there are"));

        //6. Print all transactions' values from the traders living in Cambridge
        System.out.println("6. Print all transactions' values from the traders living in Cambridge");
        transactions
                .stream()
                .filter(tr -> tr.getTrader().getCity() == "Cambridge")
                .forEach(System.out::println);
    }
}
