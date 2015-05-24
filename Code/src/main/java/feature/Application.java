package feature;

public class Application {
    // TODO: agentCode should be part of the authentication API and be accessible if the user is an hotel agent
    // TODO: same with customerCode and systemAdminCode
    public static void main(String[] args) {
        new HotelAgentEndpoints();
    }
}
