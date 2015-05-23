package feature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import hotel.Room;
import hotel.RoomTimeSlot;
import hotel.TimeSlot;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;
import spark.utils.IOUtils;

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
        TestResponse res = request("POST", "/room/timeslot?code=1&hotelCode=1&adultSpace=1&childrenSpace=1&availableFrom=2015-12-01&availableTo=2016-01-15&locationCode=1&includesBreakfast=true&agentCode=1");
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

    private TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestResponse {
        public final String body;
        public final int status;
        
        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }
    }
}
