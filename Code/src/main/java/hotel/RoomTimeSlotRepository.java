package hotel;

import static test.generated.tables.Hotel.HOTEL;
import static test.generated.tables.Room.ROOM;
import static test.generated.tables.Roomtimeslot.ROOMTIMESLOT;

import java.util.ArrayList;
import java.util.List;

import org.jooq.Record;
import org.jooq.Result;

import framework.Database;

public class RoomTimeSlotRepository {

    public List<RoomTimeSlot> find() {
        Result<Record> result = Database.getInstance().getDslContext().
            select().from(ROOMTIMESLOT).
            join(ROOM).on(ROOMTIMESLOT.ROOMID.equal(ROOM.ID)).
            join(HOTEL).on(ROOM.HOTELID.equal(HOTEL.ID)).
            fetch();
        List<RoomTimeSlot> list = new ArrayList<RoomTimeSlot>();
        for(Record record : result) {
            list.add(RoomTimeSlot.fromRecord(record));
        }
        return  list;
    }
}
