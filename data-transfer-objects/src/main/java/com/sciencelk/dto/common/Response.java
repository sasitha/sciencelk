package com.sciencelk.dto.common;

/**
 * Created by SasithaG on 6/16/2015.
 */
public class Response {
    
    private ResponseType responseType;
    private String message;

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
