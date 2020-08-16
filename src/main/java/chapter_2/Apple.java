package chapter_2;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class Apple {
    private String type;
    private Integer weight;
    private String color;

    @Override
    public String toString(){
        return "A " + this.getColor() + " apple, " + " of " + this.getWeight() + " g";
    }
}
