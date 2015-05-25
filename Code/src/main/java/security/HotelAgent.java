package security;

import java.util.ArrayList;
import java.util.List;

public class HotelAgent extends User {
    private int code;
    
    public HotelAgent(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return this.code;
    }
    
    @Override
    protected List<Permission> getAllowedActions() {
        List<Permission> allowedActions = new ArrayList<Permission>();
//        allowedActions.add(Permission.CAN_SEARCH_ROOMS);
        return allowedActions;
    }
}
