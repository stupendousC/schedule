package com.schedule.schedule.controller;

import com.schedule.schedule.service.TwilioSvc;
import com.schedule.schedule.twilio.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("sendText")
public class TwilioCtrller {

    private final TwilioSvc twilioSvc;

    @Autowired
    public TwilioCtrller(TwilioSvc twilioSvc) {
        this.twilioSvc = twilioSvc;
    }

    @PostMapping
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        twilioSvc.sendSms(smsRequest);
    }
}
