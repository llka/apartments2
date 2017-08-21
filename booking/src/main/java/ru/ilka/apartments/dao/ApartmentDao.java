package ru.ilka.apartments.dao;

import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.entity.IDatabaseEntity;
import ru.ilka.apartments.exception.DaoException;

import java.util.ArrayList;

public interface ApartmentDao<T extends IDatabaseEntity>  {
    ArrayList<Apartment> loadAll();
    Apartment loadById(int apartmentId) throws DaoException;
    void update(T apartment) throws DaoException;
    void save(T apartment);
    void delete(T apartment);
    void deleteAll();
}
