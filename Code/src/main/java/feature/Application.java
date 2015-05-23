package feature;
import static serialization.Json.json;
import static spark.Spark.post;
import hotel.Room;
import hotel.RoomTimeSlotService;

public class Application {
    public static void main(String[] args) {
        RoomTimeSlotService roomTimeSlotService = new RoomTimeSlotService();
        post("/room", (req, res) -> roomTimeSlotService.create(
            Room.fromQueryParams(req.queryMap())
        ), json());
    }
}