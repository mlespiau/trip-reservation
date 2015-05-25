package feature;

import static serialization.Json.json;
import static spark.Spark.get;
import security.AuthorizationService;
import security.Permission;
import security.SystemAdmin;
import booking.BookingService;

public class SystemAdminEndpoints {
    public SystemAdminEndpoints(AuthorizationService authorizationService, BookingService bookingService) {
        get("/hotel/room/*/booking", (req, res) -> {
            SystemAdmin systemAdmin = authorizationService.createSystemAdminFromRequest(req);
            systemAdmin.assertCan(Permission.CAN_SEE_BOOKING_HISTORY);
            return bookingService.findByRoomId(Integer.parseInt(req.splat()[0]));
        }, json());
    }
}
