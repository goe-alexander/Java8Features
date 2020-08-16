package chapter_6_collectors;

import chpater_5_5_stream_exercises.Trader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;
    private final String currency;

    @Override
    public String toString() {
        return "{" + this.trader + ", " +
                "year: "+this.year+", " +
                "value:" + this.value +"}";
    }
}
