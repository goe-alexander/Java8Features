package chapter_6_collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class NewDish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public enum Type { MEAT, FISH, OTHER }

    @Override
    public String toString(){
        return name + " : " + calories + " calories " + type.name() + (vegetarian ? " | vegeratrian" : "");
    }
}
