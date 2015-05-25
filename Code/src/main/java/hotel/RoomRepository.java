package hotel;

import static generated.tables.Hotel.HOTEL;
import static generated.tables.Room.ROOM;
import framework.DaoException;
import framework.Database;
import generated.tables.pojos.HotelPojo;

import org.jooq.Record;
import org.jooq.types.UInteger;

public class RoomRepository {

    public Room findByCode(int hotelId, int roomCode, int agentCode) {
        Record record = Database.getInstance().getDslContext().
            select().from(ROOM.join(HOTEL).on(ROOM.HOTELID.equal(HOTEL.ID))).
            where(ROOM.HOTELID.equal(UInteger.valueOf(hotelId))).
            and(ROOM.CODE.equal(UInteger.valueOf(roomCode))).
            and(HOTEL.AGENTCODE.equal(UInteger.valueOf(agentCode))).
            fetchOne();
        if (record.size() < 1) {
            throw new DaoException("roomDoesNotExistsOrAgentDoesNotHaveAccessException");
        }
        Hotel hotel = Hotel.fromPojo(record.into(HotelPojo.class));
        Room room = new Room(
            record.getValue(ROOM.CODE).intValue(),
            hotel,
            record.getValue(ROOM.ADULTSPACE).intValue(),
            record.getValue(ROOM.CHILDRENSPACE).intValue());
        return room;
    }

}
