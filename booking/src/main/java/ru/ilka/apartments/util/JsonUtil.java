package ru.ilka.apartments.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import ru.ilka.apartments.entity.Apartment;

import java.util.ArrayList;

public class JsonUtil {
    public static JsonArray convertListToJson(ArrayList<Apartment> apartments) {
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(apartments);
        JsonArray jsonArray = element.getAsJsonArray();

        return jsonArray;
    }
}
