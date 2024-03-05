package gng4120.group3.project.models.user;

import gng4120.group3.project.models.Role;
import gng4120.group3.project.payload.request.SignupRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotBlank
    @Size(max = 120)
    private String firstname = null;

    @NotBlank
    @Size(max = 120)
    private String lastname = null;

    @NotBlank
    @Size(max = 120)
    private String username = null;

    @NotBlank
    @Size(max = 120)
    @Email
    private String email = null;

    @NotBlank
    @Size(max = 120)
    private String password = null;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(SignupRequest request) {
        this.firstname = request.getFirstname();
        this.lastname = request.getLastname();
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.username = request.getUsername();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
