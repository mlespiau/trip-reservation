package feature;

import static serialization.Json.json;
import static spark.Spark.post;
import hotel.Hotel;
import hotel.HotelRepository;
import hotel.HotelService;
import room.Room;
import room.RoomRepository;
import room.RoomService;
import roomtimeslot.RoomTimeSlot;
import roomtimeslot.RoomTimeSlotService;
import roomtimeslot.TimeSlot;
import security.AuthorizationService;
import security.HotelAgent;
import security.Permission;

public class HotelAgentEndpoints {
    public HotelAgentEndpoints(final AuthorizationService authorizationService, 
            final HotelService hotelService,
            final RoomService roomService,
            final HotelRepository hotelRepository,
            final RoomRepository roomRepository) {
        RoomTimeSlotService roomTimeSlotService = new RoomTimeSlotService();
        post("/hotel", (req, res) -> {
            HotelAgent hotelAgent = authorizationService.createHotelAgentFromRequest(req);
            hotelAgent.assertCan(Permission.CAN_CREATE_HOTEL);
            return hotelService.saveNew(Hotel.fromQueryParams(req.queryMap(), hotelAgent));
        }, json());
        post("/hotel/room", (req, res) -> {
            HotelAgent hotelAgent = authorizationService.createHotelAgentFromRequest(req);
            hotelAgent.assertCan(Permission.CAN_CREATE_ROOM);
            return roomService.saveNew(Room.fromQueryParams(
                    req.queryMap(),
                    hotelRepository.findByCode(Integer.parseInt(req.queryParams("hotelCode")), hotelAgent.getCode())
                )
             );
        }, json());
        post("/hotel/room/timeslot", (req, res) -> {
            HotelAgent hotelAgent = authorizationService.createHotelAgentFromRequest(req);
            hotelAgent.assertCan(Permission.CAN_ADD_TIMESLOT);
            RoomTimeSlot roomTimeSlot = new RoomTimeSlot(roomRepository.findByCode(
                Integer.parseInt(req.queryParams("hotelCode")),
                Integer.parseInt(req.queryParams("roomCode")),
                hotelAgent.getCode()
            ), TimeSlot.fromQueryParams(req.queryMap()));
            return roomTimeSlotService.saveNew(roomTimeSlot);
        }, json());
    }
}
