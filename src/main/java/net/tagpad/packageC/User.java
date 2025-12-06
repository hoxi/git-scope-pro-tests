package net.tagpad.packageC;

import java.time.LocalDateTime;

public class User {
    private String id;
    private String username;
    private String email;
    private boolean active;
    private LocalDateTime createdAt;
    private String phoneNumber;

    public User(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{id='" + id + "', username='" + username + "', email='" + email +
               "', active=" + active + ", createdAt=" + createdAt + "}";
    }
}
