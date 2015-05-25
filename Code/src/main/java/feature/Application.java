package feature;

import hotel.HotelRepository;
import hotel.HotelService;
import room.RoomRepository;
import room.RoomService;
import roomsearch.RoomSearchService;
import roomsearch.RoomSearchSpecificationBuilder;
import roomtimeslot.RoomTimeSlotRepository;
import roomtimeslot.RoomTimeSlotService;
import roomtimeslot.TimeSlotCutter;
import security.AuthorizationService;
import booking.BookingService;
import framework.RequestParameters;

public class Application {
    // TODO: hide some values/ids from json serialization
    public static void main(String[] args) {
        AuthorizationService authorizationService = new AuthorizationService();
        RequestParameters.registerExceptionHandler();
        RoomTimeSlotRepository roomTimeSlotRepository = new RoomTimeSlotRepository();
        new HotelAgentEndpoints(authorizationService,
            new HotelService(),
            new RoomService(),
            new HotelRepository(),
            new RoomRepository());
        new CustomerEndpoints(
            authorizationService,
            roomTimeSlotRepository,
            new RoomSearchSpecificationBuilder(),
            new BookingService(new TimeSlotCutter(), new RoomTimeSlotService()),
            new RoomSearchService(roomTimeSlotRepository));
    }
}
