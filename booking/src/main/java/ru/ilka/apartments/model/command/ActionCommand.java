package ru.ilka.apartments.model.command;

import com.google.gson.JsonArray;
import ru.ilka.apartments.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Here could be your advertisement +375 29 3880490
 */
public interface ActionCommand {
    String COMMAND_ATTR = "command";

    JsonArray execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException;
}
