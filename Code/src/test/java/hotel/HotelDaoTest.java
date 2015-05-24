package hotel;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import feature.DbRebuilder;

public class HotelDaoTest {
    HotelDao hotelDao;
    
    public HotelDaoTest() {
        DbRebuilder.getInstance().rebuild();
    }
    
    @After
    public void tearDown() throws IOException {
        this.hotelDao = null;
        DbRebuilder.getInstance().cleanData();
    }

    @Before
    public void setUp() {
        this.hotelDao = new HotelDao();
    }
    
    @Test
    public void testSaveReturnsId() throws IOException {
        assertEquals(1, this.saveDummyHotel(1));
    }
    
    @Test
    public void testTwoConsecutiveSaveReturnsConsecutiveIds() {
        assertEquals(1, this.saveDummyHotel(1));
        assertEquals(2, this.saveDummyHotel(2));
    }
    
    @Test(expected=HotelDaoException.class)
    public void testSaveSameHotelTwiceThrowsException() {
        this.saveDummyHotel(1);
        this.saveDummyHotel(1);
    }
    
    private int saveDummyHotel(int code) {
        return hotelDao.save(this.createDummyHotel(code));
    }
    
    private Hotel createDummyHotel(int code) {
        return new Hotel(code, 1, 1, true);
    }
}
