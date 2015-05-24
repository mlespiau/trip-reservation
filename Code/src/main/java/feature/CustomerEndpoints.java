package feature;

import static serialization.Json.json;
import static spark.Spark.get;
import hotel.RoomTimeSlotRepository;
import security.AuthorizationService;

public class CustomerEndpoints {

    public CustomerEndpoints(AuthorizationService authorizationService, RoomTimeSlotRepository roomTimeSlotRepository) {
        get("/hotel/room/timeslot/search", (req, res) -> {
//            HotelAgent hotelAgent = authorizationService.createHotelAgentFromRequest(req);
//            return hotelService.saveNew(Hotel.fromQueryParams(req.queryMap(), hotelAgent));
            
            return roomTimeSlotRepository.find();
        }, json());
    }

}
