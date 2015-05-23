package hotel;

import spark.QueryParamsMap;

public class Room {
    private int code;
    private Hotel hotel;
    private int adultSpace;
    private int childrenSpace;
    
    public Room(int code, Hotel hotel, int adultSpace, int childrenSpace) {
        this.code = code;
        this.hotel = hotel;
        this.adultSpace = adultSpace;
        this.childrenSpace = childrenSpace;
    }
    
    public static Room fromQueryParams(QueryParamsMap queryMap) {
        return new Room(
            queryMap.get("code").integerValue(),
            new Hotel(
                queryMap.get("hotelCode").integerValue(),
                queryMap.get("agentCode").integerValue(),
                queryMap.get("locationCode").integerValue(),
                queryMap.get("includesBreakfast").booleanValue()
            ),
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
}
