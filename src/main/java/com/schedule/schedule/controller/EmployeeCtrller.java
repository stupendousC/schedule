package com.schedule.schedule.controller;

import com.schedule.schedule.model.*;
import com.schedule.schedule.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
//@CrossOrigin(origins = {"http://domain2.com", "url2", etc})
@CrossOrigin
public class EmployeeCtrller {

    @Autowired
    private EmployeeSvc employeeSvc;
    @Autowired
    private ShiftSvc shiftSvc;
    @Autowired
    private UnavailSvc unavailSvc;

    // Not all CRUD actions are available to Employee due to business needs

    ////////////// CRUD employees //////////////
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable long id) {
        return employeeSvc.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Optional<Employee> updateEmployeeById(@PathVariable long id, @RequestBody Employee employee) {
        System.out.println("updating employee id="+id);
        return employeeSvc.updateEmployee(id, employee);
    }

    ////////////// CRUD unavails //////////////
    @GetMapping("/{id}/unavails")
    public Optional<List<Unavail>> getUnavailsByEmpId(@PathVariable long id) {
        return unavailSvc.getUnavailsByEmpId(id);
    }

    @PostMapping("/{id}/unavails")
    public Unavail postUnavail(@RequestBody Unavail unavail) {
        return unavailSvc.addNewUnavail(unavail);
    }
    @DeleteMapping("/{id}/unavails/{availId}")
    public Optional<List<Unavail>> deleteUnavail(@PathVariable long id, @PathVariable long availId) {
        unavailSvc.deleteUnavail(availId);
        // also return the most current version employee's unavail rows
        return unavailSvc.getUnavailsByEmpId(id);
    }

    ////////////// CRUD shifts //////////////
    @GetMapping("/{id}/shifts")
    public Optional<List<Shift>> getAllShifts(@PathVariable long id) {
        return shiftSvc.getShiftByEmpId(id);
    }

}
