package com.sciencelk.dto.auth;

import java.util.Date;

/**
 * *
 * Created by SasithaG on 3/27/2015.
 */
public class Token {
    private String username;
    private Date expiryTime;
    private int id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
