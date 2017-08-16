package ru.ilka.apartments.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import ru.ilka.apartments.entity.Apartment;

import java.util.ArrayList;

/**
 * Here could be your advertisement.
 * Ilya_Kisel +375 29 3880490
 */
public class JsonUtil {
    public static JsonArray convertListToJson(ArrayList<Apartment> apartments){
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(apartments);
        JsonArray jsonArray = element.getAsJsonArray();

        return jsonArray;
    }
}
