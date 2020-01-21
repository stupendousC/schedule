package com.schedule.schedule.controller;

import com.schedule.schedule.model.*;
import com.schedule.schedule.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin
public class EmployeeCtrller {

    @Autowired
    private EmployeeSvc employeeSvc;
    @Autowired
    private ShiftSvc shiftSvc;
    @Autowired
    private UnavailSvc unavailSvc;

    ////////////// RUN THIS BEFORE EVERY API ENDPOINT //////////////
    public boolean verifyActiveAndCorrectEmployee(long id, HttpHeaders headers) {
        if (headers.get("googleId") == null) {
            return false;
        } else {
            String googleId = headers.get("googleId").get(0);
            Optional <Employee> foundActiveEmployeeOpt = employeeSvc.findByGoogleId(googleId);
            if (foundActiveEmployeeOpt.isPresent()) {
                Employee foundActiveEmployee = foundActiveEmployeeOpt.get();
                return foundActiveEmployee.getOauthid().equals(googleId);
            }
        }
        return false;
    }

    // Not all CRUD actions are available to Employee due to business needs

    ////////////// CRUD employees //////////////
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAndCorrectEmployee(id, headers)) return employeeSvc.getEmployeeById(id);
        return Optional.empty();
    }

    @PutMapping("/{id}")
    public Optional<Employee> updateEmployeeById(@PathVariable long id, @RequestBody Employee employee, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAndCorrectEmployee(id, headers)) return employeeSvc.updateEmployee(id, employee);
        return Optional.empty();
    }

    ////////////// CRUD unavails //////////////
    @GetMapping("/{id}/unavails")
    public Optional<List<Unavail>> getUnavailsByEmpId(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAndCorrectEmployee(id, headers)) return unavailSvc.getUnavailsByEmpId(id);
        return Optional.empty();
    }

    @PostMapping("/{id}/unavails")
    public Unavail postUnavail(@PathVariable long id, @RequestBody Unavail unavail, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAndCorrectEmployee(id, headers)) {
            Employee employee = getEmployeeById(id, headers).get();
            unavail.setEmployee(employee);
            return unavailSvc.addNewUnavail(unavail);
        }
        return new Unavail();
    }

    @DeleteMapping("/{id}/unavails/{availId}")
    public Optional<List<Unavail>> deleteUnavail(@PathVariable long id, @PathVariable long availId, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAndCorrectEmployee(id, headers)) {
            unavailSvc.deleteUnavail(availId);
            // also return the most current version employee's unavail rows
            return unavailSvc.getUnavailsByEmpId(id);
        } else {
            return Optional.empty();
        }
    }

    ////////////// CRUD shifts //////////////
    @GetMapping("/{id}/shifts")
    public Optional<List<Shift>> getAllShifts(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAndCorrectEmployee(id, headers)) return shiftSvc.getShiftsByEmpId(id);
        return Optional.empty();
    }

    @PutMapping("/{id}/shifts/{shiftId}")
    public Optional<List<Shift>> addEmployeeToShift(@PathVariable long id, @PathVariable long shiftId, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAndCorrectEmployee(id, headers)) {
            // find employeeObj and shiftObj from pathVars, then save the employeeObj onto that shiftObj
            shiftSvc.addEmployeeToShiftFromIds(id, shiftId);

            // return employee's current list of shifts
            return getAllShifts(id, headers);
        } else {
            return Optional.empty();
        }
    }

    @GetMapping("/{id}/unstaffedShifts")
    public Optional<List<Shift>> getAllUnstaffedShifts(@PathVariable long id, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAndCorrectEmployee(id, headers)) return shiftSvc.findAllUnexpiredUnstaffedShifts();
        return Optional.empty();
    }

    @GetMapping("/{id}/unstaffedShifts/{shiftId}")
    public Optional<Shift> getAllUnstaffedShifts(@PathVariable long id, @PathVariable long shiftId, @RequestHeader HttpHeaders headers) {
        if (verifyActiveAndCorrectEmployee(id, headers)) return shiftSvc.getUnstaffedShiftById(shiftId);
        return Optional.empty();
    }
}
