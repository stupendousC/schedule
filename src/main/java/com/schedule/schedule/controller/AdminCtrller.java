package com.schedule.schedule.controller;

import com.schedule.schedule.model.Admin;
import com.schedule.schedule.model.Client;
import com.schedule.schedule.model.Employee;
import com.schedule.schedule.model.Shift;
import com.schedule.schedule.service.AdminSvc;
import com.schedule.schedule.service.ClientSvc;
import com.schedule.schedule.service.EmployeeSvc;
import com.schedule.schedule.service.ShiftSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    ////////////// CRUD admins //////////////
    @GetMapping("/admins")
    public List<Admin> getAllAdmins(Model admin) {
        System.out.println("\nAdminCtrller -> AdminSvc, GET /admin/admins for a list of all admin personnel");
        return adminSvc.findAll();
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
    public void deleteAdmin(@PathVariable long id) {
        adminSvc.deleteAdmin(id);
    }
    ////////////// end CRUD admins //////////////


    ////////////// CRUD employees //////////////
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeSvc.findAll();
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
    public void deleteEmployee(@PathVariable long id) {
        employeeSvc.deleteEmployee(id);
    }
    ////////////// end CRUD employees //////////////

    ////////////// CRUD clients //////////////
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientSvc.findAll();
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
    ////////////// end CRUD shifts //////////////
}

