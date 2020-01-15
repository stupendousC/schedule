package com.schedule.schedule.service;

import com.schedule.schedule.dao.TextRepository;
import com.schedule.schedule.model.Employee;
import com.schedule.schedule.model.Shift;
import com.schedule.schedule.model.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TextSvc {
    @Autowired
    TextRepository textRepository;

    public Text addNewText(Text text) {
        return textRepository.save(text);
    }

    public List<Text> findAll() {
        return (List<Text>) textRepository.findAll();
    }

    public Optional<Text> findById(long id) { return textRepository.findById(id); }



    public void deleteText(long id) {
        textRepository.deleteById(id);
    }

    // When someone accepts a shift, then all texts sharing same shift gets deleted
    public void deleteAllTextsOfSameShift(long shift_id) {
        List<Text> allTexts = findAll();

        for ( Text text: allTexts) {
            if (text.getShift().getId() == shift_id) {
                textRepository.deleteById(text.getId());
            }
        }
    }

}





//    public Optional<List<Shift>> findAllUnexpiredUnstaffedShifts() {
//        List<Shift> allShifts = findAll();
//        if (allShifts.size() == 0) return Optional.empty();
//
//        LocalDate today = LocalDate.now(Clock.systemDefaultZone());
//        List<Shift> unexpiredUnstaffedShiftsList = new ArrayList<>();
//        for (Shift shift : allShifts) {
//            if ((shift.getEmployee() == null) && (shift.getShift_date().isAfter(today) )) {
//                unexpiredUnstaffedShiftsList.add(shift);
//            }
//        }
//
//        return Optional.of(unexpiredUnstaffedShiftsList);
//    }
//
//    public Optional<List<Shift>> findAllStaffedShifts() {
//        List<Shift> allShifts = findAll();
//
//        List<Shift> staffedShiftsList = new ArrayList<>();
//        for (Shift shift : allShifts) {
//            if ((shift.getEmployee() != null)) {
//                staffedShiftsList.add(shift);
//            }
//        }
//        return Optional.of(staffedShiftsList);
//    }
//
//    public Optional<List<Shift>> findAllStaffedShiftsOnDate( LocalDate date) {
//        Optional<List<Shift>> allStaffedShiftsOptional = findAllStaffedShifts();
//        if (allStaffedShiftsOptional.isEmpty()) { return Optional.empty(); }
//
//        List<Shift> allStaffedShifts = allStaffedShiftsOptional.get();
//        List<Shift> allStaffedShiftsOnDate = allStaffedShifts.stream()
//                .filter(shift -> (shift.getShift_date().equals(date)))
//                .collect(Collectors.toList());
//
//        return Optional.of(allStaffedShiftsOnDate);
//    }
//
//    public Optional<Shift> getShiftById(long id) {
//        return shiftRepository.findById(id);
//    }
//
//    public Optional<Shift> getUnstaffedShiftById(long id) {
//        // first make sure the shiftId really is an unexpired & unstaffed shift
//        Optional<List<Shift>> allUnexpiredUnstaffedShiftsOpt = findAllUnexpiredUnstaffedShifts();
//        if (allUnexpiredUnstaffedShiftsOpt.isEmpty()) { return Optional.empty(); }
//
//        List<Shift> allUnexpiredUnstaffedShifts = allUnexpiredUnstaffedShiftsOpt.get();
//
//        Optional<Shift> selectedShift = allUnexpiredUnstaffedShifts.stream()
//                .filter(shift -> id == shift.getId())
//                .findFirst();
//
//        System.out.println("\n\nFOUND" + selectedShift.get().getId());
//        return selectedShift;
//    }
//
//    public Optional<List<Shift>> getShiftsByEmpId(long id) {
//
//        Optional<List<Shift>> allStaffedShifts = findAllStaffedShifts();
//        if (allStaffedShifts.isEmpty()) return Optional.empty();
//
//        List<Shift> empShifts = allStaffedShifts.get().stream()
//                .filter(shift -> (shift.getEmployee().getId() == id))
//                .collect(Collectors.toList());
//
//        return Optional.of(empShifts);
//    }
//
//    public Optional<Shift> updateShift(long id, Shift newInfoShift) {
//        Optional<Shift> shiftMaybe = shiftRepository.findById(id);
//
//        shiftMaybe.ifPresent( shift -> {
//            shift.setClient(newInfoShift.getClient());
//            shift.setEmployee(newInfoShift.getEmployee());
//            shift.setShift_date(newInfoShift.getShift_date());
//            shift.setStart_time(newInfoShift.getStart_time());
//            shift.setEnd_time(newInfoShift.getEnd_time());
//            shiftRepository.save(shift);
//        });
//
//        return shiftMaybe;
//    }
//
//
//    public Shift addEmployeeToShiftFromIds(long employeeId, long shiftId) {
//        Optional<Shift> foundShift = findById(shiftId);
//        Optional<Employee> foundEmployee = employeeSvc.getEmployeeById(employeeId);
//
//        // if bogus shiftId or employeeId, return empty unsaved shift
//        if (foundShift.isEmpty() || foundEmployee.isEmpty()) return new Shift();
//        // else, extract the actual objects out from Optionals
//        Shift shift = foundShift.get();
//        Employee employee = foundEmployee.get();
//
//        return addEmployeeToShift(employee, shift);
//    }
//
//
//    public Shift addEmployeeToShift(Employee employee, Shift shift) {
//        shift.setEmployee(employee);
//        return shiftRepository.save(shift);
//    }



