package ru.ilka.apartments.command;

import com.google.gson.JsonArray;
import ru.ilka.apartments.exception.CommandException;
import ru.ilka.apartments.exception.LogicException;
import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.logic.ApartmentLogic;
import ru.ilka.apartments.util.ContextHolder;
import ru.ilka.apartments.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Here could be your advertisement.
 * Ilya_Kisel +375 29 3880490
 */
public class ShowByIdCommand implements ActionCommand {

    private static final String PARAM_APARTMENT_ID = "apartmentId";

    @Override
    public JsonArray execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        ApartmentLogic apartmentLogic = ContextHolder.getApplicationContext().getBean(ApartmentLogic.class);

        ArrayList<Apartment> apartments = new ArrayList<>();
        Apartment apartment = null;
        try {
            apartment = apartmentLogic.loadById(Integer.parseInt(request.getParameter(PARAM_APARTMENT_ID)));
        } catch (LogicException e) {
            throw new CommandException("Can not delete apartment by id",e);
        }
        apartments.add(apartment);

        return JsonUtil.convertListToJson(apartments);
    }
}