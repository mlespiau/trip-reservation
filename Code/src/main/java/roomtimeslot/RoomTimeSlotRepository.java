package roomtimeslot;

import static generated.tables.Hotel.HOTEL;
import static generated.tables.Room.ROOM;
import static generated.tables.Roomtimeslot.ROOMTIMESLOT;
import static generated.tables.Booking.BOOKING;


import java.util.ArrayList;
import java.util.List;

import org.jooq.Record;
import org.jooq.Result;
import org.jooq.types.UInteger;

import roomsearch.RoomSearchSpecification;
import framework.Database;

public class RoomTimeSlotRepository {

    public List<RoomTimeSlot> find(RoomSearchSpecification roomSearchSpecification) {
        Result<Record> result = roomSearchSpecification.toQuery().fetch();
        List<RoomTimeSlot> list = new ArrayList<RoomTimeSlot>();
        for(Record record : result) {
            list.add(RoomTimeSlot.fromRecord(record));
        }
        return  list;
    }

    // TODO: domain "leaks" as RoomTimeSlot as they know about other tables? document that would like to make it agnostic
    public RoomTimeSlot findById(int id) {
        Record record = Database.getInstance().getDslContext().
            select().from(ROOMTIMESLOT).
            join(ROOM).on(ROOMTIMESLOT.ROOMID.equal(ROOM.ID)).
            join(HOTEL).on(ROOM.HOTELID.equal(HOTEL.ID)).
            where(ROOMTIMESLOT.ID.equal(UInteger.valueOf(id))).
            fetchOne();
        return RoomTimeSlot.fromRecord(record);
    }

    public List<RoomTimeSlot> findBookedByRoomId(int roomId) {
        Result<Record> result = Database.getInstance().getDslContext().
                select().from(ROOMTIMESLOT).
                join(ROOM).on(ROOMTIMESLOT.ROOMID.equal(ROOM.ID)).
                join(HOTEL).on(ROOM.HOTELID.equal(HOTEL.ID)).
                join(BOOKING).on(ROOMTIMESLOT.BOOKINGID.equal(BOOKING.ID)).
                where(ROOM.ID.equal(UInteger.valueOf(roomId))).fetch();
        List<RoomTimeSlot> list = new ArrayList<RoomTimeSlot>();
        for(Record record : result) {
            list.add(RoomTimeSlot.fromRecord(record));
        }
        return list;
    }


}
