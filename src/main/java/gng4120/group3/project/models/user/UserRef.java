package gng4120.group3.project.models.user;

public class UserRef extends User {

    public UserRef(User user) {
        // You can include any other fields you want to expose
        this.setFirstname(user.getFirstname());
        this.setLastname(user.getLastname());
        this.setUsername(user.getUsername());
        this.setRoles(user.getRoles());
    }

    // You may include additional methods or fields specific to UserRef
}