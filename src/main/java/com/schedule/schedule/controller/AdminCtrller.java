package com.schedule.schedule.controller;

import com.schedule.schedule.model.*;
import com.schedule.schedule.service.*;
import com.schedule.schedule.twilio.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin
public class AdminCtrller {

    @Autowired
    private AdminSvc adminSvc;
    @Autowired
    private EmployeeSvc employeeSvc;
    @Autowired
    private ClientSvc clientSvc;
    @Autowired
    private ShiftSvc shiftSvc;
    @Autowired
    private UnavailSvc unavailSvc;
    @Autowired
    private TwilioSvc twilioSvc;
    @Autowired
    private TextSvc textSvc;

    // RUN THIS BEFORE EVERY API ENDPOINT
    public boolean verifyActiveAdmin(HttpHeaders headers) {
        if (headers.get("googleId") == null) return false;
        String googleId = headers.get("googleId").get(0);
        Optional <Admin> foundActiveAdmin = adminSvc.findByGoogleId(googleId);
        return (foundActiveAdmin.isPresent() ? true : false);
    }

    ////////////// CRUD admins //////////////
    @GetMapping("/admins")
    public List<Admin> getAllAdmins(Model admin, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAdmin(headers)) {
            System.out.println("YES YOU're AN ADMIN!");
            return adminSvc.findAllActives();
        }
        return new ArrayList<Admin>();
    }

    @PostMapping("/admins")
    public Admin postAdmin(@RequestBody Admin admin, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAdmin(headers)) {
            System.out.println("YES YOU're AN ADMIN!");
            return adminSvc.addNewAdmin(admin);
        }
        return new Admin();
    }

    @GetMapping("/admins/{id}")
    public Optional<Admin> getAdminById(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        return adminSvc.getAdminById(id);
    }

    @PutMapping("/admins/{id}")
    public Optional<Admin> updateAdminById(@PathVariable long id, @RequestBody Admin admin, @RequestHeader HttpHeaders headers) {
        return adminSvc.updateAdmin(id, admin);
    }

    @DeleteMapping("/admins/{id}")
    // does NOT actually delete person from table, instead it switches active column from true to false
    public void deleteAdmin(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        adminSvc.deleteAdmin(id);
    }
    ////////////// end CRUD admins //////////////


    ////////////// CRUD employees //////////////
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(@RequestHeader HttpHeaders headers) {
        return employeeSvc.findAllActives();
    }

    @PostMapping("/employees")
    public Employee postEmployee(@RequestBody Employee employee, @RequestHeader HttpHeaders headers) {
        return employeeSvc.addNewEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        return employeeSvc.getEmployeeById(id);
    }

    @PutMapping("/employees/{id}")
    public Optional<Employee> updateEmployeeById(@PathVariable long id, @RequestBody Employee employee, @RequestHeader HttpHeaders headers) {
        return employeeSvc.updateEmployee(id, employee);
    }

    @DeleteMapping("/employees/{id}")
    // does NOT actually delete person from table, instead it switches active column from true to false
    public void deleteEmployee(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        employeeSvc.deleteEmployee(id);
    }
    ////////////// end CRUD employees //////////////

    ////////////// CRUD clients //////////////
    @GetMapping("/clients")
    public List<Client> getAllClients (@RequestHeader HttpHeaders headers) {
        return clientSvc.findAllActives();
    }

    @PostMapping("/clients")
    public Client postClient(@RequestBody Client client, @RequestHeader HttpHeaders headers) {
        return clientSvc.addNewClient(client);
    }

    @GetMapping("/clients/{id}")
    public Optional<Client> getClientById(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        return clientSvc.getClientById(id);
    }

    @PutMapping("/clients/{id}")
    public Optional<Client> updateClientById(@PathVariable long id, @RequestBody Client client, @RequestHeader HttpHeaders headers) {
        return clientSvc.updateClient(id, client);
    }

    @DeleteMapping("/clients/{id}")
    // does NOT actually delete person from table, instead it switches active column from true to false
    public void deleteClient(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        clientSvc.deleteClient(id);
    }
    ////////////// end CRUD clients //////////////

    ////////////// CRUD shifts //////////////
    // As the company grows, should probably archive worked shifts in the past somewhere else?
    @GetMapping("/shifts")
    public List<Shift> getAllShifts(@RequestHeader HttpHeaders headers) {
        return shiftSvc.findAll();
    }

    @PostMapping("/shifts")
    public Shift postShift( @RequestBody Shift shift, @RequestHeader HttpHeaders headers) {
        return shiftSvc.addNewShift(shift);
    }

    @GetMapping("/shifts/{id}")
    public Optional<Shift> getShiftById(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        return shiftSvc.getShiftById(id);
    }

    @PutMapping("/shifts/{id}")
    public Optional<Shift> updateShiftById(@PathVariable long id, @RequestBody Shift shift, @RequestHeader HttpHeaders headers) {
        return shiftSvc.updateShift(id, shift);
    }

    @DeleteMapping("/shifts/{id}")
    public void deleteShift(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        shiftSvc.deleteShift(id);
    }

    @GetMapping("/shifts/availableEmployees/{shiftId}")
    public List<Employee> getAvailEmployeesByShift(@PathVariable long shiftId, @RequestHeader HttpHeaders headers) {
//        System.out.println("AdminCTRLLER rec'd request for available Emps for shift obj id#" + shiftId);
        return employeeSvc.getAvailEmployeesByShift(shiftId);
    }

    @GetMapping("/employees/availableEmployees/{dateStr}")
    public List<Employee> getAvailEmployeesByDate(@PathVariable String dateStr, @RequestHeader HttpHeaders headers) {
        LocalDate date = LocalDate.parse(dateStr);
//        System.out.println("AdminCTRLLER rec'd request for available Emps for date =" + date);
        return employeeSvc.getAvailEmployeesByDate(date);
    }

    ////////////// end CRUD shifts //////////////

    ////////////// CRUD unavails //////////////
    @GetMapping("/unavails")
    public List<Unavail> getAllUnavails(@RequestHeader HttpHeaders headers) {
        return unavailSvc.findAll();
    }

    // admin will need to specify employee otherwise Unavail table won't accept
    @PostMapping("/unavails")
    public Unavail postUnavail(@RequestBody Unavail unavail, @RequestHeader HttpHeaders headers) {
        return unavailSvc.addNewUnavail(unavail);
    }

    @GetMapping("/unavails/{id}")
    public Optional<Unavail> getUnavailById(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        return unavailSvc.getUnavailById(id);
    }

    @DeleteMapping("/unavails/{id}")
    public void deleteUnavail(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        unavailSvc.deleteUnavail(id);
    }
    ////////////// end CRUD unavails //////////////



    ////////////// CRUD texts //////////////
    @PostMapping("/sendText")
    public String saveAndSendText(@RequestBody TextSmsCombo textSmsCombo, @RequestHeader HttpHeaders headers) {

//        System.out.println("\n\nAdminCtrller received megaCombo of...");
//        System.out.println("phoneNum " + textSmsCombo.getPhoneNumber());
//        System.out.println("msg = " + textSmsCombo.getMessage());
//        System.out.println("uuid = " + textSmsCombo.getUuid());
//        System.out.println("client =" + textSmsCombo.getClient().getName());
//        System.out.println("employee =" + textSmsCombo.getEmployee().getName());

        // Separate the received data into a Text object and a SmsRequest object
        Text text = new Text(textSmsCombo.getUuid(), textSmsCombo.getEmployee(), textSmsCombo.getClient(), textSmsCombo.getShift());
        SmsRequest smsRequest = new SmsRequest(textSmsCombo.getPhoneNumber(), textSmsCombo.getMessage());

        // first add the new data to texts table in db
        textSvc.addNewText(text);

        // then send text out, which will refer to the id of the text obj in the link url
        twilioSvc.sendSms(smsRequest);
        return "Text sent to " + smsRequest.getPhoneNumber() + "\nMessage = " + smsRequest.getMessage();
    }
}



/// FOR SAFEKEEPING, just in case
//    @PostMapping("/sendText")
//    public String sendSms(@Valid @RequestBody SmsRequest smsRequest) {
//
//        System.out.println("\n\nadminCTRL received via /sendText..." + smsRequest.getPhoneNumber() + smsRequest.getMessage());
//
//        // first add the new data to texts table in db
//        // how?
//
//        // then send text out, which will refer to the id of the text obj in the link url
//        twilioSvc.sendSms(smsRequest);
//        return "Text sent to " + smsRequest.getPhoneNumber() + "\nMessage = " + smsRequest.getMessage();
//    }

