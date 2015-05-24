package feature;

import static serialization.Json.json;
import static spark.Spark.get;
import framework.RequestParameters;
import hotel.RoomSearchSpecification;
import hotel.RoomSearchSpecificationBuilder;
import hotel.RoomTimeSlotRepository;
import security.AuthorizationService;

public class CustomerEndpoints {

    public CustomerEndpoints(
    		AuthorizationService authorizationService,
			RoomTimeSlotRepository roomTimeSlotRepository,
			RoomSearchSpecificationBuilder roomSearchSpecificationBuilder) {
        get("/hotel/room/timeslot/search", (req, res) -> {
//            HotelAgent hotelAgent = authorizationService.createHotelAgentFromRequest(req);
//            return hotelService.saveNew(Hotel.fromQueryParams(req.queryMap(), hotelAgent));
            RoomSearchSpecification roomSearchSpecification = roomSearchSpecificationBuilder.fromQueryParams(new RequestParameters(req.queryMap()));
            return roomTimeSlotRepository.find(roomSearchSpecification);
        }, json());
    }

}
