package com.example.doubleslash.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String password;

    public LoggedInUser(String userId, String displayName) {
        this.password = password;
        this.userId = userId;
    }

    public String getUserId(){return userId;}

    public String getPassword() {
        return password;
    }
}