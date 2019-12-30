package com.schedule.schedule.controller;

import com.schedule.schedule.model.Employee;
import com.schedule.schedule.service.EmployeeSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminCtrller {

    private EmployeeSvc employeeSvc;

    @Autowired
    public AdminCtrller(EmployeeSvc employeeSvc) {
        this.employeeSvc = employeeSvc;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        // TODO: get from actual dababase!
        return employeeSvc.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable int id) {
        return employeeSvc.getEmployeeById(id);
    }


    @PostMapping("/employees")
    public void addNewEmployee(@RequestBody LinkedHashMap<String, String> payload) {
        //payload is a LinkedHashMap object
        employeeSvc.addNewEmployee(payload);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeSvc.deleteEmployee(id);
    }

    @PutMapping("/employees/{id}")
    public void updateEmployee(@PathVariable int id, @RequestBody LinkedHashMap<String, String> payload) {
        employeeSvc.updateEmployee(id, payload);
    }


//    public List<Shift> getAllShifts() {
//
//    }

}
