package com.sciencelk.portal.entities;

public class SessionStatus {
    public static final String EXPIRED = "expired";
    public static final String ACTIVE = "active";
    public static final String NOSESSION = "nosession";


    private String sessionStatus;

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}
