package com.schedule.schedule.controller;

import com.schedule.schedule.model.*;
import com.schedule.schedule.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
//@CrossOrigin(origins = {"http://domain2.com", "url2", etc})
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

    ////////////// CRUD admins //////////////
    @GetMapping("/admins")
    public List<Admin> getAllAdmins(Model admin) {
        System.out.println("\nAdminCtrller -> AdminSvc, GET /admin/admins for a list of all ACTIVE admin personnel");

//        System.out.println("RECEIVED PATH VARIABLES: googleId = " + googleId + " ROLE = " + authenticatedRole);

        return adminSvc.findAllActives();
    }

    @PostMapping("/admins")
    public Admin postAdmin(@RequestBody Admin admin) {
        System.out.println("\nAdminCtrller -> AdminSvc, POST /admin/admins to add new admin " + admin.getName());
        return adminSvc.addNewAdmin(admin);
    }

    @GetMapping("/admins/{id}")
    public Optional<Admin> getAdminById(@PathVariable long id) {
        return adminSvc.getAdminById(id);
    }

    @PutMapping("/admins/{id}")
    public Optional<Admin> updateAdminById(@PathVariable long id, @RequestBody Admin admin) {
        return adminSvc.updateAdmin(id, admin);
    }

    @DeleteMapping("/admins/{id}")
    // does NOT actually delete person from table, instead it switches active column from true to false
    public void deleteAdmin(@PathVariable long id) {
        adminSvc.deleteAdmin(id);
    }
    ////////////// end CRUD admins //////////////


    ////////////// CRUD employees //////////////
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeSvc.findAllActives();
    }

    @PostMapping("/employees")
    public Employee postEmployee(@RequestBody Employee employee) {
        return employeeSvc.addNewEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable long id) {
        return employeeSvc.getEmployeeById(id);
    }

    @PutMapping("/employees/{id}")
    public Optional<Employee> updateEmployeeById(@PathVariable long id, @RequestBody Employee employee) {
        return employeeSvc.updateEmployee(id, employee);
    }

    @DeleteMapping("/employees/{id}")
    // does NOT actually delete person from table, instead it switches active column from true to false
    public void deleteEmployee(@PathVariable long id) {
        employeeSvc.deleteEmployee(id);
    }
    ////////////// end CRUD employees //////////////

    ////////////// CRUD clients //////////////
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientSvc.findAllActives();
    }

    @PostMapping("/clients")
    public Client postClient(@RequestBody Client client) {
        return clientSvc.addNewClient(client);
    }

    @GetMapping("/clients/{id}")
    public Optional<Client> getClientById(@PathVariable long id) {
        return clientSvc.getClientById(id);
    }

    @PutMapping("/clients/{id}")
    public Optional<Client> updateClientById(@PathVariable long id, @RequestBody Client client) {
        return clientSvc.updateClient(id, client);
    }

    @DeleteMapping("/clients/{id}")
    // does NOT actually delete person from table, instead it switches active column from true to false
    public void deleteClient(@PathVariable long id) {
        clientSvc.deleteClient(id);
    }
    ////////////// end CRUD clients //////////////

    ////////////// CRUD shifts //////////////
    @GetMapping("/shifts")
    public List<Shift> getAllShifts() {
        return shiftSvc.findAll();
    }

    @PostMapping("/shifts")
    public Shift postShift(@RequestBody Shift shift) {
        return shiftSvc.addNewShift(shift);
    }

    @GetMapping("/shifts/{id}")
    public Optional<Shift> getShiftById(@PathVariable long id) {
        return shiftSvc.getShiftById(id);
    }

    @PutMapping("/shifts/{id}")
    public Optional<Shift> updateShiftById(@PathVariable long id, @RequestBody Shift shift) {
        return shiftSvc.updateShift(id, shift);
    }

    @DeleteMapping("/shifts/{id}")
    public void deleteShift(@PathVariable long id) {
        shiftSvc.deleteShift(id);
    }

    @GetMapping("/shifts/availableEmployees/{date}")
    public String getAvailEmployees(@PathVariable Date date) {
        System.out.println("AdminCTRLLER received " + date);

        return "WORKIGN ON IT!!!";
    }
    ////////////// end CRUD shifts //////////////

    ////////////// CRUD unavails //////////////
    @GetMapping("/unavails")
    public List<Unavail> getAllUnavails() {
        return unavailSvc.findAll();
    }

    @PostMapping("/unavails")
    public Unavail postUnavail(@RequestBody Unavail unavail) {
        return unavailSvc.addNewUnavail(unavail);
    }

    @GetMapping("/unavails/{id}")
    public Optional<Unavail> getUnavailById(@PathVariable long id) {
        return unavailSvc.getUnavailById(id);
    }

    @DeleteMapping("/unavails/{id}")
    public void deleteUnavail(@PathVariable long id) {
        unavailSvc.deleteUnavail(id);
    }
    ////////////// end CRUD unavails //////////////
}
