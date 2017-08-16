package ru.ilka.apartments.command;


import com.google.gson.JsonArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Here could be your advertisement +375 29 3880490
 */
public class EmptyCommand implements ActionCommand {
    @Override
    public JsonArray execute(HttpServletRequest request, HttpServletResponse response){
        System.out.println("Welcome to empty command");
        return new JsonArray();
    }
}
