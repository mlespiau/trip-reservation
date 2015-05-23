package hotel;

public class HotelService {
    private HotelServiceDao hotelServiceDao;
    
    public HotelService(HotelServiceDao hotelServiceDao) {
        this.hotelServiceDao = hotelServiceDao; 
    }
    
    public Hotel create(Hotel hotel) {
        int id = this.hotelServiceDao.save(hotel);
        hotel.setId(id);
        return hotel;
    }
}
