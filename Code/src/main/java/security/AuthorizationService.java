package security;

import spark.Request;

public class AuthorizationService {
    public HotelAgent createHotelAgentFromRequest(Request req) {
        if (req.queryParams("agentToken") != null) {
            assertAgentTokenIsValid();
            return new HotelAgent(Integer.parseInt(req.queryParams("agentCode")));
        } else {
            throw new InvalidUserException();
        }
    }

    private void assertAgentTokenIsValid() {
        // This should authenticate the hotel agent code is valid and that the session is active
    }
}
