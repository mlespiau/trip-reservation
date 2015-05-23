package hotel;


public class RoomTimeSlotService {

    public RoomTimeSlot create(Room room, TimeSlot timeSlot) {
        return new RoomTimeSlot(room, timeSlot);
    }
    
}
