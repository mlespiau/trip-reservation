package hotel;

import org.jooq.exception.DataAccessException;
import org.jooq.types.UInteger;

import test.generated.Tables;
import test.generated.tables.records.HotelRecord;
import feature.Database;

// TODO: Verify the hotel does not exists
// TODO: Verify the agentCode exists
public class HotelDao {
    
    public int save(Hotel hotel) {
        HotelRecord hotelRecord = Database.getInstance().getDslContext().newRecord(Tables.HOTEL);
        hotelRecord.setCode(UInteger.valueOf(hotel.getCode()));
        hotelRecord.setAgentcode(UInteger.valueOf(hotel.getAgentCode()));
        hotelRecord.setLocationcode(UInteger.valueOf(hotel.getLocationCode()));
        hotelRecord.setIncludesbreakfast(UInteger.valueOf(hotel.includesBreakfast() ? 1 : 0));
        try {
            hotelRecord.store();
        } catch (DataAccessException e) {
            throw new HotelDaoException(e);
        }
        return hotelRecord.getId().intValue();
    }
    
//    Result<Record> resultQuery = Database.getInstance().resultQuery("SELECT id, code, agentCode, locationCode, includesBreakfast FROM book");
    
}
