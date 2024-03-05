package gng4120.group3.project.payload.request;

import jakarta.validation.constraints.NotBlank;

public class SigninRequest {
    @NotBlank
    private String emailUser;
    @NotBlank
    private String password;

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
