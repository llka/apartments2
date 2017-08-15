package ru.ilka.apartments.model.logic;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilka.apartments.exception.LogicException;
import ru.ilka.apartments.model.dao.ApartmentDaoImpl;
import ru.ilka.apartments.model.entity.Apartment;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Here could be your advertisement.
 * Ilya_Kisel +375 29 3880490
 */
@Service
public class ApartmentLogic {

    @Autowired
    private ApartmentDaoImpl apartmentDao;

    public ApartmentLogic() {

    }

    public ArrayList<Apartment> loadAll(){
        apartmentDao.openSessionWithTransaction();
        ArrayList<Apartment> apartments = apartmentDao.loadAll();
        apartmentDao.closeSessionWithTransaction();
        return apartments;
    }

    public Apartment loadById(int apartmentId) throws LogicException {
        if(apartmentId <= 0){
            throw new LogicException("Apartment Id can not be less then 1");
        }
        apartmentDao.openSessionWithTransaction();
        Apartment apartment = apartmentDao.loadById(apartmentId);
        apartmentDao.closeSessionWithTransaction();
        return apartment;
    }

    public void update(Apartment apartment){
        apartmentDao.openSessionWithTransaction();
        apartmentDao.update(apartment);
        apartmentDao.closeSessionWithTransaction();
    }

    public void save(Apartment apartment){
        apartmentDao.openSessionWithTransaction();
        apartmentDao.save(apartment);
        apartmentDao.closeSessionWithTransaction();
    }

    public void delete(Apartment apartment){
        apartmentDao.openSessionWithTransaction();
        apartmentDao.delete(apartment);
        apartmentDao.closeSessionWithTransaction();
    }

    public void deleteAll(){
        apartmentDao.openSessionWithTransaction();
        apartmentDao.deleteAll();
        apartmentDao.closeSessionWithTransaction();
    }

    public void addApartment(){
        Apartment apartment = new Apartment();
        apartment.setBookedFrom(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
        apartment.setBookedTo(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
        save(apartment);
    }

    public void bookApartment(int apartmentId, int duration) throws LogicException {
        Apartment apartment = loadById(apartmentId);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime bookedTo = now.plusDays(duration);
        Timestamp timestampNow = Timestamp.valueOf(now);
        Timestamp timestampTo = Timestamp.valueOf(bookedTo);

        apartment.setBookedFrom(timestampNow);
        apartment.setBookedTo(timestampTo);
        update(apartment);
    }

    public String showPerfectly(ArrayList<Apartment> apartments){
        StringBuilder result = new StringBuilder();
        for (Apartment apartment:apartments) {
            result.append("<div>");
            result.append(apartment.toString());
            result.append("</div>\n");
        }
        return  result.toString();
    }

    public ArrayList<Apartment> findAllAvailable(ArrayList<Apartment> apartments){
        ArrayList<Apartment> available = new ArrayList<>();
        for (Apartment apartment : apartments) {
            if(isAvailable(apartment)){
                available.add(apartment);
            }
        }
        return available;
    }

    public JsonArray convertListToJson(ArrayList<Apartment> apartments){
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(apartments);
        JsonArray jsonArray = element.getAsJsonArray();

        return jsonArray;
    }

    private boolean isAvailable(Apartment apartment){
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestampNow = Timestamp.valueOf(now);
        if(apartment.getBookedTo().before(timestampNow)){
            return true;
        }else{
            return false;
        }
    }
}
