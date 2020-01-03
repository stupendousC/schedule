package com.schedule.schedule.controller;

import com.schedule.schedule.model.Admin;
import com.schedule.schedule.model.Employee;
import com.schedule.schedule.service.AdminSvc;
import com.schedule.schedule.service.EmployeeSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
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
        System.out.println("\nEmployeeCtrller -> EmployeeSvc, going to /admin/employees for a list of all Employee");
        return employeeSvc.findAll();
    }

    @PostMapping("/employees")
    public Employee postEmployee(@RequestBody Employee employee) {
        System.out.println("\nEmployeeCtrller -> EmployeeSvc, POST /admin/employees to add new employee " + employee.getName());
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
}

