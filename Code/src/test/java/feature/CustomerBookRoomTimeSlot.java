package feature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import integration.ApiIntegrationTest;
import integration.Request;
import integration.TestResponse;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import roomtimeslot.RoomTimeSlot;

public class CustomerBookRoomTimeSlot extends ApiIntegrationTest {
    @Before
    public void setUp() {
        DbRebuilder.getInstance().createFakeDataForTests();
    }

    @Test
    public void aRequiredRoomTimeSlotIdErrorShouldOccur() {
        Map<String, String> parameters = new HashMap<String, String>();
        TestResponse res = this.bookRoomTimeSlot(parameters);
        assertNotEquals(200, res.status);
        assertTrue(res.body.contains("roomTimeSlotId"));
    }
    
    @Test
    public void aRequiredCheckInErrorShouldOccur() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("roomTimeSlotId", "1");
        TestResponse res = this.bookRoomTimeSlot(parameters);
        assertNotEquals(200, res.status);
        assertTrue(res.body.contains("checkIn"));
    }
    
    @Test
    public void aRequiredCheckOutErrorShouldOccur() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("checkIn", "2015-05-21");
        parameters.put("roomTimeSlotId", "1");
        TestResponse res = this.bookRoomTimeSlot(parameters);
        assertNotEquals(200, res.status);
        assertTrue(res.body.contains("checkOut"));
    }
    
    @Test
    public void aUserAuthenticationErrorSouldBeReceived() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("checkIn", "2014-10-21");
        parameters.put("checkOut", "2015-11-01");
        parameters.put("roomTimeSlotId", "1");
        TestResponse res = this.bookRoomTimeSlot(parameters);
        assertNotEquals(200, res.status);
        assertTrue(res.body.contains("User"));
    }
    
    @Test
    public void aBookedRoomTimeSlotShouldBeReceived() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("checkIn", "2014-10-21");
        parameters.put("checkOut", "2015-11-01");
        parameters.put("roomTimeSlotId", "1");
        parameters.put("customerCode", "1");
        parameters.put("customerToken", "someToken");
        TestResponse res = this.bookRoomTimeSlot(parameters);
        assertEquals(200, res.status);
        RoomTimeSlot roomTimeSlot = RoomTimeSlot.fromJsonString(res.body);
        assertNotEquals(0, roomTimeSlot.getBooking().getId());
        assertEquals(1, roomTimeSlot.getBooking().getCustomerCode());
        assertNotNull(roomTimeSlot.getBooking().getDate());
    }
    
    private TestResponse bookRoomTimeSlot(Map<String, String> postParameters) {
        return Request.post("/hotel/room/timeslot/book", postParameters);
    }
    
    // TODO: Missing parameter validation (ints and dates)
}
