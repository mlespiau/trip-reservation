package feature;

import hotel.HotelRepository;
import hotel.HotelService;
import hotel.RoomRepository;
import hotel.RoomService;
import security.AuthorizationService;

public class Application {
    // TODO: agentCode, customerCode and systemAdminCode should be part of the authentication API and be accessible from the user object
    public static void main(String[] args) {
        new HotelAgentEndpoints(new AuthorizationService(),
            new HotelService(),
            new RoomService(),
            new HotelRepository(),
            new RoomRepository());
    }
}
