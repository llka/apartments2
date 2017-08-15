package ru.ilka.apartments.model.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import ru.ilka.apartments.model.entity.Apartment;
import ru.ilka.apartments.model.entity.IDatabaseEntity;
import ru.ilka.apartments.model.util.HibernateUtil;

import java.util.ArrayList;

/**
 * Created by Ilya_Kisel aka _Ilka on 8/1/2017.
 */
@Repository
public class ApartmentDaoImpl implements ApartmentDao {

    private static final String COLUMN_ID = "apartmentId";

    private Session session;
    private Transaction transaction;

    public ApartmentDaoImpl(){
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

    public Session openSession(){
        this.session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }

    public void closeSession(){
        this.session.close();
    }

    public void beginTransaction(){
        transaction = session.beginTransaction();
    }

    public Session openSessionWithTransaction(){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeSessionWithTransaction() {
        transaction.commit();
        session.close();
    }

    @Override
    public ArrayList<Apartment> loadAll() {
        Criteria criteria = session.createCriteria(Apartment.class,"APARTMENTS");
        criteria.addOrder(Order.asc(COLUMN_ID));
        return (ArrayList<Apartment>)criteria.list();
    }

    @Override
    public Apartment loadById(int apartmentId) {
        return (Apartment) session.get(Apartment.class, apartmentId);
    }

    @Override
    public void update(IDatabaseEntity apartment) {
        session.update(apartment);
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
