package hotel;

import org.jooq.Record;

import com.google.gson.Gson;

import spark.QueryParamsMap;
import test.generated.tables.pojos.RoomPojo;

public class Room {
    private int code;
    private Hotel hotel;
    private int adultSpace;
    private int childrenSpace;
    private int id;
    
    public Room(int code, Hotel hotel, int adultSpace, int childrenSpace) {
        this.code = code;
        this.hotel = hotel;
        this.adultSpace = adultSpace;
        this.childrenSpace = childrenSpace;
    }
    
    public static Room fromQueryParams(QueryParamsMap queryMap, Hotel hotel) {
        return new Room(
            queryMap.get("code").integerValue(),
            hotel,
            queryMap.get("adultSpace").integerValue(),
            queryMap.get("childrenSpace").integerValue()
        );
    }
    
    public int getAdultSpace() {
        return adultSpace;
    }
    
    public int getChildrenSpace() {
        return childrenSpace;
    }
    
    public int getCode() {
        return code;
    }
    
    public Hotel getHotel() {
        return hotel;
    }

    public static Room fromJsonString(String json) {
        return new Gson().fromJson(json, Room.class);
    }

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public static Room fromRecord(Record record) {
        Hotel hotel = Hotel.fromRecord(record);
        RoomPojo roomPojo = record.into(RoomPojo.class);
        Room room =  new Room(roomPojo.getCode().intValue(), hotel, roomPojo.getAdultspace().intValue(), roomPojo.getChildrenspace().intValue());
        room.setId(roomPojo.getId().intValue());
        return room;
    }
}
