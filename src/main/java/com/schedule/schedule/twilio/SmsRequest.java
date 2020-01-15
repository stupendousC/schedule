package com.schedule.schedule.twilio;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class SmsRequest {

    @NotBlank
    private final String phoneNumber;       // destination
    @NotBlank
    private final String message;

    public SmsRequest(String phoneNumber, String message) {
        // acceptable formats for destination phoneNumber = "14251112222" or "4251112222" or "425-111-2222" or "1-425-111-2222" or "(425)111-2222"
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}


// for SAFEKEEPING, just in case
// getting the info out of json is now being done by TextSmsCombo model
//    public SmsRequest(@JsonProperty("phoneNumber") String phoneNumber,
//                      @JsonProperty("message") String message) {
//        // acceptable formats for destination phoneNumber = "14251112222" or "4251112222" or "425-111-2222" or "1-425-111-2222" or "(425)111-2222"
//        this.phoneNumber = phoneNumber;
//        this.message = message;
//    }
