package feature;

import static serialization.Json.json;
import static spark.Spark.get;
import static spark.Spark.post;
import booking.BookingService;
import framework.RequestParameters;
import hotel.RoomSearchSpecification;
import hotel.RoomSearchSpecificationBuilder;
import roomtimeslot.RoomTimeSlot;
import roomtimeslot.RoomTimeSlotRepository;
import security.AuthorizationService;
import security.Customer;

public class CustomerEndpoints {

    public CustomerEndpoints(
            AuthorizationService authorizationService,
            RoomTimeSlotRepository roomTimeSlotRepository,
            RoomSearchSpecificationBuilder roomSearchSpecificationBuilder,
            BookingService bookingService) {
        get("/hotel/room/timeslot/search", (req, res) -> {
        	// TODO: Authorize 
        	// TODO: Maybe users should be gathered before and the user API ask for different "permissions"
        	// TODO: Add pagination
            RoomSearchSpecification roomSearchSpecification = roomSearchSpecificationBuilder.fromQueryParams(new RequestParameters(req.queryMap()));
            return roomTimeSlotRepository.find(roomSearchSpecification);
        }, json());
        post("/hotel/room/timeslot/book", (req, res) -> {
            RequestParameters requestParameters = new RequestParameters(req.queryMap());
            requestParameters.assertHasValue("roomTimeSlotId");
            requestParameters.assertHasValue("checkIn");
            requestParameters.assertHasValue("checkOut");
            RoomTimeSlot roomTimeSlot = roomTimeSlotRepository.findById(requestParameters.getAsInteger("roomTimeSlotId"));
            Customer customer = authorizationService.createCustomerFromRequest(req);
        	return bookingService.book(customer, roomTimeSlot, requestParameters.getAsLocalDate("checkIn"), requestParameters.getAsLocalDate("checkOut"));
        }, json());
    }

}
