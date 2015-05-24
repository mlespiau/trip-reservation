package hotel;

import org.jooq.Record;

import test.generated.tables.pojos.RoomtimeslotPojo;

import com.google.gson.Gson;

public class RoomTimeSlot {
    private TimeSlot timeSlot;
    private Room room;
    private int id;

    public RoomTimeSlot(Room room, TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
        this.room = room;
    }
    
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
    
    public Room getRoom() {
        return room;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static RoomTimeSlot fromJsonString(String json) {
        return new Gson().fromJson(json, RoomTimeSlot.class);
    }

    public static RoomTimeSlot fromRecord(Record record) {
        Room room = Room.fromRecord(record);
        RoomtimeslotPojo pojo = record.into(RoomtimeslotPojo.class);
        TimeSlot timeSlot = TimeSlot.create(pojo.getFromdate().toLocalDate(), pojo.getTodate().toLocalDate());
        return new RoomTimeSlot(room, timeSlot);
    }
}
