package ru.ilka.apartments.command;

import com.google.gson.JsonArray;
import org.springframework.stereotype.Component;
import ru.ilka.apartments.exception.CommandException;
import ru.ilka.apartments.exception.LogicException;
import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.logic.ApartmentLogic;
import ru.ilka.apartments.util.ContextHolder;
import ru.ilka.apartments.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Component
public class BookApartmentCommand implements ActionCommand {

    private static final String PARAM_APARTMENT_ID = "apartmentId";
    private static final String PARAM_DURATION = "duration";

    @Override
    public JsonArray execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        ApartmentLogic apartmentLogic = ContextHolder.getApplicationContext().getBean(ApartmentLogic.class);

        try {
            apartmentLogic.bookApartment(Integer.parseInt(request.getParameter(PARAM_APARTMENT_ID)), Integer.parseInt(request.getParameter(PARAM_DURATION)));
        } catch (LogicException e) {
            throw new CommandException("Can not book apartment by id", e);
        }
        ArrayList<Apartment> apartments = null;
        try {
            apartments = apartmentLogic.loadAll();
        } catch (LogicException e) {
            throw new CommandException("Can not load all ", e);
        }

        return JsonUtil.convertListToJson(apartments);
    }
}
