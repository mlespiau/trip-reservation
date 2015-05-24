package feature;

import hotel.HotelRepository;
import hotel.HotelService;
import hotel.RoomDao;
import hotel.RoomService;

public class Application {
    // TODO: agentCode should be part of the authentication API and be accessible if the user is an hotel agent
    // TODO: same with customerCode and systemAdminCode
    public static void main(String[] args) {
        new HotelAgentEndpoints(new HotelService(), new RoomService(new RoomDao()), new HotelRepository(), new RoomRepository());
    }
}
