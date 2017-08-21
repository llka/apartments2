package ru.ilka.apartments.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.entity.IDatabaseEntity;
import ru.ilka.apartments.exception.DaoException;
import ru.ilka.apartments.util.HibernateUtil;

import java.util.ArrayList;

@Repository
public class ApartmentDaoImpl implements ApartmentDao {
    private Session session;
    private Transaction transaction;

    public ApartmentDaoImpl() {
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Session openSession() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }

    public void closeSession() {
        this.session.close();
    }

    public void beginTransaction() {
        transaction = session.beginTransaction();
    }

    public Session openSessionWithTransaction() throws DaoException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
        } catch (HibernateException e) {
            throw new DaoException("Session opening failed", e);
        }
        transaction = session.beginTransaction();
        return session;
    }

    public void closeSessionWithTransaction() throws DaoException {
        transaction.commit();
        try {
            session.close();
        } catch (HibernateException e) {
            throw new DaoException("Session closing failed", e);
        }
    }

    @Override
    public ArrayList<Apartment> loadAll() {
        return (ArrayList<Apartment>) session.createQuery("from Apartment order by apartmentId").list();
    }

    @Override
    public Apartment loadById(int apartmentId) throws DaoException {
        Apartment apartment = session.get(Apartment.class, apartmentId);
        if (apartment == null) {
            throw new DaoException("No apartment with Id = " + apartmentId + " ");
        }
        return apartment;
    }

    @Override
    public void update(IDatabaseEntity apartment) throws DaoException {
        try {
            session.update(apartment);
        } catch (HibernateException e) {
            throw new DaoException("Update failed", e);
        }
    }

    @Override
    public void save(IDatabaseEntity apartment) {
        session.save(apartment);
    }

    @Override
    public void delete(IDatabaseEntity apartment) {
        session.delete(apartment);
    }

    @Override
    public void deleteAll() {
        ArrayList<Apartment> apartments = loadAll();
        apartments.forEach(this::delete);
    }
}
