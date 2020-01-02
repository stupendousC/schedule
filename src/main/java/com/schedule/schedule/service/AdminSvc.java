//package com.schedule.schedule.service;
//
//import com.schedule.schedule.dao.EmployeeDao;
//import com.schedule.schedule.model.Employee;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//private class AdminSvc {
//
//    private AdminDao adminDao;
//
//    @Autowired
//    public AdminSvc(@Qualifier("employees") EmployeeDao employeeDao) {
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
//}
//
//
