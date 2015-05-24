package feature;

import static test.generated.tables.Hotel.HOTEL;
import static test.generated.tables.Room.ROOM;
import framework.Database;
import hotel.Hotel;
import hotel.Room;

import org.jooq.Record;
import org.jooq.types.UInteger;

public class RoomRepository {

    public Room findByCode(int hotelId, int roomCode) {
        Record record = Database.getInstance().getDslContext().
            select().from(ROOM.join(HOTEL).on(ROOM.HOTELID.equal(HOTEL.ID))).
            where(ROOM.HOTELID.equal(UInteger.valueOf(hotelId))).
            and(ROOM.CODE.equal(UInteger.valueOf(roomCode))).
            fetchOne();
        Hotel hotel = new Hotel(
            record.getValue(HOTEL.CODE).intValue(),
            record.getValue(HOTEL.AGENTCODE).intValue(),
            record.getValue(HOTEL.LOCATIONCODE).intValue(),
            Boolean.valueOf((record.getValue(HOTEL.INCLUDESBREAKFAST).intValue() == 1 ? "true" : "false")));
        Room room = new Room(
            record.getValue(ROOM.CODE).intValue(),
            hotel,
            record.getValue(ROOM.ADULTSPACE).intValue(),
            record.getValue(ROOM.CHILDRENSPACE).intValue());
        return room;
    }

}
