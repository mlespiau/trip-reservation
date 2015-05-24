package feature;

import static serialization.Json.json;
import static spark.Spark.post;
import hotel.Hotel;
import hotel.HotelRepository;
import hotel.HotelService;
import hotel.Room;
import hotel.RoomDao;
import hotel.RoomService;
import hotel.RoomTimeSlotService;
import hotel.TimeSlot;

public class HotelAgentEndpoints {
    public HotelAgentEndpoints(final HotelService hotelService) {
        RoomService roomService = new RoomService(new RoomDao());
        RoomTimeSlotService roomTimeSlotService = new RoomTimeSlotService();
        HotelRepository hotelRepository = new HotelRepository();
        RoomRepository roomRepository = new RoomRepository();
        post("/hotel", (req, res) -> hotelService.saveNew(Hotel.fromQueryParams(req.queryMap())), json());
        // TODO: here we need some validations hotel should 
        // Hotel exist
        // AgentCode is same as Hotel's agentCode (it means it can edit that hotel)
        post("/hotel/room", (req, res) -> roomService .saveNew(
           Room.fromQueryParams(
               req.queryMap(),
               hotelRepository.findByCode(Integer.parseInt(req.queryParams("hotelCode")))
           )
        ), json());
        post("/hotel/room/timeslot", (req, res) -> roomTimeSlotService.create(
            roomRepository.findByCode(
                Integer.parseInt(req.queryParams("hotelCode")),
                Integer.parseInt(req.queryParams("roomCode"))
            ),
            TimeSlot.fromQueryParams(req.queryMap())
        ), json());
    }
}
