package hotel;

import com.google.gson.Gson;

import security.HotelAgent;
import spark.QueryParamsMap;
import test.generated.tables.pojos.HotelPojo;

public class Hotel {
    private int id;
    private int code;
    private int agentCode;
    private int locationCode;
    private boolean includesBreakfast;

    public Hotel(int code, int agentCode, int locationCode, boolean includesBreakfast) {
        this.code = code;
        this.agentCode = agentCode;
        this.locationCode = locationCode;
        this.includesBreakfast = includesBreakfast;
    }
    
    public int getCode() {
        return code;
    }

    public int getLocationCode() {
        return locationCode;
    }

    public boolean includesBreakfast() {
        return includesBreakfast;
    }
    
    public int getAgentCode() {
        return agentCode;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Hotel fromQueryParams(QueryParamsMap queryMap, HotelAgent hotelAgent) {
        return new Hotel(
            queryMap.get("code").integerValue(),
            hotelAgent.getCode(),
            queryMap.get("locationCode").integerValue(),
            queryMap.get("includesBreakfast").booleanValue());
    }

    public static Hotel fromJsonString(String json) {
        return new Gson().fromJson(json, Hotel.class);
    }

    public static Hotel fromPojo(HotelPojo hotelPojo) {
        Hotel hotel = new Hotel(hotelPojo.getCode().intValue(), hotelPojo.getAgentcode().intValue(), hotelPojo.getLocationcode().intValue(), (hotelPojo.getIncludesbreakfast().intValue() == 1 ? true : false));
        hotel.setId(hotelPojo.getId().intValue());
        return hotel;
    }
}
