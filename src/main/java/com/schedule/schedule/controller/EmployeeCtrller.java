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
@RequestMapping("/employee")
//@CrossOrigin(origins = {"http://domain2.com", "url2", etc})
@CrossOrigin
public class EmployeeCtrller {

    @Autowired
    private EmployeeSvc employeeSvc;
    @Autowired
    private ShiftSvc shiftSvc;
    @Autowired
    private UnavailSvc unavailSvc;



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
