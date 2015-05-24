package hotel;


public class HotelDaoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HotelDaoException(Exception e) {
        super(e);
    }
    
    public HotelDaoException(String message) {
        super(message);
    }

}
