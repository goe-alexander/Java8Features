package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonDeserializer {


    public static void main(String[] args) {
        Path jsonPath = Paths.get("C:\\Users\\alexandru.neagoe\\Git\\Java8Features\\src\\main\\java\\utils\\sourceJson.json");
        try {
            String jsonContent = new String(Files.readAllBytes(jsonPath));

            DataFromJson[] jsonArrayData;
            DataFromJson[] allFriends = new DataFromJson[0];
            Gson gson = new Gson();

            //getting only what we need, the friends
            JsonElement jsonElement = new JsonParser().parse(jsonContent);
            // !!! ATENTIE ca aici tyrebuie sa adaptezi in functie de ce este structura principala daca este array faci asa altfel tre sa iei din jsonElement un getAsObject
            JsonArray initialArray = jsonElement.getAsJsonArray();
            for (JsonElement element : initialArray) {
                JsonObject obj = element.getAsJsonObject();
                JsonArray friendArray = obj.getAsJsonArray("friends");

                //System.out.println(friendArray.toString());

                jsonArrayData = gson.fromJson(friendArray.toString(), DataFromJson[].class);
                // Add the each array to a resulting array
                allFriends = Stream.concat(Arrays.stream(allFriends), Arrays.stream(jsonArrayData)).toArray(DataFromJson[]::new);
                Arrays.asList(jsonArrayData).forEach(friend -> {
                    System.out.println(friend.id + " : " + friend.name);
                });
            }

            System.out.println("total NUmber of friends: " + allFriends.length );
            Arrays.asList(allFriends).forEach(element ->{
                System.out.println(element.id + " | " +element.name);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
