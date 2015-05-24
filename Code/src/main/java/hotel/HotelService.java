package hotel;

public class HotelService {
    private HotelDao hotelDao;
    
    public HotelService(HotelDao hotelDao) {
        this.hotelDao = hotelDao; 
    }
    
    public Hotel saveNew(Hotel hotel) {
        int id = this.hotelDao.save(hotel);
        hotel.setId(id);
        return hotel;
    }
}
