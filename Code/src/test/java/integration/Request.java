package integration;
import static org.junit.Assert.fail;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;


import spark.utils.IOUtils;


public class Request {
    public static TestResponse get(String path) {
        return request("GET",path);
    }
    
    public static TestResponse post(String path) {
        return request("POST",path);
    }
    
    private static String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
    
    public static TestResponse post(String path, Map<String, String> parameters) {
        String postParameters = "?" + parameters.entrySet()
            .stream().map(p -> urlEncodeUTF8(p.getKey()) + "=" + urlEncodeUTF8(p.getValue()))
            .reduce((p1, p2) -> p1 + "&" + p2)
            .orElse("");
        return request("POST", path + postParameters);
    }
    
    private static TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }
}
