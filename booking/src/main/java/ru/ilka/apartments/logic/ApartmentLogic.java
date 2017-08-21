package ru.ilka.apartments.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilka.apartments.exception.DaoException;
import ru.ilka.apartments.exception.LogicException;
import ru.ilka.apartments.dao.ApartmentDaoImpl;
import ru.ilka.apartments.entity.Apartment;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;


@Service
public class ApartmentLogic {

    //@Autowired
    private ApartmentDaoImpl apartmentDao;

    @Autowired
    public ApartmentLogic(ApartmentDaoImpl apartmentDao) {
        this.apartmentDao = apartmentDao;
    }

    public ArrayList<Apartment> loadAll() throws LogicException {
        try {
            apartmentDao.openSessionWithTransaction();
            ArrayList<Apartment> apartments = apartmentDao.loadAll();
            apartmentDao.closeSessionWithTransaction();
            return apartments;
        } catch (DaoException e) {
            throw new LogicException("Can not load all", e);
        }
    }

    public Apartment loadById(int apartmentId) throws LogicException {
        if (apartmentId <= 0) {
            throw new LogicException("Apartment Id can not be less then 1");
        }
        try {
            apartmentDao.openSessionWithTransaction();
            Apartment apartment = apartmentDao.loadById(apartmentId);
            apartmentDao.closeSessionWithTransaction();
            return apartment;
        } catch (DaoException e) {
            throw new LogicException("Problem with loading an apartment ", e);
        }

    }

    public void update(Apartment apartment) throws LogicException {
        try {
            apartmentDao.openSessionWithTransaction();
            apartmentDao.update(apartment);
            apartmentDao.closeSessionWithTransaction();
        } catch (DaoException e) {
            throw new LogicException("Problem with updating apartment ", e);
        }
    }

    public void save(Apartment apartment) throws LogicException {
        try {
            apartmentDao.openSessionWithTransaction();
            apartmentDao.save(apartment);
            apartmentDao.closeSessionWithTransaction();
        } catch (DaoException e) {
            throw new LogicException("Can not save new apartment ", e);
        }
    }

    public void delete(Apartment apartment) throws LogicException {
        try {
            apartmentDao.openSessionWithTransaction();
            apartmentDao.delete(apartment);
            apartmentDao.closeSessionWithTransaction();
        } catch (DaoException e) {
            throw new LogicException("Can not delete apartment: " + apartment + " ", e);
        }
    }

    public void deleteAll() throws LogicException {
        try {
            apartmentDao.openSessionWithTransaction();
            apartmentDao.deleteAll();
            apartmentDao.closeSessionWithTransaction();
        } catch (DaoException e) {
            throw new LogicException("Can not delete all apartments ", e);
        }
    }

    public void addApartment() throws LogicException {
        Apartment apartment = new Apartment();
        apartment.setBookedFrom(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
        apartment.setBookedTo(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
        save(apartment);
    }

    public void bookApartment(int apartmentId, int duration) throws LogicException {
        Apartment apartment = loadById(apartmentId);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime bookedTo = now.plusHours(duration);
        Timestamp timestampNow = Timestamp.valueOf(now);
        Timestamp timestampTo = Timestamp.valueOf(bookedTo);

        apartment.setBookedFrom(timestampNow);
        apartment.setBookedTo(timestampTo);
        update(apartment);
    }

    public ArrayList<Apartment> findAllAvailable(ArrayList<Apartment> apartments) {
        ArrayList<Apartment> available = new ArrayList<>();
        for (Apartment apartment : apartments) {
            if (isAvailable(apartment)) {
                available.add(apartment);
            }
        }
        return available;
    }

    private boolean isAvailable(Apartment apartment) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestampNow = Timestamp.valueOf(now);
        if (apartment.getBookedTo().before(timestampNow)) {
            return true;
        } else {
            return false;
        }
    }
}
