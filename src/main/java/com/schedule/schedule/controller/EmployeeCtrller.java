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

    ////////////// CRUD employees //////////////

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable long id) {
        System.out.println("finding employee id="+id);
        return employeeSvc.getEmployeeById(id);
    }
    @PutMapping("/{id}")
    public Optional<Employee> updateEmployeeById(@PathVariable long id, @RequestBody Employee employee) {
        System.out.println("updating employee id="+id);
        return employeeSvc.updateEmployee(id, employee);
    }

    ////////////// end CRUD employees //////////////


    ////////////// CRUD unavails //////////////
    @GetMapping("/{id}/unavails")
    public Optional<List<Unavail>> getUnavailsByEmpId(@PathVariable long id) {
        return unavailSvc.getUnavailsByEmpId(id);
    }



    @PostMapping("/unavails")
    public Unavail postUnavail(@RequestBody Unavail unavail) {
        return unavailSvc.addNewUnavail(unavail);
    }
    @DeleteMapping("/unavails/{id}")
    public void deleteUnavail(@PathVariable long id) {
        unavailSvc.deleteUnavail(id);
    }
    ////////////// end CRUD unavails //////////////
}
