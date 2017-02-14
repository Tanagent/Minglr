package com.minglr.android;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anthony on 2/11/17.
 */

public class User {
    public String email;
    public String firstname;
    public String lastname;
    public String birthday;
    public String city;
    public String state;
    public String description;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }



    public User(String email, String firstname, String lastname, String birthday, String city,
                String state) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.city = city;
        this.state = state;
        this.description = "";
    }

    public User(String email, String firstname, String lastname, String birthday, String city,
                String state, String description) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.city = city;
        this.state = state;
        this.description = "";
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("firstname", firstname);
        result.put("lastname", lastname);
        result.put("birthday", birthday);
        result.put("city", city);
        result.put("state", state);
        result.put("description", description);
        return result;
    }
}
