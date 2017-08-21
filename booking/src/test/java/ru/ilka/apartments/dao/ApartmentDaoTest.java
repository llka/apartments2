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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ru.ilka.apartments.entity.Apartment;
import ru.ilka.apartments.exception.DaoException;


import java.sql.Timestamp;
import java.util.ArrayList;

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

    private static Logger logger = LoggerFactory.getLogger(ApartmentDaoTest.class);

    private Apartment apartment;

    @Autowired
    private ApartmentDaoImpl apartmentDao;

    @Test
    @DatabaseSetup("/apartmentsData.xml")
    public void shouldLoadById() throws DaoException {
        apartmentDao.setSession(HibernateUtilTest.getSessionFactory().openSession());
        apartmentDao.beginTransaction();
        Apartment apartment = apartmentDao.loadById(99);
        apartmentDao.closeSessionWithTransaction();

        Assert.assertEquals(99,apartment.getApartmentId());
    }

    @Test(expected = DaoException.class)
    public void shouldThrowDaoExceptionWhenFindingWithNonexistentId() throws DaoException {
        apartmentDao.setSession(HibernateUtilTest.getSessionFactory().openSession());
        apartmentDao.beginTransaction();
        apartmentDao.loadById(999);
        apartmentDao.closeSessionWithTransaction();
    }


    @Test
    public void shouldLoadAndSortAllApartments() throws DaoException {
        apartmentDao.openSessionWithTransaction();
        ArrayList<Apartment> apartments = apartmentDao.loadAll();
        apartmentDao.closeSessionWithTransaction();
        for (int i = 0; i < apartments.size(); i++) {
            logger.info(apartments.get(i).toString());
        }
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
    public void shouldSaveApartment() throws DaoException{
        apartmentDao.setSession(HibernateUtilTest.getSessionFactory().openSession());
        apartmentDao.beginTransaction();
        apartmentDao.save(apartment);
        apartmentDao.closeSessionWithTransaction();
    }

    @Test
    @DatabaseSetup("/expectedAfterAdd.xml")
    @ExpectedDatabase(value ="/expectedAfterUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldUpdate() throws DaoException {
        apartmentDao.setSession(HibernateUtilTest.getSessionFactory().openSession());
        apartmentDao.beginTransaction();
        apartment.setBookedFrom(Timestamp.valueOf("2000-12-31 12:00:00"));
        apartmentDao.update(apartment);
        apartmentDao.closeSessionWithTransaction();
    }

    @Test
    @DatabaseSetup("/expectedAfterUpdate.xml")
    @ExpectedDatabase(value ="/expectedAfterDeleteById.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldDeleteById() throws DaoException{
        apartmentDao.setSession(HibernateUtilTest.getSessionFactory().openSession());
        apartmentDao.beginTransaction();
        apartmentDao.delete(apartment);
        apartmentDao.closeSessionWithTransaction();
    }

    @Test
    @DatabaseSetup("/expectedAfterUpdate.xml")
    @ExpectedDatabase(value ="/expectedAfterDeleteAll.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void shouldDeleteAll() throws DaoException{
        apartmentDao.setSession(HibernateUtilTest.getSessionFactory().openSession());
        apartmentDao.beginTransaction();
        apartmentDao.deleteAll();
        apartmentDao.closeSessionWithTransaction();
    }
}
