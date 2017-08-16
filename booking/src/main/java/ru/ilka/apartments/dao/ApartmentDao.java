package ru.ilka.apartments.dao;

import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.entity.IDatabaseEntity;

import java.util.ArrayList;

/**
 * Here could be your advertisement.
 * Ilya_Kisel +375 29 3880490
 */
public interface ApartmentDao<T extends IDatabaseEntity>  {
    public ArrayList<Apartment> loadAll();
    public Apartment loadById(int apartmentId);
    public void update(T apartment);
    public void save(T apartment);
    public void delete(T apartment);
    public void deleteAll();
}