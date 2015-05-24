package feature;
import static serialization.Json.json;
import static spark.Spark.post;
import hotel.Hotel;
import hotel.HotelService;
import hotel.HotelDao;
import hotel.Room;
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
        HotelService hotelService = new HotelService(new HotelDao());
        post("/hotel", (req, res) -> hotelService.saveNew(Hotel.fromQueryParams(req.queryMap())), json());
        post("/room/timeslot", (req, res) -> roomTimeSlotService.create(
            Room.fromQueryParams(req.queryMap()),
            TimeSlot.fromQueryParams(req.queryMap())
        ), json());
    }
}
