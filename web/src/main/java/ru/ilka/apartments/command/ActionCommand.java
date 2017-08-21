package ru.ilka.apartments.command;

import com.google.gson.JsonArray;
import ru.ilka.apartments.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionCommand {
    JsonArray execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException;
}
