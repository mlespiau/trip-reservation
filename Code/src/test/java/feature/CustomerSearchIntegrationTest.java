package feature;

import static org.junit.Assert.*;
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

import roomtimeslot.RoomTimeSlot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CustomerSearchIntegrationTest extends ApiIntegrationTest {
    @Before
    public void setUp() {
        DbRebuilder.getInstance().createFakeDataForTests();
    }
    
    @Test
    public void searchCheckInParameterIsRequired() {
    	SearchParameterBuilder parameters = SearchParameterBuilder.createDefault();
    	parameters.removeCheckIn();
        TestResponse res = this.search(parameters.get());
        assertNotEquals(200, res.status);
        assertTrue(res.body.contains("checkIn"));
    }
    
    @Test
    public void searchCheckOutParameterIsRequired() {
    	SearchParameterBuilder parameters = SearchParameterBuilder.createDefault();
    	parameters.removeCheckOut();
        TestResponse res = this.search(parameters.get());
        assertNotEquals(200, res.status);
        assertTrue(res.body.contains("checkOut"));
    }
    
    @Test
    public void searchAdultSpaceParameterIsRequired() {
    	SearchParameterBuilder parameters = SearchParameterBuilder.createDefault();
    	parameters.removeAdultSpace();
        TestResponse res = this.search(parameters.get());
        assertNotEquals(200, res.status);
        assertTrue(res.body.contains("adultSpace"));
    }
    
    @Test
    public void searchChildrenSpaceParameterIsRequired() {
    	SearchParameterBuilder parameters = SearchParameterBuilder.createDefault();
    	parameters.removeChildrenSpace();
        TestResponse res = this.search(parameters.get());
        assertNotEquals(200, res.status);
        assertTrue(res.body.contains("childrenSpace"));
    }

    @Test
    public void searchBetweenDatesAndRoomSizeReturnsTwoTimeSlot() {
    	SearchParameterBuilder parameters = SearchParameterBuilder.createDefault();
        TestResponse res = this.search(parameters.get());
        assertEquals(200, res.status);
        Type listType = new TypeToken<ArrayList<RoomTimeSlot>>() {}.getType();
        List<RoomTimeSlot> timeSlotList = new Gson().fromJson(res.body, listType);
        assertEquals(2, timeSlotList.size());
    }

    // TODO: Add input validation (checkIn < checkOut)
    
    private TestResponse search(Map<String, String> postParameters) {
        // TODO: Add authentication
        return Request.get("/hotel/room/timeslot/search", postParameters);
    }
    
    private static class SearchParameterBuilder {
    	private Map<String, String> parameters = new HashMap<String, String>();

    	public void addCheckIn() {
    		parameters.put("checkIn", "2014-05-28");
    	}
    	
		public void removeCheckIn() {
			parameters.remove("checkIn");
		}

		public void addCheckOut() {
    		parameters.put("checkOut", "2014-06-10");
    	}

    	public void removeCheckOut() {
			parameters.remove("checkOut");
		}

    	public void addAdultSpace() {
            parameters.put("adultSpace", "2");
    	}
    	
		public void removeAdultSpace() {
			parameters.remove("adultSpace");
		}

    	public void addChildrenSpace() {
    		parameters.put("childrenSpace", "0");
    	}
    	
    	public void removeChildrenSpace() {
    		parameters.remove("childrenSpace");
    	}
    	
    	public Map<String, String> get() {
    		return parameters;
    	}
    	
    	public static SearchParameterBuilder createDefault() {
    		SearchParameterBuilder instance = new SearchParameterBuilder();
    		instance.addCheckIn();
    		instance.addCheckOut();
    		instance.addAdultSpace();
    		instance.addChildrenSpace();
    		return instance;
    	}
    }
}

