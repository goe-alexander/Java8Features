package chapter_3;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface FunctionalInterfaceBufferedReader {
    String process(BufferedReader br) throws IOException;
}
