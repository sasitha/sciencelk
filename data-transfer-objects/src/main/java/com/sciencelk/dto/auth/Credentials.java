package com.sciencelk.dto.auth;

import org.hibernate.validator.constraints.Length;

/**
 * *
 * Created by SasithaG on 3/27/2015.
 */
public class Credentials {
    @Length(max = 10, message = "Username must be less than 10 characters long")
    private String username;

    private String password;


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
}
