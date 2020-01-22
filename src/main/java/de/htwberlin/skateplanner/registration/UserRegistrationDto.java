package de.htwberlin.skateplanner.registration;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserRegistrationDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @Email
    @NotEmpty
    private String email;

    private boolean allowingNotifications;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(@NotEmpty String username, @NotEmpty String password, @NotEmpty String confirmPassword, @Email @NotEmpty String email, boolean allowingNotifications) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.allowingNotifications = allowingNotifications;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAllowingNotifications() {
        return allowingNotifications;
    }

    public void setAllowingNotifications(boolean allowingNotifications) {
        this.allowingNotifications = allowingNotifications;
    }
}
