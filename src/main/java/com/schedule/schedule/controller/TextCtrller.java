package com.schedule.schedule.controller;

import com.schedule.schedule.model.Employee;
import com.schedule.schedule.model.Shift;
import com.schedule.schedule.model.Text;
import com.schedule.schedule.service.ShiftSvc;
import com.schedule.schedule.service.TextSvc;
import com.schedule.schedule.service.TwilioSvc;
import com.schedule.schedule.twilio.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping()
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin
public class TextCtrller {

    @Autowired
    private TwilioSvc twilioSvc;
    @Autowired
    private TextSvc textSvc;
    @Autowired
    private ShiftSvc shiftSvc;

    // When employees click on link that's inside their sms message...
    @GetMapping("/text/{uuid}")
    public Optional<Shift> uponEmployeeReply(@PathVariable String uuid) {
        // check to see if text obj in db related to this uuid still exist
        // if yes, then this employee can have the shift
        // if no, then some other employee got the shift, and that's why all text objs that references that shift obj got deleted

        Optional<Text> maybeText = textSvc.findByUuid(uuid);
        if (maybeText.isPresent()) {
            System.out.println("SHOWING "+maybeText.get().getClient().getName());
            // employee can get the shift info & confirmation button to display on front end
            return Optional.of(maybeText.get().getShift());
        } else {
            // 1. shift already booked that's why all related texts are deleted
            // 2. or url has bogus uuid
            return Optional.empty();
        }
    }

    // When shift is avail, and employee click on button 'yes I want this shift'...
    @PostMapping("/text/{uuid}")
    public Boolean empAcceptedShift(@PathVariable String uuid) {
        // find shift by uuid in text db
        Optional<Text> maybeText = textSvc.findByUuid(uuid);

        // race condition -> shift is no longer available
        if (!maybeText.isPresent()) return false;

        // add this employee to that shift
        Text foundText = maybeText.get();
        Employee employee = foundText.getEmployee();
        Shift shift = foundText.getShift();
        shiftSvc.addEmployeeToShift(employee, shift);

        // find all other data rows in texts db that share a shift-obj with this one, and delete them all
        textSvc.deleteAllTextsOfSameShift(shift);

        return true;
    }
}













