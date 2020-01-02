//package com.schedule.schedule.dao;
//
//import com.schedule.schedule.model.Employee;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository("employees")
//public class EmployeeDao {
//
//    // TODO: replace TEMPORARY FAKE DB
//    private List<Employee> allEmployees = new ArrayList<>(Arrays.asList(
//            new Employee(null, "Butters not from db", "home", "4251231234", "cutie@pie.com"),
//            new Employee(null, "Oski not from db", "home", "4251231234", "puffy@butt.com")
//    ));
//
//    public List<Employee> getAllEmployees() {
//        // TODO: get from real DB
//        return allEmployees;
//    }
//
//    public void addNewEmployee(LinkedHashMap name_addy_cell_email) {
//        System.out.println("DAO RECEIVED: " + name_addy_cell_email.getClass());
//
//        String name = name_addy_cell_email.get("name").toString();
//        String address = name_addy_cell_email.get("address").toString();
//        String cell = name_addy_cell_email.get("cell").toString();
//        String email = name_addy_cell_email.get("email").toString();
//
//        Employee newEmployee = new Employee(null, name, address, cell, email);
//        System.out.println(newEmployee);
//
//        // TODO: add to dabatabse, for now adding to temp fake db
//        allEmployees.add(newEmployee);
//    }
//
//
//    public Optional<Employee> getEmployeeById(int id) {
//        List<Employee> allEmployees = this.getAllEmployees();
//        Optional<Employee> maybeEmployee = Optional.of(allEmployees.stream()
//                .filter(e -> e.getId() == (id))
//                .findFirst()
//                .get());
//        // TODO: will return as {"message": "No value present", "status":500}
//        System.out.println("RETURNING " + (maybeEmployee));
//        return (maybeEmployee);
//    }
//
//    public void deleteEmployee(int id) {
//        Optional<Employee> maybeEmployee = getEmployeeById(id);
//        maybeEmployee.ifPresent(employee -> allEmployees.remove(employee));
//    }
//
//    public void updateEmployee(int id, LinkedHashMap name_addy_cell_email) {
//        Optional<Employee> maybeEmployee = getEmployeeById(id);
//        if (maybeEmployee.isPresent()) {
//            // TODO: update employee info in database
//
//            // temporary
//            String name = name_addy_cell_email.get("name").toString();
//            String address = name_addy_cell_email.get("address").toString();
//            String cell = name_addy_cell_email.get("cell").toString();
//            String email = name_addy_cell_email.get("email").toString();
//            // temporary
//            Employee employeeToUpdate = maybeEmployee.get();
//            employeeToUpdate.setEmail(email);
//            employeeToUpdate.setAddress(address);
//            employeeToUpdate.setCell(cell);
//            employeeToUpdate.setName(name);
//        }
//    }
//}
