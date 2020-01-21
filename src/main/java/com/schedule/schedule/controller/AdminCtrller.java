package com.schedule.schedule.controller;

import com.schedule.schedule.model.*;
import com.schedule.schedule.service.*;
import com.schedule.schedule.twilio.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    ////////////// RUN THIS BEFORE EVERY API ENDPOINT //////////////
    public boolean verifyActiveAdmin(HttpHeaders headers) {
        if (headers.get("googleId") == null) {
            return false;
        } else {
            String googleId = headers.get("googleId").get(0);
            Optional <Admin> foundActiveAdmin = adminSvc.findByGoogleId(googleId);
            return (foundActiveAdmin.isPresent());
        }
    }

    ////////////// CRUD admins //////////////
    @GetMapping("/admins")
    public List<Admin> getAllAdmins(Model admin, @RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return adminSvc.findAllActives();
        return new ArrayList<>();
    }

    @PostMapping("/admins")
    public Admin postAdmin(@RequestBody Admin admin, @RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return adminSvc.addNewAdmin(admin);
        return new Admin();
    }

    @GetMapping("/admins/{id}")
    public Optional<Admin> getAdminById(@PathVariable long id, @RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return adminSvc.getAdminById(id);
        return Optional.empty();
    }

    @PutMapping("/admins/{id}")
    public Optional<Admin> updateAdminById(@PathVariable long id, @RequestBody Admin admin, @RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return adminSvc.updateAdmin(id, admin);
        return Optional.empty();
    }

    @DeleteMapping("/admins/{id}")
    // does NOT actually delete person from table, instead it switches active column from true to false
    public void deleteAdmin(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAdmin(headers)) adminSvc.deleteAdmin(id);
    }



    ////////////// CRUD employees //////////////
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(@RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return employeeSvc.findAllActives();
        return new ArrayList<>();
    }

    @PostMapping("/employees")
    public Employee postEmployee(@RequestBody Employee employee, @RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return employeeSvc.addNewEmployee(employee);
        return new Employee();
    }

    @GetMapping("/employees/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable long id, @RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return employeeSvc.getEmployeeById(id);
        return Optional.empty();
    }

    @PutMapping("/employees/{id}")
    public Optional<Employee> updateEmployeeById(@PathVariable long id, @RequestBody Employee employee, @RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return employeeSvc.updateEmployee(id, employee);
        return Optional.empty();
    }

    @DeleteMapping("/employees/{id}")
    // does NOT actually delete person from table, instead it switches active column from true to false
    public void deleteEmployee(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAdmin(headers)) employeeSvc.deleteEmployee(id);
    }


    ////////////// CRUD clients //////////////
    @GetMapping("/clients")
    public List<Client> getAllClients (@RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return clientSvc.findAllActives();
        return new ArrayList<>();
    }

    @PostMapping("/clients")
    public Client postClient(@RequestBody Client client, @RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return clientSvc.addNewClient(client);
        return new Client();
    }

    @GetMapping("/clients/{id}")
    public Optional<Client> getClientById(@PathVariable long id, @RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return clientSvc.getClientById(id);
        return Optional.empty();
    }

    @PutMapping("/clients/{id}")
    public Optional<Client> updateClientById(@PathVariable long id, @RequestBody Client client, @RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return clientSvc.updateClient(id, client);
        return Optional.empty();
    }

    @DeleteMapping("/clients/{id}")
    // does NOT actually delete person from table, instead it switches active column from true to false
    public void deleteClient(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAdmin(headers)) clientSvc.deleteClient(id);
    }



    ////////////// CRUD shifts //////////////
    // As the company grows, should probably archive worked shifts in the past somewhere else?
    @GetMapping("/shifts")
    public List<Shift> getAllShifts(@RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return shiftSvc.findAll();
        return new ArrayList<>();
    }

    @PostMapping("/shifts")
    public Shift postShift( @RequestBody Shift shift, @RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return shiftSvc.addNewShift(shift);
        return new Shift();
    }

    @GetMapping("/shifts/{id}")
    public Optional<Shift> getShiftById(@PathVariable long id, @RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return shiftSvc.getShiftById(id);
        return Optional.empty();
    }

    @PutMapping("/shifts/{id}")
    public Optional<Shift> updateShiftById(@PathVariable long id, @RequestBody Shift shift, @RequestHeader HttpHeaders headers) {

        if (verifyActiveAdmin(headers)) return shiftSvc.updateShift(id, shift);
        return Optional.empty();
    }

    @DeleteMapping("/shifts/{id}")
    public void deleteShift(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAdmin(headers)) shiftSvc.deleteShift(id);
    }

    @GetMapping("/shifts/availableEmployees/{shiftId}")
    public List<Employee> getAvailEmployeesByShift(@PathVariable long shiftId, @RequestHeader HttpHeaders headers) {
//        System.out.println("AdminCTRLLER rec'd request for available Emps for shift obj id#" + shiftId);
        if (verifyActiveAdmin(headers)) return employeeSvc.getAvailEmployeesByShift(shiftId);
        return new ArrayList<>();
    }

    @GetMapping("/employees/availableEmployees/{dateStr}")
    public List<Employee> getAvailEmployeesByDate(@PathVariable String dateStr, @RequestHeader HttpHeaders headers) {
        LocalDate date = LocalDate.parse(dateStr);
//        System.out.println("AdminCTRLLER rec'd request for available Emps for date =" + date);
        if (verifyActiveAdmin(headers)) return employeeSvc.getAvailEmployeesByDate(date);
        return new ArrayList<>();
    }


    ////////////// CRUD unavails //////////////
    @GetMapping("/unavails")
    public List<Unavail> getAllUnavails(@RequestHeader HttpHeaders headers) {
        if (verifyActiveAdmin(headers)) return unavailSvc.findAll();
        return new ArrayList<>();
    }

    // admin will need to specify employee otherwise Unavail table won't accept
    @PostMapping("/unavails")
    public Unavail postUnavail(@RequestBody Unavail unavail, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAdmin(headers)) return unavailSvc.addNewUnavail(unavail);
        return new Unavail();
    }

    @GetMapping("/unavails/{id}")
    public Optional<Unavail> getUnavailById(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAdmin(headers)) return unavailSvc.getUnavailById(id);
        return Optional.empty();
    }

    @DeleteMapping("/unavails/{id}")
    public void deleteUnavail(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAdmin(headers)) unavailSvc.deleteUnavail(id);
    }



    ////////////// CRUD texts //////////////
    @PostMapping("/sendText")
    public String saveAndSendText(@RequestBody TextSmsCombo textSmsCombo, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAdmin(headers)) {
            // Separate the received data into a Text object and a SmsRequest object
            Text text = new Text(textSmsCombo.getUuid(), textSmsCombo.getEmployee(), textSmsCombo.getClient(), textSmsCombo.getShift());
            SmsRequest smsRequest = new SmsRequest(textSmsCombo.getPhoneNumber(), textSmsCombo.getMessage());

            // first add the new data to texts table in db
            textSvc.addNewText(text);

            // then send text out, which will refer to the id of the text obj in the link url
            twilioSvc.sendSms(smsRequest);
            return "Text sent to " + smsRequest.getPhoneNumber() + "\nMessage = " + smsRequest.getMessage();

        } else {
            return "NOT AUTHORIZED";
        }
    }
}
