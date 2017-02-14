package com.minglr.android;

/**
 * Created by Brian on 2/12/17.
 */

public class DateObject {
    private String ownerId;
    private String location;
    private String date;
    private String time;


    public DateObject() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public DateObject(String personId, String location, String date, String time) {
        this.ownerId = personId;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String location) {this.date = date;}

    public void setTime(String time) {this.time = time;}

    public String getOwnerId() {
        return ownerId;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
