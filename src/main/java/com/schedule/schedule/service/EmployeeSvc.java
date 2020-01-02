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
        System.out.println("\nEmployeeSvc -> EmployeeRepository, to return #" + employeeRepository.count() + " employees");
        Iterable<Employee> allAdmins = employeeRepository.findAll();
        allAdmins.forEach(person ->
                System.out.println(person.getName()));

        return (List<Employee>) employeeRepository.findAll();
    }


//    private EmployeeDao employeeDao;
//
//    @Autowired
//    public EmployeeSvc(@Qualifier("employees") EmployeeDao employeeDao) {
//        this.employeeDao = employeeDao;
//    }
//
//    public List<Employee> getAllEmployees() {
//        return employeeDao.getAllEmployees();
//    }
//
//    public Optional<Employee> getEmployeeById(int id) {
//        return employeeDao.getEmployeeById(id);
//    }
//
//    public void addNewEmployee(LinkedHashMap name_addy_cell_email) {
//        employeeDao.addNewEmployee(name_addy_cell_email);
//    }
//
//    public void deleteEmployee(int id) {
//        employeeDao.deleteEmployee(id);
//    }
//
//    public void updateEmployee(int id, LinkedHashMap name_addy_cell_email) {
//        employeeDao.updateEmployee(id, name_addy_cell_email);
//    }
}


