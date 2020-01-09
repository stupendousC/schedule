package com.schedule.schedule.service;

import com.schedule.schedule.dao.ShiftRepository;
import com.schedule.schedule.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShiftSvc {

    @Autowired
    private ShiftRepository shiftRepository;

    public List<Shift> findAll() {
        return (List<Shift>) shiftRepository.findAll();
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
        if (allStaffedShiftsOptional.isEmpty()) { return Optional.empty(); }

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







    ///////// this is all messed up
    public Optional<List<Shift>> getShiftByEmpId(long id) {
        List<Shift> allShifts = findAll();

        if (allShifts.size() == 0) { return Optional.empty(); }

        List<Shift> empShifts = allShifts.stream()
                .filter(shift -> {
                    System.out.println("\tdoes this shift belong to this emp?" + (id==shift.getEmployee().getId()));
                    return (id == shift.getEmployee().getId());
                })
                .collect(Collectors.toList());

        return Optional.of(empShifts);
    }

    public Optional<Shift> updateShift(long id, Shift newInfoShift) {
        Optional<Shift> shiftMaybe = shiftRepository.findById(id);

        shiftMaybe.ifPresent( shift -> {
            // following 2 lines replaced b/c of ORM
//            shift.setClient_id(newInfoShift.getClient_id());
//            shift.setEmployee_id(newInfoShift.getEmployee_id());
            shift.setClient(newInfoShift.getClient());
            shift.setEmployee(newInfoShift.getEmployee());
            shift.setShift_date(newInfoShift.getShift_date());
            shift.setStart_time(newInfoShift.getStart_time());
            shift.setEnd_time(newInfoShift.getEnd_time());
            shiftRepository.save(shift);
        });

        return shiftMaybe;
    }

    public void deleteShift(long id) {
        shiftRepository.deleteById(id);
    }
}