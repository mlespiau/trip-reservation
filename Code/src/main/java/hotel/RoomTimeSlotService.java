package hotel;

import java.sql.Date;

import org.jooq.exception.DataAccessException;
import org.jooq.types.UInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.generated.Tables;
import test.generated.tables.daos.RoomtimeslotDao;
import test.generated.tables.records.RoomtimeslotRecord;
import framework.DaoException;
import framework.Database;

// TODO: Move generated classes to generated package instead of test.generated
public class RoomTimeSlotService {
    Logger logger = LoggerFactory.getLogger(getClass()); 

    public RoomTimeSlot saveNew(RoomTimeSlot roomTimeSlot) {
        int id = this.save(roomTimeSlot);
        roomTimeSlot.getTimeSlot().setId(id);
        return roomTimeSlot;
    }
    
    private int save(RoomTimeSlot roomTimeSlot) {
        RoomtimeslotRecord roomTimeSlotRecord = Database.getInstance().getDslContext().newRecord(Tables.ROOMTIMESLOT);
        roomTimeSlotRecord.setFromdate(Date.valueOf(roomTimeSlot.getTimeSlot().getFrom()));
        roomTimeSlotRecord.setTodate(Date.valueOf(roomTimeSlot.getTimeSlot().getTo()));
        roomTimeSlotRecord.setRoomid(UInteger.valueOf(roomTimeSlot.getRoom().getId()));
        try {
            roomTimeSlotRecord.store();
        } catch (DataAccessException e) {
            logger.info("errorSavingTimeSlotService", e);
            throw new DaoException(e);
        }
        return roomTimeSlotRecord.getId().intValue();
    }

    public void delete(RoomTimeSlot roomTimeSlot) {
        RoomtimeslotDao dao = new RoomtimeslotDao(Database.getInstance().getConfiguraton());
        dao.deleteById(UInteger.valueOf(roomTimeSlot.getTimeSlot().getId()));
    }
}
