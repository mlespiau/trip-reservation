package integration;

public class TestResponse {
    public final String body;
    public final int status;
    
    public TestResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }
}
