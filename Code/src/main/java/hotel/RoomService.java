package hotel;

import org.jooq.exception.DataAccessException;
import org.jooq.types.UInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.generated.Tables;
import test.generated.tables.records.RoomRecord;
import framework.DaoException;
import framework.Database;

public class RoomService {
    Logger logger = LoggerFactory.getLogger(getClass()); 

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
            logger.info("errorSavingRoom", e);
            throw new DaoException(e);
        }
        return roomRecord.getId().intValue();
    }
}
