package feature;

import static org.junit.Assert.assertEquals;
import hotel.RoomTimeSlot;
import integration.ApiIntegrationTest;
import integration.Request;
import integration.TestResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CustomerFeaturesIntegrationTest extends ApiIntegrationTest {
    @Before
    public void setUp() {
        DbRebuilder.getInstance().createFakeDataForTests();
    }
    
    @Test
    public void searchWithoutFiltersShouldReturnAllTimeSlots() {
        Map<String, String> postParameters = new HashMap<String, String>();
        // TODO: Add authentication
        TestResponse res = Request.get("/hotel/room/timeslot/search", postParameters);
        assertEquals(200, res.status);
        Type listType = new TypeToken<ArrayList<RoomTimeSlot>>() {}.getType();
        List<RoomTimeSlot> timeSlotList = new Gson().fromJson(res.body, listType);
        assertEquals(1000, timeSlotList.size());
    }
}
