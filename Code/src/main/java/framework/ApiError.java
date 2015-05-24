package framework;

public class ApiError {
    String message;
    
    public ApiError(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
}
