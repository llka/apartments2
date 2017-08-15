package ru.ilka.apartments.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import ru.ilka.apartments.HibernateUtilTest;
import ru.ilka.apartments.TestConfig;
import ru.ilka.apartments.model.dao.ApartmentDaoImpl;
import ru.ilka.apartments.model.entity.Apartment;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Here could be your advertisement.
 * Ilya_Kisel +375 29 3880490
 */

@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@DbUnitConfiguration(databaseConnection = "dataSource")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class ApartmentDaoTest{

    private Apartment apartment;

    @Autowired
    private ApartmentDaoImpl apartmentDao;

    @Test
    @DatabaseSetup("/apartmentsData.xml")
    public void shouldLoadById(){
        apartmentDao.setSession(HibernateUtilTest.getSessionFactory().openSession());
        apartmentDao.beginTransaction();
        Apartment apartment = apartmentDao.loadById(99);
        apartmentDao.closeSessionWithTransaction();

        Assert.assertEquals(99,apartment.getApartmentId());
    }


    @Test
    public void shouldLoadAndSortAllApartments(){
        apartmentDao.openSessionWithTransaction();
        ArrayList<Apartment> apartments = apartmentDao.loadAll();
        apartments.forEach(System.out::println);
        apartmentDao.closeSessionWithTransaction();
        Assert.assertTrue(apartments.get(0).getApartmentId() <= apartments.get(apartments.size()-1).getApartmentId());
    }

    @Before
    public void initApartment(){
        apartment = new Apartment();
        apartment.setApartmentId(100);
        apartment.setBookedFrom(Timestamp.valueOf("1999-12-31 12:00:00"));
        apartment.setBookedTo(Timestamp.valueOf("1999-12-31 12:00:00"));
    }

    @Test
    @DatabaseSetup("/apartmentsData.xml")
    @ExpectedDatabase(value ="/expectedAfterAdd.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldSaveApartment(){
        apartmentDao.setSession(HibernateUtilTest.getSessionFactory().openSession());
        apartmentDao.beginTransaction();
        apartmentDao.save(apartment);
        apartmentDao.closeSessionWithTransaction();
    }

    @Test
    @DatabaseSetup("/expectedAfterAdd.xml")
    @ExpectedDatabase(value ="/expectedAfterUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldUpdate(){
        apartmentDao.setSession(HibernateUtilTest.getSessionFactory().openSession());
        apartmentDao.beginTransaction();
        apartment.setBookedFrom(Timestamp.valueOf("2000-12-31 12:00:00"));
        apartmentDao.update(apartment);
        apartmentDao.closeSessionWithTransaction();
    }

    @Test
    @DatabaseSetup("/expectedAfterUpdate.xml")
    @ExpectedDatabase(value ="/expectedAfterDeleteById.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldDeleteById(){
        apartmentDao.setSession(HibernateUtilTest.getSessionFactory().openSession());
        apartmentDao.beginTransaction();
        apartmentDao.delete(apartment);
        apartmentDao.closeSessionWithTransaction();
    }

    @Test
    @DatabaseSetup("/expectedAfterUpdate.xml")
    @ExpectedDatabase(value ="/expectedAfterDeleteAll.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldDeleteAll(){
        apartmentDao.setSession(HibernateUtilTest.getSessionFactory().openSession());
        apartmentDao.beginTransaction();
        apartmentDao.deleteAll();
        apartmentDao.closeSessionWithTransaction();
    }
}
