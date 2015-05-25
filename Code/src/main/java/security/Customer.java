package security;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private int code;
    
    public Customer(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }

    @Override
    protected List<Permission> getAllowedActions() {
        List<Permission> allowedActions = new ArrayList<Permission>();
        allowedActions.add(Permission.CAN_SEARCH_ROOMS);
        return allowedActions;
    }
}
