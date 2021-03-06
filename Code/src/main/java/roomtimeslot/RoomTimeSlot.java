package roomtimeslot;

import generated.tables.pojos.RoomtimeslotPojo;

import org.jooq.Record;

import room.Room;
import booking.Booking;

import com.google.gson.Gson;

public class RoomTimeSlot {
    private TimeSlot timeSlot;
    private Room room;
    private Booking booking;

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
    
    public Booking getBooking() {
        return booking;
    }

    public static RoomTimeSlot fromJsonString(String json) {
        return new Gson().fromJson(json, RoomTimeSlot.class);
    }

    public static RoomTimeSlot fromRecord(Record record) {
        Room room = Room.fromRecord(record);
        RoomtimeslotPojo pojo = record.into(RoomtimeslotPojo.class);
        TimeSlot timeSlot = TimeSlot.create(pojo.getFromdate().toLocalDate(), pojo.getTodate().toLocalDate());
        timeSlot.setId(pojo.getId().intValue());
        return new RoomTimeSlot(room, timeSlot);
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
