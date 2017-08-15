package ru.ilka.apartments.model.command;

import com.google.gson.JsonArray;
import ru.ilka.apartments.exception.CommandException;
import ru.ilka.apartments.model.logic.ApartmentLogic;
import ru.ilka.apartments.model.util.ContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Here could be your advertisement.
 * Ilya_Kisel +375 29 3880490
 */
public class DeleteAllCommand implements ActionCommand {

    @Override
    public JsonArray execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        ContextHolder.getApplicationContext().getBean(ApartmentLogic.class).deleteAll();
        return new JsonArray();
    }
}
