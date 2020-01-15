package com.schedule.schedule.controller;

import com.schedule.schedule.service.TwilioSvc;
import com.schedule.schedule.twilio.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping()
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin
public class TextCtrller {

    @Autowired
    private final TwilioSvc twilioSvc;

    //    @Autowired      // I moved this up by TwilioSvc, feels like it makes more sense there.  I think as long as I don't mess with Autowired on TwilioConfig it's ok
    public TextCtrller(TwilioSvc twilioSvc) {
        this.twilioSvc = twilioSvc;
    }

    @PostMapping("/sendText")
    public String sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        twilioSvc.sendSms(smsRequest);
        return "Text sent to " + smsRequest.getPhoneNumber() + "\nMessage = " + smsRequest.getMessage();
    }






    // When employees click on link that's inside their sms message...
    @GetMapping("/text/{uuid}")
    public String uponEmployeeReply(@PathVariable String uuid) {
        // check to see if text obj in db related to this uuid still exist
            // if yes, then this employee can have the shift
            // if no, then some other employee got the shift, and that's why all text objs that references that shift obj got deleted


        return "EMP sees: 1. confirmation screen, or 2. Shift taken";
    }

    // If employee is first to click on confirmation to take the shift...
    @PostMapping("/sendText/{uuid}")
    public String empAcceptedShift(@PathVariable long uuid) {

        // add this employee to that shift

        // find all other data rows in texts db that share a shift-obj with this one, and delete them all

        return "EMPLOYEE is accetping the shift.  working on it!!";
    }
}













