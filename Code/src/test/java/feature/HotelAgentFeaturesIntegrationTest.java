package feature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import hotel.Hotel;
import hotel.Room;
import hotel.RoomTimeSlot;
import hotel.TimeSlot;
import integration.Request;
import integration.TestResponse;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;

public class HotelAgentFeaturesIntegrationTest {
    private static final String HOTEL_CODE = "1";
    private static final String AGENT_CODE = "1";
    private static final String LOCATION_CODE = "1";
    private static final String INCLUDES_BREAKFAST = "true";

    @BeforeClass
    public static void beforeClass() throws InterruptedException {
        Application.main(null);	  
        // TODO: Remove this hack, this code should hook to an onServerReady event and launch tests afterwards
        Thread.sleep(500);
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }
    
    @Test
    public void aNewHotelShouldBeCreated() {
        Map<String, String> postParameters = new HashMap<String, String>();
        postParameters.put("code", HOTEL_CODE);
        postParameters.put("agentCode", AGENT_CODE);
        postParameters.put("locationCode", LOCATION_CODE);
        postParameters.put("includesBreakfast", INCLUDES_BREAKFAST);
        TestResponse res = Request.post("/hotel", postParameters);
        assertEquals(200, res.status);
        Hotel hotel = Hotel.fromJsonString(res.body);
        assertEquals(Integer.parseInt(AGENT_CODE), hotel.getAgentCode());
        assertEquals(Integer.parseInt(HOTEL_CODE), hotel.getCode());
        assertEquals(Integer.parseInt(LOCATION_CODE), hotel.getLocationCode());
        assertEquals(Boolean.getBoolean(INCLUDES_BREAKFAST), hotel.includesBreakfast());
        assertNotEquals(0, hotel.getId());
    }

    @Test
    public void aNewRoomShouldBeCreated() {
        TestResponse res = Request.post("/room/timeslot?code=1&hotelCode=1&adultSpace=1&childrenSpace=1&availableFrom=2015-12-01&availableTo=2016-01-15&locationCode=1&includesBreakfast=true&agentCode=1");
        RoomTimeSlot roomTimeSlot = RoomTimeSlot.fromJsonString(res.body);
        Room room = roomTimeSlot.getRoom();
        TimeSlot timeSlot = roomTimeSlot.getTimeSlot();
        assertEquals(200, res.status);
        assertEquals(1, room.getHotel().getAgentCode());
        assertEquals(1, room.getAdultSpace());
        assertEquals(1, room.getChildrenSpace());
        assertEquals("2015-12-01", timeSlot.getFrom().toString());		
        assertEquals("2016-01-15", timeSlot.getTo().toString());
        // TODO: Failing test until database integration is implemented
        assertNotEquals(0, timeSlot.getId());
        assertEquals(1, room.getHotel().getLocationCode());	    		
        assertEquals(true, room.getHotel().includesBreakfast());
    }
}
