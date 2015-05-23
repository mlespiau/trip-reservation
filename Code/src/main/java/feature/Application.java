package feature;
import static spark.Spark.post;
import hotel.Room;
import hotel.RoomService;
import static serialization.Json.json;

public class Application {
    public static void main(String[] args) {
        RoomService roomService = new RoomService();
        post("/room", (req, res) -> roomService.create(
            Room.fromQueryParams(req.queryMap())
        ), json());
    }
}
