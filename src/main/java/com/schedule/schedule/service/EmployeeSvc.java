package com.schedule.schedule.service;

import com.schedule.schedule.dao.EmployeeRepository;
import com.schedule.schedule.model.Admin;
import com.schedule.schedule.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeSvc {


    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> findByGoogleId(String googleId) {
        List<Employee> allActives = findAllActives();

        for (Employee person : allActives) {
            System.out.println("looking at " + person.getName());
            if (person.getOauthid().equals(googleId)) {
                System.out.println("FOUND!");
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public List<Employee> findAllActives() {

        Iterable<Employee> allEmployee = employeeRepository.findAll();
        List<Employee> allEmployeeList = (List<Employee>) allEmployee;

        List<Employee> allActives = allEmployeeList.stream()
                .filter(Employee::getActive)
                .collect(Collectors.toList());

        return allActives;
    }

    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    public Optional<Employee> updateEmployee(long id, Employee newInfoEmployee) {
        Optional<Employee> employeeMaybe = employeeRepository.findById(id);

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
}

