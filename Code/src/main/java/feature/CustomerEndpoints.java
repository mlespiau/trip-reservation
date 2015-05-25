package feature;

import static serialization.Json.json;
import static spark.Spark.get;
import static spark.Spark.post;
import roomsearch.RoomSearchService;
import roomsearch.RoomSearchSpecification;
import roomsearch.RoomSearchSpecificationBuilder;
import roomtimeslot.RoomTimeSlot;
import roomtimeslot.RoomTimeSlotRepository;
import security.AuthorizationService;
import security.Customer;
import security.Permission;
import booking.BookingService;
import framework.RequestParameters;

public class CustomerEndpoints {

    public CustomerEndpoints(
            AuthorizationService authorizationService,
            RoomTimeSlotRepository roomTimeSlotRepository,
            RoomSearchSpecificationBuilder roomSearchSpecificationBuilder,
            BookingService bookingService,
            RoomSearchService roomSearchService) {
        get("/hotel/room/timeslot/search", (req, res) -> {
        	// TODO: Add pagination
            Customer customer = authorizationService.createCustomerFromRequest(req);
            customer.assertCan(Permission.CAN_SEARCH_ROOMS);
            RoomSearchSpecification roomSearchSpecification = roomSearchSpecificationBuilder.fromQueryParams(new RequestParameters(req.queryMap()));
            return roomSearchService.search(roomSearchSpecification);
        }, json());
        post("/hotel/room/timeslot/book", (req, res) -> {
            Customer customer = authorizationService.createCustomerFromRequest(req);
            customer.assertCan(Permission.CAN_BOOK_ROOMS);
            RequestParameters requestParameters = new RequestParameters(req.queryMap());
            requestParameters.assertHasValue("roomTimeSlotId");
            requestParameters.assertHasValue("checkIn");
            requestParameters.assertHasValue("checkOut");
            RoomTimeSlot roomTimeSlot = roomTimeSlotRepository.findById(requestParameters.getAsInteger("roomTimeSlotId"));
        	return bookingService.book(customer, roomTimeSlot, requestParameters.getAsLocalDate("checkIn"), requestParameters.getAsLocalDate("checkOut"));
        }, json());
    }

}
