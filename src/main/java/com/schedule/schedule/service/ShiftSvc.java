package com.schedule.schedule.service;

import com.schedule.schedule.dao.ShiftRepository;
import com.schedule.schedule.model.Client;
import com.schedule.schedule.model.Employee;
import com.schedule.schedule.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShiftSvc {

    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private EmployeeSvc employeeSvc;
    @Autowired ClientSvc clientSvc;

    public List<Shift> findAll() {
        return (List<Shift>) shiftRepository.findAll();
    }

    public Optional<List<Shift>> findAllUnexpiredUnstaffedShifts() {
        List<Shift> allShifts = findAll();
        if (allShifts.size() == 0) return Optional.empty();

        LocalDate today = LocalDate.now(Clock.systemDefaultZone());
        List<Shift> unexpiredUnstaffedShiftsList = new ArrayList<>();
        for (Shift shift : allShifts) {
            if ((shift.getEmployee() == null) && (shift.getShift_date().isAfter(today) )) {
                unexpiredUnstaffedShiftsList.add(shift);
            }
        }

        return Optional.of(unexpiredUnstaffedShiftsList);
    }

    public Optional<List<Shift>> findAllStaffedShifts() {
        List<Shift> allShifts = findAll();
        if (allShifts.size() == 0) return Optional.empty();

        List<Shift> staffedShiftsList = new ArrayList<>();
        for (Shift shift : allShifts) {
            if ((shift.getEmployee() != null)) {
                staffedShiftsList.add(shift);
            }
        }
        return Optional.of(staffedShiftsList);
    }

    public Optional<List<Shift>> findAllStaffedShiftsOnDate( LocalDate date) {
        Optional<List<Shift>> allStaffedShiftsOptional = findAllStaffedShifts();
        if (!allStaffedShiftsOptional.isPresent()) { return Optional.empty(); }

        List<Shift> allStaffedShifts = allStaffedShiftsOptional.get();
        List<Shift> allStaffedShiftsOnDate = allStaffedShifts.stream()
                .filter(shift -> (shift.getShift_date().equals(date)))
                .collect(Collectors.toList());

        return Optional.of(allStaffedShiftsOnDate);
    }

    public Optional<Shift> findById(long id) { return shiftRepository.findById(id); }

    public Shift addNewShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    public Optional<Shift> getShiftById(long id) {
        return shiftRepository.findById(id);
    }

    public Optional<Shift> getUnstaffedShiftById(long id) {
        // first make sure the shiftId really is an unexpired & unstaffed shift
        Optional<List<Shift>> allUnexpiredUnstaffedShiftsOpt = findAllUnexpiredUnstaffedShifts();
        if (!allUnexpiredUnstaffedShiftsOpt.isPresent()) { return Optional.empty(); }

        List<Shift> allUnexpiredUnstaffedShifts = allUnexpiredUnstaffedShiftsOpt.get();

        Optional<Shift> selectedShift = allUnexpiredUnstaffedShifts.stream()
                .filter(shift -> id == shift.getId())
                .findFirst();

        if (selectedShift.isPresent()) {
            return selectedShift;
        } else {
            return Optional.empty();
        }
    }

    public Optional<List<Shift>> getShiftsByEmpId(long id) {

        Optional<List<Shift>> allStaffedShifts = findAllStaffedShifts();
        if (!allStaffedShifts.isPresent()) return Optional.empty();

        List<Shift> empShifts = allStaffedShifts.get().stream()
                .filter(shift -> (shift.getEmployee().getId() == id))
                .collect(Collectors.toList());

        return Optional.of(empShifts);
    }

    public Optional<Shift> updateShift(long id, Shift newInfoShift) {
        Optional<Shift> shiftMaybe = shiftRepository.findById(id);

        shiftMaybe.ifPresent( shift -> {
            shift.setClient(newInfoShift.getClient());
            shift.setEmployee(newInfoShift.getEmployee());
            shift.setShift_date(newInfoShift.getShift_date());
            shift.setStart_time(newInfoShift.getStart_time());
            shift.setEnd_time(newInfoShift.getEnd_time());
            shiftRepository.save(shift);
        });

        return shiftMaybe;
    }


    public Optional<Shift> addEmployeeToShiftFromIds(long employeeId, long shiftId) {
        Optional<Shift> foundShift = findById(shiftId);
        Optional<Employee> foundEmployee = employeeSvc.getEmployeeById(employeeId);

        // if bogus shiftId or employeeId, return empty unsaved shift
        if (!foundShift.isPresent() || !foundEmployee.isPresent()) return Optional.empty();
        // else, extract the actual objects out from Optionals
        Shift shift = foundShift.get();
        Employee employee = foundEmployee.get();

        return addEmployeeToShift(employee, shift);
    }


    public Optional<Shift> addEmployeeToShift(Employee employee, Shift shift) {
        // ONLY allow setting if shift doesn't already have an employee!
//        System.out.println("\nshift.getEmployee() = "+shift.getEmployee() +" is it null? " + (shift.getEmployee() == null));
        if (shift.getEmployee() == null) {
            shift.setEmployee(employee);
            return Optional.of(shiftRepository.save(shift));
        } else {
//            System.out.println("this shift is already taken by " + shift.getEmployee().getName());
            return Optional.empty();
        }
    }













    public void deleteShift(long id) {
        shiftRepository.deleteById(id);
    }
}