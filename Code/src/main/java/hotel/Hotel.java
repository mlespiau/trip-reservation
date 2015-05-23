package hotel;

import com.google.gson.Gson;

import spark.QueryParamsMap;

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

    public static Hotel fromQueryParams(QueryParamsMap queryMap) {
        return new Hotel(
            queryMap.get("code").integerValue(),
            queryMap.get("agentCode").integerValue(),
            queryMap.get("locationCode").integerValue(),
            queryMap.get("includesBreakfast").booleanValue());
    }

    public static Hotel fromJsonString(String json) {
        return new Gson().fromJson(json, Hotel.class);
    }
}
