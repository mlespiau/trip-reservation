package hotel;

public class RoomService {
    RoomDao roomDao;
    
    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public Room saveNew(Room room) {
        int id = this.roomDao.save(room);
        room.setId(id);
        return room;
    }

}
