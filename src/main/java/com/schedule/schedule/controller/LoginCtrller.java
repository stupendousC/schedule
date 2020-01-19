package com.schedule.schedule.controller;

import com.schedule.schedule.model.*;
import com.schedule.schedule.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/login")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin
public class LoginCtrller {

    @Autowired
    private AdminSvc adminSvc;
    @Autowired
    private EmployeeSvc employeeSvc;

    @Autowired
    private LoginSvc loginSvc;

    @GetMapping("/{googleId}")
    public HashMap<String, Object> loginFromGoogle(@PathVariable String googleId) {
//        System.out.println("\nLoginCtrller rec'd request from googleId " + googleId);
        return loginSvc.loginFromGoogle(googleId);

//        HashMap<String, Object> responseData = new HashMap<>();
//
//        // search admins table for matching googleId
//        Optional<Admin> maybeAdmin = adminSvc.findByGoogleId(googleId);
//        if (maybeAdmin.isPresent()) {
//            responseData.put("ADMIN", maybeAdmin.get());
//            return responseData;
//        }
//
//        // search employees table for matching googleId
//        Optional<Employee> maybeEmployee = employeeSvc.findByGoogleId(googleId);
//        if (maybeEmployee.isPresent()) {
//            responseData.put("EMPLOYEE", maybeEmployee.get());
//            return responseData;
//        }
//
//        // googleId does not match anyone in db
//        return responseData;
    }

    @PostMapping("/{googleId}")
    public Map<String, Object> loginFromGoogleWithUuid(@PathVariable String googleId, @RequestBody Employee employee) {
        // The request body is NOT an actual Employee, I just needed an empty person to attach a uuid to in the requestBody
        String uuid = employee.getUuid();
        return loginSvc.loginFromGoogleWithUuid(googleId, uuid);
    }
}
