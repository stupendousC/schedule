package com.schedule.schedule.service;

import com.schedule.schedule.dao.ShiftRepository;
import com.schedule.schedule.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ShiftSvc {

    @Autowired
    private ShiftRepository shiftRepository;

    public List<Shift> findAll() {
        return (List<Shift>) shiftRepository.findAll();
    }


    public Shift addNewShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    public Optional<Shift> getShiftById(long id) {
        return shiftRepository.findById(id);
    }

    public Optional<List<Shift>> getShiftByEmpId(long id) {
        List<Shift> allShifts = findAll();
        List<Shift> empShifts = allShifts.stream()
                .filter(shift -> (id == shift.getEmployee_id()))
                .collect(Collectors.toList());

        return Optional.of(empShifts);
    }

    public Optional<Shift> updateShift(long id, Shift newInfoShift) {
        Optional<Shift> shiftMaybe = shiftRepository.findById(id);

        shiftMaybe.ifPresent( shift -> {
            shift.setClient_id(newInfoShift.getClient_id());
            shift.setEmployee_id(newInfoShift.getEmployee_id());
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