package hotel;

import com.google.gson.Gson;

public class RoomTimeSlot {
    private TimeSlot timeSlot;
    private Room room;

    public RoomTimeSlot(Room room) {
        this.timeSlot = room.getTimeAvailability().stream().findFirst().get();
        this.room = room;
    }
    
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
    public Room getRoom() {
        return room;
    }

    public static RoomTimeSlot fromJsonString(String json) {
        return new Gson().fromJson(json, RoomTimeSlot.class);
    }
}