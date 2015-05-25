package feature;

import static serialization.Json.json;
import static spark.Spark.post;
import hotel.Hotel;
import hotel.HotelRepository;
import hotel.HotelService;
import hotel.Room;
import hotel.RoomRepository;
import hotel.RoomService;
import hotel.RoomTimeSlot;
import hotel.RoomTimeSlotService;
import hotel.TimeSlot;
import security.AuthorizationService;
import security.HotelAgent;

public class HotelAgentEndpoints {
    public HotelAgentEndpoints(final AuthorizationService authorizationService, 
            final HotelService hotelService,
            final RoomService roomService,
            final HotelRepository hotelRepository,
            final RoomRepository roomRepository) {
        RoomTimeSlotService roomTimeSlotService = new RoomTimeSlotService();
        post("/hotel", (req, res) -> {
            HotelAgent hotelAgent = authorizationService.createHotelAgentFromRequest(req);
            return hotelService.saveNew(Hotel.fromQueryParams(req.queryMap(), hotelAgent));
        }, json());
        post("/hotel/room", (req, res) -> {
            HotelAgent hotelAgent = authorizationService.createHotelAgentFromRequest(req);
            return roomService.saveNew(Room.fromQueryParams(
                    req.queryMap(),
                    hotelRepository.findByCode(Integer.parseInt(req.queryParams("hotelCode")), hotelAgent.getCode())
                )
             );
        }, json());
        post("/hotel/room/timeslot", (req, res) -> {
            HotelAgent hotelAgent = authorizationService.createHotelAgentFromRequest(req);
            RoomTimeSlot roomTimeSlot = new RoomTimeSlot(roomRepository.findByCode(
                Integer.parseInt(req.queryParams("hotelCode")),
                Integer.parseInt(req.queryParams("roomCode")),
                hotelAgent.getCode()
            ), TimeSlot.fromQueryParams(req.queryMap()));
            return roomTimeSlotService.saveNew(roomTimeSlot);
        }, json());
    }
}
