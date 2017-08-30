package ru.ilka.apartments.util;

import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.exception.UtilException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonUtil {

    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static JsonArray convertListToJson(ArrayList<Apartment> apartments) {
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(apartments);
        JsonArray jsonArray = element.getAsJsonArray();

        return jsonArray;
    }

    public static String readApartmentData(BufferedReader reader) throws UtilException {
        StringBuffer jsonData = new StringBuffer();
        String jsonLine;
        try {
            while ((jsonLine = reader.readLine()) != null) {
                jsonData.append(jsonLine);
            }
        } catch (IOException e) {
            throw new UtilException("Can not read json data", e);
        }
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonData.toString()).getAsJsonObject();

        JsonObject jsonApartment = jsonObject.getAsJsonObject("apartment");
        JsonElement jsonCost = jsonApartment.getAsJsonPrimitive("cost");

        logger.info("json data from request body:");
        logger.info("jsonApartment " + jsonApartment);
        logger.info("jsonCost " + jsonCost);

        return jsonCost.toString();
    }
}
