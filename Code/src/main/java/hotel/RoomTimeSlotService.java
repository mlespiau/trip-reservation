package hotel;


public class RoomTimeSlotService {
    private RoomTimeSlotDao roomTimeSlotDao; 
    
    public RoomTimeSlotService(RoomTimeSlotDao roomTimeSlotDao) {
        this.roomTimeSlotDao = roomTimeSlotDao;
    }
    
    public RoomTimeSlot create(Room room, TimeSlot timeSlot) {
        RoomTimeSlot roomTimeSlot = new RoomTimeSlot(room, timeSlot);
        int id = this.roomTimeSlotDao.save(roomTimeSlot);
        roomTimeSlot.setId(id);
        return roomTimeSlot;
    }
    
}
