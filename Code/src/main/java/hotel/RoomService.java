package hotel;

import framework.Database;

import org.jooq.exception.DataAccessException;
import org.jooq.types.UInteger;

import test.generated.Tables;
import test.generated.tables.records.RoomRecord;

public class RoomService {

    public Room saveNew(Room room) {
        int id = this.save(room);
        room.setId(id);
        return room;
    }
    
    public int save(Room room) {
        RoomRecord roomRecord = Database.getInstance().getDslContext().newRecord(Tables.ROOM);
        roomRecord.setCode(UInteger.valueOf(room.getCode()));
        roomRecord.setAdultspace(UInteger.valueOf(room.getAdultSpace()));
        roomRecord.setChildrenspace(UInteger.valueOf(room.getChildrenSpace()));
        roomRecord.setHotelid(UInteger.valueOf(room.getHotel().getId()));
        try {
            roomRecord.store();
        } catch (DataAccessException e) {
            throw new RoomDaoException(e);
        }
        return roomRecord.getId().intValue();
    }
}
