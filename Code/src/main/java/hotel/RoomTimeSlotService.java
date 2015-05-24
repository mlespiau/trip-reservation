package hotel;

import java.sql.Date;

import org.jooq.exception.DataAccessException;
import org.jooq.types.UInteger;

import test.generated.Tables;
import test.generated.tables.records.RoomtimeslotRecord;
import framework.Database;


public class RoomTimeSlotService {

    public RoomTimeSlot create(Room room, TimeSlot timeSlot) {
        RoomTimeSlot roomTimeSlot = new RoomTimeSlot(room, timeSlot);
        int id = this.save(roomTimeSlot);
        roomTimeSlot.setId(id);
        return roomTimeSlot;
    }
    
    private int save(RoomTimeSlot roomTimeSlot) {
        RoomtimeslotRecord roomTimeSlotRecord = Database.getInstance().getDslContext().newRecord(Tables.ROOMTIMESLOT);
        roomTimeSlotRecord.setFromdate(Date.valueOf(roomTimeSlot.getTimeSlot().getFrom()));
        roomTimeSlotRecord.setTodate(Date.valueOf(roomTimeSlot.getTimeSlot().getTo()));
        roomTimeSlotRecord.setRoomid(UInteger.valueOf(roomTimeSlot.getRoom().getId()));
        // TODO: Fix save method in all DAOs (error handling)
        try {
            roomTimeSlotRecord.store();
        } catch (DataAccessException e) {
            throw new RoomDaoException(e);
        }
        return roomTimeSlotRecord.getId().intValue();
    }
    
}
