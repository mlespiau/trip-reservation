package hotel;

public class Hotel {
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

}
