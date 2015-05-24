package feature;

import framework.RequestParameters;
import hotel.HotelRepository;
import hotel.HotelService;
import hotel.RoomRepository;
import hotel.RoomSearchSpecificationBuilder;
import hotel.RoomService;
import hotel.RoomTimeSlotRepository;
import security.AuthorizationService;

public class Application {
    // TODO: agentCode, customerCode and systemAdminCode should be part of the authentication API and be accessible from the user object
    public static void main(String[] args) {
        AuthorizationService authorizationService = new AuthorizationService();
        RequestParameters.registerExceptionHandler();
        new HotelAgentEndpoints(authorizationService,
            new HotelService(),
            new RoomService(),
            new HotelRepository(),
            new RoomRepository());
        new CustomerEndpoints(
        		authorizationService,
        		new RoomTimeSlotRepository(),
        		new RoomSearchSpecificationBuilder());
    }
}
