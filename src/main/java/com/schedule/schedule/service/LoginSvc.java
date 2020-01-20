package com.schedule.schedule.service;

import com.schedule.schedule.dao.AdminRepository;
import com.schedule.schedule.dao.EmployeeRepository;
import com.schedule.schedule.model.Admin;
import com.schedule.schedule.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginSvc {

    @Autowired
    private AdminSvc adminSvc;
    @Autowired
    private EmployeeSvc employeeSvc;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    public HashMap<String, Object> loginFromGoogle(String googleId) {
//        System.out.println("\nLoginCtrller rec'd request from googleId " + googleId);

        HashMap<String, Object> responseData = new HashMap<>();

        // search admins table for matching googleId
        Optional<Admin> maybeAdmin = adminSvc.findByGoogleId(googleId);
        if (maybeAdmin.isPresent()) {
            responseData.put("ADMIN", maybeAdmin.get());
            return responseData;
        }

        // search employees table for matching googleId
        Optional<Employee> maybeEmployee = employeeSvc.findByGoogleId(googleId);
        if (maybeEmployee.isPresent()) {
            responseData.put("EMPLOYEE", maybeEmployee.get());
            return responseData;
        }

        // googleId does not match anyone in db
        return responseData;
    }


    public Map<String, Object> loginFromGoogleWithUuid(String googleId, String uuid) {
        // returns either a Map<"ADMIN/EMPLOYEE", Admin/Employee obj> or Map<"INVALID UUID", empty Employee obj>
        HashMap<String, Object> responseData = new HashMap<>();

        // search admins table for matching uuid
        Optional<Admin> maybeAdmin = adminSvc.findByUuid(uuid);
        if (maybeAdmin.isPresent()) {
            // save googleId if found, then return newly updated person
            Admin admin = maybeAdmin.get();
            admin.setOauthid(googleId);
            adminRepository.save(admin);

            responseData.put("ADMIN", admin);
            return responseData;
        }

        // search employees table for matching googleId
        Optional<Employee> maybeEmployee = employeeSvc.findByUuid(uuid);
        if (maybeEmployee.isPresent()) {
            // save googleId if found, then return newly updated person
            Employee employee = maybeEmployee.get();
            employee.setOauthid(googleId);
            employeeRepository.save(employee);

            responseData.put("EMPLOYEE", employee);
            return responseData;
        }

        // googleId does not match anyone in db
        responseData.put("INVALID UUID", new Employee());
        return responseData;
    }
}
