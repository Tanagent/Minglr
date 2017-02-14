package com.minglr.android;

public class Date {

    private String location;
    private String time;
    private String date;
    private int profilePhotoId;
    private String userID;

    public Date() {}

    public Date(String location, String time, String date, int profilePhotoId, String userID) {
        this.location = location;
        this.time = time;
        this.date = date;
        this.profilePhotoId = profilePhotoId;
        this.userID = userID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public int getProfilePhotoId() {
        return profilePhotoId;
    }

    public void setProfilePhotoId(int profilePhotoId) {
        this.profilePhotoId = profilePhotoId;
    }

    public String getUserID() {return userID;}

    public void setUserID(String userID) {this.userID = userID;}
}
