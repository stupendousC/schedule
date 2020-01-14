package com.schedule.schedule.controller;

import com.schedule.schedule.model.*;
import com.schedule.schedule.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin
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
    public Unavail postUnavail(@PathVariable long id, @RequestBody Unavail unavail) {
        System.out.println("CTRL sees u wanting to add... " + unavail.getDay_off() + " for emp #" + id );

        Optional<Employee> employeeMaybe = getEmployeeById(id);
        if (employeeMaybe.isPresent()) {
            unavail.setEmployee(employeeMaybe.get());
            return unavailSvc.addNewUnavail(unavail);
        } else return new Unavail();
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
        return shiftSvc.getShiftsByEmpId(id);
    }

    @PutMapping("/{id}/shifts/{shiftId}")
    public Optional<List<Shift>> addEmployeeToShift(@PathVariable long id, @PathVariable long shiftId) {
        // find employeeObj and shiftObj from pathVars, then save the employeeObj onto that shiftObj
        shiftSvc.addEmployeeToShiftFromIds(id, shiftId);

        // return employee's current list of shifts
        return getAllShifts(id);
    }

    @GetMapping("/{id}/unstaffedShifts")
    public Optional<List<Shift>> getAllUnstaffedShifts(@PathVariable long id) {
        return shiftSvc.findAllUnexpiredUnstaffedShifts();
    }

    @GetMapping("/{id}/unstaffedShifts/{shiftId}")
    // I *was* going to make this the text response
    public Optional<Shift> getAllUnstaffedShifts(@PathVariable long id, @PathVariable long shiftId) {
        System.out.println("empCtrller passing to Shift svc to find unstaffed shift Id #" + shiftId );
        return shiftSvc.getUnstaffedShiftById(shiftId);
    }
}
