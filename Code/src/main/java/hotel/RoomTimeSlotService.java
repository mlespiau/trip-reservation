package hotel;

import java.sql.Date;

import org.jooq.exception.DataAccessException;
import org.jooq.types.UInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.generated.Tables;
import test.generated.tables.records.RoomtimeslotRecord;
import framework.DaoException;
import framework.Database;


public class RoomTimeSlotService {
    Logger logger = LoggerFactory.getLogger(getClass()); 

    public RoomTimeSlot create(Room room, TimeSlot timeSlot) {
        RoomTimeSlot roomTimeSlot = new RoomTimeSlot(room, timeSlot);
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
    
}
