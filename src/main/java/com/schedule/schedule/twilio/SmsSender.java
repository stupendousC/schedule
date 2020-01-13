package com.schedule.schedule.twilio;

import com.schedule.schedule.twilio.SmsRequest;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);

}


// why bother with this?  In case of diff text sending providers, maybe a twilio competitor or soemthing.
