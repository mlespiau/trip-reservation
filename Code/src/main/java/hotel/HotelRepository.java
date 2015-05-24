package hotel;

import java.util.List;

import org.jooq.types.UInteger;

import test.generated.tables.daos.HotelDao;
import test.generated.tables.pojos.HotelPojo;
import framework.Database;

public class HotelRepository {

    public Hotel findByCode(int hotelCode) {
        HotelDao hotelDao = new HotelDao(Database.getInstance().getConfiguraton());
        List<HotelPojo> hotelList = hotelDao.fetchByCode(UInteger.valueOf(hotelCode));
        return hotelList.size() > 0 ? Hotel.fromPojo(hotelList.get(0)) : null;
    }

}
