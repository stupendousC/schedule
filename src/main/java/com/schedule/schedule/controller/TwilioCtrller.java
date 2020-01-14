package com.schedule.schedule.controller;

import com.schedule.schedule.service.TwilioSvc;
import com.schedule.schedule.twilio.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("sendText")
@CrossOrigin(origins = "http://localhost:3000")
public class TwilioCtrller {

    @Autowired
    private final TwilioSvc twilioSvc;

    //    @Autowired      // I moved this up by TwilioSvc, feels like it makes more sense there.  I think as long as I don't mess with Autowired on TwilioConfig it's ok
    public TwilioCtrller(TwilioSvc twilioSvc) {
        this.twilioSvc = twilioSvc;
    }

    @PostMapping
    public String sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        twilioSvc.sendSms(smsRequest);
        return "Text sent to " + smsRequest.getPhoneNumber() + "\nMessage = " + smsRequest.getMessage();
    }

    @PostMapping("/getReply")
    public String receiveSms () {
//        https://www.twilio.com/docs/sms/tutorials/how-to-receive-and-reply-java
        return "NEED TO DEPLOY bc they won't accept my localhost";
    }
}
