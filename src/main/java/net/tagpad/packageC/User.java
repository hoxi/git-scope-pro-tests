package net.tagpad.packageC;

public class User {
    private String id;
    private String username;
    private String email;
    private boolean active;

    public User(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.active = true;
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

    @Override
    public String toString() {
        return "User{id='" + id + "', username='" + username + "', email='" + email + "', active=" + active + "}";
    }
}
