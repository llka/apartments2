package ru.ilka.apartments.logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.ilka.apartments.TestConfig;
import ru.ilka.apartments.exception.DaoException;
import ru.ilka.apartments.exception.LogicException;
import ru.ilka.apartments.dao.ApartmentDaoImpl;
import ru.ilka.apartments.entity.Apartment;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class ApartmentLogicTest {
    private Apartment TEST_OBJECT = new Apartment();

    private static Logger logger = LoggerFactory.getLogger(ApartmentLogicTest.class);

    private ArrayList<Apartment> TEST_APARTMENTS = new ArrayList<>();

    @InjectMocks
    @Autowired
    private ApartmentLogic apartmentLogic;

    @Mock
    private ApartmentDaoImpl apartmentDao;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        TEST_OBJECT.setApartmentId(11);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime unavailable = now.plusDays(1);
        LocalDateTime available = now.minusDays(1);
        Timestamp timestampUnavailable = Timestamp.valueOf(unavailable);
        Timestamp timestampAvailable = Timestamp.valueOf(available);

        for (int i = 0; i < 3; i++) {
            Apartment apartment = new Apartment();
            apartment.setApartmentId(i);
            apartment.setBookedFrom(timestampAvailable);
            apartment.setBookedTo(timestampUnavailable);
            TEST_APARTMENTS.add(apartment);
        }

        TEST_APARTMENTS.get(0).setBookedTo(timestampAvailable);
    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenFindingWithNegativeId() throws LogicException {
        apartmentLogic.loadById(-1);
    }

    @Test
    public void shouldFindAllApartments() throws LogicException, DaoException {
        //given
        when(apartmentDao.loadAll()).thenReturn(TEST_APARTMENTS);
        //when
        ArrayList<Apartment> apartments = apartmentLogic.loadAll();
        for (int i = 0; i < apartments.size(); i++) {
            logger.info(apartments.get(i).toString());
        }
        //then
        Assert.assertEquals(apartments.size(),TEST_APARTMENTS.size());

        verify(apartmentDao, times(1)).loadAll();
        verify(apartmentDao, times(1)).openSessionWithTransaction();
        verify(apartmentDao, times(1)).closeSessionWithTransaction();
        verifyNoMoreInteractions(apartmentDao);
    }

    @Test
    public void shouldFindApartmentById() throws LogicException, DaoException {
        when(apartmentDao.loadById(11)).thenReturn(TEST_OBJECT);

        Apartment apartment = apartmentLogic.loadById(11);
        Assert.assertEquals(apartment.getApartmentId(),TEST_OBJECT.getApartmentId());

        verify(apartmentDao, times(1)).loadById(11);
        verify(apartmentDao, times(1)).openSessionWithTransaction();
        verify(apartmentDao, times(1)).closeSessionWithTransaction();
        verifyNoMoreInteractions(apartmentDao);
    }

    @Test
    public void shouldFindAllAvailableApartments() {
        //when
        ArrayList<Apartment> apartments = apartmentLogic.findAllAvailable(TEST_APARTMENTS);
        //then
        Assert.assertEquals(apartments.get(0),TEST_APARTMENTS.get(0));
    }
}
