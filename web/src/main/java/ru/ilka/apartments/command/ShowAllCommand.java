package ru.ilka.apartments.command;

import com.google.gson.JsonArray;
import org.springframework.stereotype.Component;
import ru.ilka.apartments.exception.CommandException;
import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.exception.LogicException;
import ru.ilka.apartments.logic.ApartmentLogic;
import ru.ilka.apartments.util.ContextHolder;
import ru.ilka.apartments.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


@Component
public class ShowAllCommand implements ActionCommand {

    @Override
    public JsonArray execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        ApartmentLogic apartmentLogic = ContextHolder.getApplicationContext().getBean(ApartmentLogic.class);

        ArrayList<Apartment> apartments = null;
        try {
            apartments = apartmentLogic.loadAll();
        } catch (LogicException e) {
            throw new CommandException("Can not load all", e);
        }

        return JsonUtil.convertListToJson(apartments);
    }
}
