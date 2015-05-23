package feature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import hotel.Room;
import hotel.RoomTimeSlot;
import hotel.TimeSlot;
import integration.Request;
import integration.TestResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;

public class PostRoomEndpointIntegrationTest {
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
