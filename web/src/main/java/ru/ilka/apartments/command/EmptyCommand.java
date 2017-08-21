package ru.ilka.apartments.command;

import com.google.gson.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class EmptyCommand implements ActionCommand {

    private static Logger logger = LoggerFactory.getLogger(EmptyCommand.class);

    @Override
    public JsonArray execute(HttpServletRequest request, HttpServletResponse response) {

        logger.warn("Welcome to empty command, from: " + request.getRequestURL());

        JsonArray array = new JsonArray();
        array.add("Ups, your URL is incorrect");
        array.add("Try this -> http://localhost:8083/Ajax/apartments");
        return array;
    }
}
