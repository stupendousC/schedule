package com.schedule.schedule.service;

import com.schedule.schedule.dao.EmployeeRepository;
import com.schedule.schedule.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EmployeeSvc {


    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
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
            employeeRepository.save(user);
        });

        return employeeMaybe;
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

}

