package com.schedule.schedule.service;

import com.schedule.schedule.dao.EmployeeRepository;
import com.schedule.schedule.dao.ShiftRepository;
import com.schedule.schedule.model.Employee;
import com.schedule.schedule.model.Shift;
import com.schedule.schedule.model.Unavail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeSvc {


    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private ShiftSvc shiftSvc;
    @Autowired
    private UnavailSvc unavailSvc;

    public Optional<Employee> findByGoogleId(String googleId) {
        List<Employee> allActives = findAllActives();

        for (Employee person : allActives) {
            if (person.getOauthid().equals(googleId)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public List<Employee> findAllActives() {

        Iterable<Employee> allEmployee = employeeRepository.findAll();
        List<Employee> allEmployeeList = (List<Employee>) allEmployee;

        return allEmployeeList.stream()
                .filter(Employee::getActive)
                .collect(Collectors.toList());
    }

    public Employee addNewEmployee(Employee employee) {
        // TODO: validate phone number!!!  FOR ALL PEOPLES!!!

        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    public Optional<Employee> updateEmployee(long id, Employee newInfoEmployee) {
        Optional<Employee> employeeMaybe = employeeRepository.findById(id);

        // TODO: validate phone number!!!! FOR ALL PEOPLES!!!

        employeeMaybe.ifPresent( user -> {
            user.setAddress(newInfoEmployee.getAddress());
            user.setEmail(newInfoEmployee.getEmail());
            user.setName(newInfoEmployee.getName());
            user.setPhone(newInfoEmployee.getPhone());
            user.setActive(newInfoEmployee.getActive());
            employeeRepository.save(user);
        });

        return employeeMaybe;
    }

    public void deleteEmployee(long id) {
        Optional<Employee> departingPerson = employeeRepository.findById(id);
        departingPerson.ifPresent( person -> {
            person.setActive(false);
            employeeRepository.save(person);
        });
    }

    public List<Employee> getAvailEmployeesByShift(long shiftId) {
//        System.out.println("EmployeeSvc rec'd request for available Emps for shift obj id#" + shiftId);

        // find the shift obj based on shiftId
        Optional<Shift> optionalShift = shiftSvc.findById(shiftId);
        if (optionalShift.isEmpty()) return new ArrayList<>();
        // if shift exists, extract Shift obj out of the Optional via .get()
        Shift shift = optionalShift.get();
        LocalDate targetDate = shift.getShift_date();

        return getAvailEmployeesByDate(targetDate);
    }

    public List<Employee> getAvailEmployeesByDate(LocalDate targetDate) {
//        System.out.println("EmployeeSvc rec'd request for available Emps for date = " + targetDate);

        // find who's 1. not in Unavails for that day AND 2. not in Shifts for that day
        // 0.   assemble list of active employees
        List<Employee> allEligibleEmps = findAllActives();
        // 1.   filter out those with an unavailable date == shift date
        Optional<List<Unavail>> allUnavailsOnDate = unavailSvc.getUnavailsByDate(targetDate);
        if (allUnavailsOnDate.isPresent()) {
            List<Unavail> unavails = allUnavailsOnDate.get();
            // remove the employees in unavails from allEligibleEmps
            unavails.forEach( unavail -> {
                Employee ineligibleEmp = unavail.getEmployee();
                allEligibleEmps.remove(ineligibleEmp);
            });
        }

        // 2.   filter out remaining people who already have a diff shift on same date
        Optional<List<Shift>> allStaffedShiftsOnDateOptional= shiftSvc.findAllStaffedShiftsOnDate(targetDate);
        if (allStaffedShiftsOnDateOptional.isPresent()) {
            List<Shift> allStaffedShiftsOnDate = allStaffedShiftsOnDateOptional.get();

            for (Shift currShift : allStaffedShiftsOnDate) {
                // remove currShift's employee from allEligibleEmps
                Employee ineligibleEmp = currShift.getEmployee();
                allEligibleEmps.remove(ineligibleEmp);
            }
        }

//        System.out.println("We found " + allEligibleEmps.size() + "eligibileEmps!!");
        return allEligibleEmps;
    }
}