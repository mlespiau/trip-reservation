package hotel;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import feature.DbRebuilder;
import framework.DaoException;

public class HotelDaoTest {
    HotelService hotelService;
    
    public HotelDaoTest() {
        DbRebuilder.getInstance().rebuild();
    }
    
    @After
    public void tearDown() throws IOException {
        this.hotelService = null;
        DbRebuilder.getInstance().cleanData();
    }

    @Before
    public void setUp() {
        this.hotelService = new HotelService();
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
    
    @Test(expected=DaoException.class)
    public void testSaveSameHotelTwiceThrowsException() {
        this.saveDummyHotel(1);
        this.saveDummyHotel(1);
    }
    
    private int saveDummyHotel(int code) {
        Hotel hotel = hotelService.saveNew(this.createDummyHotel(code));
        return hotel.getId();
    }
    
    private Hotel createDummyHotel(int code) {
        return new Hotel(code, 1, 1, true);
    }
}
