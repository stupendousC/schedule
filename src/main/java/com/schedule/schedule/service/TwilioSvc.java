package com.schedule.schedule.service;


import com.schedule.schedule.twilio.SmsRequest;
import com.schedule.schedule.twilio.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class TwilioSvc {

    private final SmsSender smsSender;

    @Autowired
    public TwilioSvc(@Qualifier("twilio") SmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }
}
