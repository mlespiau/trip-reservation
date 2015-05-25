package security;

public class Customer extends User {
    private int code;
    
    public Customer(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
}
