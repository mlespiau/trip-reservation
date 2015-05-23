package hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

import spark.QueryParamsMap;

import com.google.gson.Gson;

public class Room {
    private int code;
    private Hotel hotel;
    private int adultSpace;
    private int childrenSpace;
    private Collection<TimeSlot> availability;
    
    public Room(int code, Hotel hotel, int adultSpace, int childrenSpace, Collection<TimeSlot> availability) {
        this.code = code;
        this.hotel = hotel;
        this.adultSpace = adultSpace;
        this.childrenSpace = childrenSpace;
        this.availability = availability;
    }
    
    public static Room fromQueryParams(QueryParamsMap queryMap) {
        TimeSlot time = TimeSlot.create(
            LocalDate.parse(queryMap.get("availableFrom").value(), DateTimeFormatter.ISO_LOCAL_DATE),
            LocalDate.parse(queryMap.get("availableTo").value(), DateTimeFormatter.ISO_LOCAL_DATE)
        );
        Collection<TimeSlot> availability = new ArrayList<TimeSlot>();
        availability.add(time);
        return new Room(
            queryMap.get("code").integerValue(),
            new Hotel(
                queryMap.get("hotelCode").integerValue(),
                queryMap.get("agentCode").integerValue(),
                queryMap.get("locationCode").integerValue(),
                queryMap.get("includesBreakfast").booleanValue()
            ),
            queryMap.get("adultSpace").integerValue(),
            queryMap.get("childrenSpace").integerValue(),
            availability
        );
    }
    
    public static Room fromJsonString(String json) {
        return new Gson().fromJson(json, Room.class);
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
    
    public Collection<TimeSlot> getTimeAvailability() {
        return availability;
    }
}
