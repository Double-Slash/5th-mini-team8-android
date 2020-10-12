package com.example.doubleslash.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String id;
    private String password;
    private String accessToken;

    public LoggedInUser(){

    }

    public LoggedInUser(String id, String password) {
        this.password = password;
        this.id = id;
    }

    /////////
    //싱글톤 패턴으로 구현
    private static class userHolder {
        public static final LoggedInUser Instance = new LoggedInUser();
    }

    public static LoggedInUser getLoggedInUser(){
        return userHolder.Instance;
    }
    /////////


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    public String getUserId(){return id;}

    public String getPassword() {
        return password;
    }
}