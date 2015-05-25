package feature;

import booking.BookingService;
import hotel.HotelRepository;
import hotel.HotelService;
import room.RoomRepository;
import room.RoomService;
import roomsearch.RoomSearchSpecificationBuilder;
import roomtimeslot.RoomTimeSlotRepository;
import roomtimeslot.RoomTimeSlotService;
import roomtimeslot.TimeSlotCutter;
import security.AuthorizationService;
import framework.RequestParameters;

public class Application {
    // TODO: agentCode, customerCode and systemAdminCode should be part of the authentication API and be accessible from the user object
    // TODO: hide some values/ids from json serialization
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
            new RoomSearchSpecificationBuilder(),
            new BookingService(new TimeSlotCutter(), new RoomTimeSlotService()));
    }
}
