package feature;

import static serialization.Json.json;
import static spark.Spark.get;
import hotel.RoomSearchSpecification;
import hotel.RoomTimeSlotRepository;
import security.AuthorizationService;

public class CustomerEndpoints {

    public CustomerEndpoints(AuthorizationService authorizationService, RoomTimeSlotRepository roomTimeSlotRepository) {
        get("/hotel/room/timeslot/search", (req, res) -> {
//            HotelAgent hotelAgent = authorizationService.createHotelAgentFromRequest(req);
//            return hotelService.saveNew(Hotel.fromQueryParams(req.queryMap(), hotelAgent));
            RoomSearchSpecification roomSearchSpecification = RoomSearchSpecification.fromQueryParams(req.queryMap());
            return roomTimeSlotRepository.find(roomSearchSpecification);
        }, json());
    }

}
