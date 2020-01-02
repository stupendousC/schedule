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



    @Autowired
    private EmployeeSvc employeeSvc;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        System.out.println("\nEmployeeCtrller -> EmployeeSvc, going to /admin/employees for a list of all Employee");
        return employeeSvc.findAll();
    }

//    @GetMapping("/employees/{id}")
//    public Optional<Employee> getEmployeeById(@PathVariable int id) {
//        return employeeSvc.getEmployeeById(id);
//    }
////
//
//    @PostMapping("/employees")
//    public void addNewEmployee(@RequestBody LinkedHashMap<String, String> payload) {
//        //payload is a LinkedHashMap object
//        employeeSvc.addNewEmployee(payload);
//    }
//
//    @DeleteMapping("/employees/{id}")
//    public void deleteEmployee(@PathVariable int id) {
//        employeeSvc.deleteEmployee(id);
//    }
//
//    @PutMapping("/employees/{id}")
//    public void updateEmployee(@PathVariable int id, @RequestBody LinkedHashMap<String, String> payload) {
//        employeeSvc.updateEmployee(id, payload);
//    }


}


//      RETIRING
//    private EmployeeSvc employeeSvc;
//
//    @Autowired
//    public AdminCtrller(EmployeeSvc employeeSvc) {
//        this.employeeSvc = employeeSvc;
//    }