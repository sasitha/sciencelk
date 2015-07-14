package com.sciencelk.dto.auth;


import com.sciencelk.dto.common.ResponseType;

/**
 * *
 * Created by SasithaG on 3/27/2015.
 */
public class AuthResponse {

    private String authToken;
    private String username;
    private String authResult;
    private ResponseType status;

    public ResponseType getStatus() {
        return status;
    }

    public void setStatus(ResponseType status) {
        this.status = status;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthResult() {
        return authResult;
    }

    public void setAuthResult(String authResult) {
        this.authResult = authResult;
    }
}
