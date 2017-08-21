package ru.ilka.apartments.command;

import com.google.gson.JsonArray;
import org.springframework.stereotype.Component;
import ru.ilka.apartments.exception.CommandException;
import ru.ilka.apartments.exception.LogicException;
import ru.ilka.apartments.logic.ApartmentLogic;
import ru.ilka.apartments.util.ContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DeleteAllCommand implements ActionCommand {

    @Override
    public JsonArray execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            ContextHolder.getApplicationContext().getBean(ApartmentLogic.class).deleteAll();
        } catch (LogicException e) {
            throw new CommandException("Can not delete all", e);
        }
        return new JsonArray();
    }
}
