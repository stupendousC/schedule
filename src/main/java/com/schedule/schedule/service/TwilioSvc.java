package com.schedule.schedule.service;


import com.schedule.schedule.twilio.SmsRequest;
import com.schedule.schedule.twilio.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//@org.springframework.stereotype.Service       // IDK why this long thing is needed, but tha'ts what amigoscode tutorial did
@Service        // changed to this, still works
public class TwilioSvc {

    @Autowired
    private final SmsSender smsSender;

//    @Autowired      // I moved this up by SmsSender, feels like it makes more sense there.
//    IDK why I can't do this iwith TwilioInitializer.java and TwilioSmsSender.java, maybe its' cuz they're dealing with TwilioConfig??
    public TwilioSvc(@Qualifier("twilio") SmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }
}
