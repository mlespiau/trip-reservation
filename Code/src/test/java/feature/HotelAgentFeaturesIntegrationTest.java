package feature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import framework.ApiError;
import hotel.Hotel;
import hotel.HotelService;
import integration.ApiIntegrationTest;
import integration.Request;
import integration.TestResponse;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import room.Room;
import room.RoomService;
import roomtimeslot.RoomTimeSlot;
import roomtimeslot.TimeSlot;

import com.google.gson.Gson;

public class HotelAgentFeaturesIntegrationTest extends ApiIntegrationTest {
    private static final String HOTEL_CODE = "1";
    private static final String HOTEL_CODE_CREATE = "2";
    private static final String AGENT_CODE = "1";
    private static final String LOCATION_CODE = "1";
    private static final String INCLUDES_BREAKFAST = "true";
    private static final String ROOM_CODE = "1";
    private static final String ROOM_CODE_CREATE = "2";
    private static final String ADULT_SPACE = "1";
    private static final String CHILDREN_SPACE = "0";
    private static final String AGENT_TOKEN = "someSecurityToken";

    @Before
    public void setUp() {
        HotelService hotelService = new HotelService();
        Hotel hotel = hotelService.saveNew(new Hotel(1, 1, 1, true));
        RoomService roomService = new RoomService();
        roomService.saveNew(new Room(1, hotel, 1, 1));
    }

    @Test
    public void aNewHotelShouldBeCreated() {
        Map<String, String> postParameters = new HashMap<String, String>();
        postParameters.put("code", HOTEL_CODE_CREATE);
        postParameters.put("agentCode", AGENT_CODE);
        postParameters.put("agentToken", AGENT_TOKEN);
        postParameters.put("locationCode", LOCATION_CODE);
        postParameters.put("includesBreakfast", INCLUDES_BREAKFAST);
        TestResponse res = Request.post("/hotel", postParameters);
        assertEquals(200, res.status);
        Hotel hotel = Hotel.fromJsonString(res.body);
        assertEquals(Integer.parseInt(AGENT_CODE), hotel.getAgentCode());
        assertEquals(Integer.parseInt(HOTEL_CODE_CREATE), hotel.getCode());
        assertEquals(Integer.parseInt(LOCATION_CODE), hotel.getLocationCode());
        assertEquals(Boolean.valueOf(INCLUDES_BREAKFAST).booleanValue(), hotel.includesBreakfast());
        assertNotEquals(0, hotel.getId());
    }

    @Test
    public void aNewRoomShouldBeCreated() {
        Map<String, String> postParameters = new HashMap<String, String>();
        postParameters.put("code", ROOM_CODE_CREATE);
        postParameters.put("hotelCode", HOTEL_CODE);
        postParameters.put("adultSpace", ADULT_SPACE);
        postParameters.put("agentToken", AGENT_TOKEN);
        postParameters.put("agentCode", AGENT_CODE);
        postParameters.put("childrenSpace", CHILDREN_SPACE);
        TestResponse res = Request.post("/hotel/room", postParameters);
        assertEquals(200, res.status);
        Room room = Room.fromJsonString(res.body);
        assertEquals(Integer.parseInt(ROOM_CODE_CREATE), room.getCode());
        assertEquals(Integer.parseInt(HOTEL_CODE), room.getHotel().getCode());
        assertEquals(Integer.parseInt(ADULT_SPACE), room.getAdultSpace());
        assertEquals(Integer.parseInt(CHILDREN_SPACE), room.getChildrenSpace());
        assertNotEquals(0, room.getId());
    }
    
    @Test
    public void aNewRoomTimeSlotShouldBeCreated() {
        Map<String, String> postParameters = new HashMap<String, String>();
        postParameters.put("agentToken", AGENT_TOKEN);
        postParameters.put("roomCode", ROOM_CODE);
        postParameters.put("hotelCode", HOTEL_CODE);
        postParameters.put("agentCode", AGENT_CODE);
        postParameters.put("availableFrom", "2015-12-01");
        postParameters.put("availableTo", "2016-01-15");
        TestResponse res = Request.post("/hotel/room/timeslot", postParameters);
        RoomTimeSlot roomTimeSlot = RoomTimeSlot.fromJsonString(res.body);
        Room room = roomTimeSlot.getRoom();
        TimeSlot timeSlot = roomTimeSlot.getTimeSlot();
        assertEquals(200, res.status);
        assertEquals(1, room.getHotel().getAgentCode());
        assertEquals(1, room.getAdultSpace());
        assertEquals(1, room.getChildrenSpace());
        assertEquals("2015-12-01", timeSlot.getFrom().toString());
        assertEquals("2016-01-15", timeSlot.getTo().toString());
        assertNotEquals(0, roomTimeSlot.getTimeSlot().getId());
        assertEquals(1, room.getHotel().getLocationCode());
        assertEquals(true, room.getHotel().includesBreakfast());
    }
    
    @Test
    public void aUnauthorizedErrorShouldOcurr() {
        Map<String, String> postParameters = new HashMap<String, String>();
        this.testUnauthorizedErrorOccursForEndPoints("/hotel", postParameters);
        this.testUnauthorizedErrorOccursForEndPoints("/hotel/room", postParameters);
        this.testUnauthorizedErrorOccursForEndPoints("/hotel/room/timeslot", postParameters);
    }
    
    private void testUnauthorizedErrorOccursForEndPoints(String endPoint, Map<String, String> postParameters) {
        TestResponse res = Request.post(endPoint, postParameters);
        assertEquals(401, res.status);
        ApiError error = new Gson().fromJson(res.body, ApiError.class);
        assertEquals("Unauthorized. User is not authenticated.", error.getMessage());
    }
}
