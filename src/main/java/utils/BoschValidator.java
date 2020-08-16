package utils;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class BoschValidator {
    private static JsonFactory jsonFactory = new JsonFactory();
    public static void main(String[] args){
        Path jsonPath = Paths.get("C:\\Users\\alexandru.neagoe\\Git\\Java8Features\\src\\main\\java\\utils\\NyonActivities.json");
        try {
            String jsonContent = new String(Files.readAllBytes(jsonPath));
            System.out.println("Is Json valid: " + isValid(jsonContent));
//            System.out.println(jsonContent);
            System.out.println(Objects.hash(null, null));
            Objects.equals(null, null);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static boolean isValid(String json) {
        if (json.isEmpty()) {
            return false;
        }

        try (JsonParser parser = jsonFactory.createParser(json)) {
            while (!parser.isClosed()) {
                parser.nextToken();
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
