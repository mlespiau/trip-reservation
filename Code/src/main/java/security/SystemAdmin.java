package security;

import java.util.ArrayList;
import java.util.List;

public class SystemAdmin extends User {

    @Override
    protected List<Permission> getAllowedActions() {
        List<Permission> allowedActions =  new ArrayList<Permission>();
        allowedActions.add(Permission.CAN_SEE_BOOKING_HISTORY);
        return allowedActions;
    }

}
