package ru.ilka.apartments.command;

import com.google.gson.JsonArray;
import ru.ilka.apartments.exception.CommandException;
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
public class AddApartmentCommand implements ActionCommand {

    @Override
    public JsonArray execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        ApartmentLogic apartmentLogic = ContextHolder.getApplicationContext().getBean(ApartmentLogic.class);

        apartmentLogic.addApartment();
        ArrayList<Apartment> apartments = apartmentLogic.loadAll();

        return JsonUtil.convertListToJson(apartments);
    }
}
