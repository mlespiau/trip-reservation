package feature;
import static serialization.Json.json;
import static spark.Spark.post;
import hotel.Hotel;
import hotel.HotelRepository;
import hotel.HotelService;
import hotel.Room;
import hotel.RoomDao;
import hotel.RoomService;
import hotel.RoomTimeSlot;
import hotel.RoomTimeSlotDao;
import hotel.RoomTimeSlotService;
import hotel.TimeSlot;

public class Application {
    // TODO: agentCode should be part of the authentication API and be accessible if the user is an hotel agent
    // TODO: same with customerCode and systemAdminCode
    public static void main(String[] args) {
        RoomTimeSlotService roomTimeSlotService = new RoomTimeSlotService(
            new RoomTimeSlotDao() {
                @Override
                public int save(RoomTimeSlot roomTimeSlot) {
                    // TODO Auto-generated method stub
                    return 0;
                }
            }
        );
        HotelService hotelService = new HotelService();
        RoomService roomService = new RoomService(new RoomDao());
        HotelRepository hotelRepository = new HotelRepository();
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
        post("/room/timeslot", (req, res) -> roomTimeSlotService.create(
            Room.fromQueryParams(req.queryMap(), hotelRepository.findByCode(Integer.parseInt(req.queryParams("hotelCode")))),
            TimeSlot.fromQueryParams(req.queryMap())
        ), json());
        
    }
}
