package feature;
import static serialization.Json.json;
import static spark.Spark.post;
import hotel.Room;
import hotel.RoomTimeSlot;
import hotel.RoomTimeSlotDao;
import hotel.RoomTimeSlotService;
import hotel.TimeSlot;

public class Application {
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
        post("/room/timeslot", (req, res) -> roomTimeSlotService.create(
            Room.fromQueryParams(req.queryMap()),
            TimeSlot.fromQueryParams(req.queryMap())
        ), json());
    }
}
