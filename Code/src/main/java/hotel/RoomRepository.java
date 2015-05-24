package hotel;

import static test.generated.tables.Hotel.HOTEL;
import static test.generated.tables.Room.ROOM;
import framework.Database;

import org.jooq.Record;
import org.jooq.types.UInteger;

import test.generated.tables.pojos.HotelPojo;

public class RoomRepository {

    public Room findByCode(int hotelId, int roomCode, int agentCode) {
        Record record = Database.getInstance().getDslContext().
            select().from(ROOM.join(HOTEL).on(ROOM.HOTELID.equal(HOTEL.ID))).
            where(ROOM.HOTELID.equal(UInteger.valueOf(hotelId))).
            and(ROOM.CODE.equal(UInteger.valueOf(roomCode))).
            and(HOTEL.AGENTCODE.equal(UInteger.valueOf(agentCode))).
            fetchOne();
        Hotel hotel = Hotel.fromPojo(record.into(HotelPojo.class));
        Room room = new Room(
            record.getValue(ROOM.CODE).intValue(),
            hotel,
            record.getValue(ROOM.ADULTSPACE).intValue(),
            record.getValue(ROOM.CHILDRENSPACE).intValue());
        return room;
    }

}
