package hotel;

import org.jooq.exception.DataAccessException;
import org.jooq.types.UInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.DaoException;
import framework.Database;
import generated.Tables;
import generated.tables.records.HotelRecord;

public class HotelService {
    Logger logger = LoggerFactory.getLogger(getClass()); 

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
            logger.info("errorSavingHotel", e);
            throw new DaoException(e);
        }
        return hotelRecord.getId().intValue();
    }
}
