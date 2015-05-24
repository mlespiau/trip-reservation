package hotel;

import org.jooq.exception.DataAccessException;
import org.jooq.types.UInteger;

import test.generated.Tables;
import test.generated.tables.records.HotelRecord;
import framework.DaoException;
import framework.Database;

public class HotelService {
    
    public Hotel saveNew(Hotel hotel) {
        int id = this.save(hotel);
        hotel.setId(id);
        return hotel;
    }
    
    private int save(Hotel hotel) {
        HotelRecord hotelRecord = Database.getInstance().getDslContext().newRecord(Tables.HOTEL);
        hotelRecord.setCode(UInteger.valueOf(hotel.getCode()));
        hotelRecord.setAgentcode(UInteger.valueOf(hotel.getAgentCode()));
        hotelRecord.setLocationcode(UInteger.valueOf(hotel.getLocationCode()));
        hotelRecord.setIncludesbreakfast(UInteger.valueOf(hotel.includesBreakfast() ? 1 : 0));
        try {
            hotelRecord.store();
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
        return hotelRecord.getId().intValue();
    }
}
