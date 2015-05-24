package security;

import static spark.Spark.exception;
import spark.Request;

import com.google.gson.Gson;

import framework.ApiError;

public class AuthorizationService {
    public AuthorizationService() {
        exception(InvalidUserException.class, (e, request, response) -> {
            response.status(401);
            Gson gson = new Gson();
            response.body(gson.toJson(new ApiError("Unauthorized. User is not authenticated.")));
        });
    }
    public HotelAgent createHotelAgentFromRequest(Request req) {
        if (req.queryParams("agentToken") != null) {
            assertAgentTokenIsValid();
            return new HotelAgent(Integer.parseInt(req.queryParams("agentCode")));
        } else {
            throw new InvalidUserException();
        }
    }

    private void assertAgentTokenIsValid() {
        // This is left blank here on purpose. 
        // This should authenticate against an authentication service 
        // the hotel agent code should be valid and that the session is active
    }
}
