package hotel;

import static generated.tables.Hotel.HOTEL;

import org.jooq.types.UInteger;

import framework.DaoException;
import framework.Database;
import generated.tables.pojos.HotelPojo;

public class HotelRepository {

    public Hotel findByCode(int hotelCode, int agentCode) {
        HotelPojo hotelPojo = Database.getInstance().getDslContext().
                select().from(HOTEL).
                where(HOTEL.CODE.equal(UInteger.valueOf(hotelCode))).
                and(HOTEL.AGENTCODE.equal(UInteger.valueOf(agentCode))).
                fetchOne().into(HotelPojo.class);
        if (hotelPojo == null) {
            throw new DaoException("hotelDoesNotExistsOrAgentDoesNotHaveAccessException");
        }
        return Hotel.fromPojo(hotelPojo);
    }

}
