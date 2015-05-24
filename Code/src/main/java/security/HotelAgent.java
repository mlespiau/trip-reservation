package security;

public class HotelAgent extends User {
    private int code;
    
    public HotelAgent(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return this.code;
    }
}
