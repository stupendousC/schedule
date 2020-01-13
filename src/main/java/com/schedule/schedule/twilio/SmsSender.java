package com.schedule.schedule.twilio;

import com.schedule.schedule.twilio.SmsRequest;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);

}
