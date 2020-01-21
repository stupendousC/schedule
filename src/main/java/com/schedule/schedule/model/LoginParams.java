package com.schedule.schedule.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginParams {
    private String googleId;
    private String googleAccessToken;
    private String uuid;

    public LoginParams (@JsonProperty("googleId") String googleId,
                        @JsonProperty("googleAccessToken") String googleAccessToken,
                        @JsonProperty("uuid") String uuid) {
        this.googleId = googleId;
        this.googleAccessToken = googleAccessToken;
        this.uuid = uuid;
    }

    // GETTERS ONLY
    public String getGoogleId() {
        return googleId;
    }

    public String getGoogleAccessToken() {
        return googleAccessToken;
    }

    public String getUuid() {
        return uuid;
    }
}


