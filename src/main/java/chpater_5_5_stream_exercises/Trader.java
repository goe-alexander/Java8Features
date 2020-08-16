package chpater_5_5_stream_exercises;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString(){
        return "Trader " + this.name + " in " + this.city;
    }
}
