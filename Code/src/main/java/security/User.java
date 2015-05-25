package security;

import java.util.List;

abstract public class User {
    abstract protected List<Permission> getAllowedActions();
    
    public void assertCan(Permission permission) {
        if (!this.getAllowedActions().contains(permission)) {
            throw new InvalidUserException();
        }
    }
}
