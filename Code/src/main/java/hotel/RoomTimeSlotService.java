package hotel;

import com.google.gson.Gson;

public class RoomTimeSlotService {

    public RoomTimeSlot create(Room room) {
        return new RoomTimeSlot(room);
    }
    
}
