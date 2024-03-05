package gng4120.group3.project.models.user;

import java.io.Serializable;

public class UserRef extends User {

    public String test = "Hello World";

    public UserRef(User user) {
        // You can include any other fields you want to expose
        this.setFirstname(user.getFirstname());
        this.setLastname(user.getLastname());
        this.setEmail(user.getEmail());
        this.setUsername(user.getUsername());
        this.setRoles(user.getRoles());
    }

    // You may include additional methods or fields specific to UserRef
}