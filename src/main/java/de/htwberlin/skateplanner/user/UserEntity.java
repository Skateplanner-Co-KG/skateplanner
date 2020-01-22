package de.htwberlin.skateplanner.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

    @Column(unique = true)
    private String email;
    @Id
    private String username;
    private String password;
    private String role;
    @Column(name = "notify")
    private boolean receivingNotifications;

    protected UserEntity() {
    }

    public UserEntity(String email, String username, String password, String role, boolean receivingNotifications) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.receivingNotifications = receivingNotifications;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean isReceivingNotifications() {
        return receivingNotifications;
    }
}
