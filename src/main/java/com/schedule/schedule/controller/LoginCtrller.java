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
//@CrossOrigin(origins = {"http://domain2.com", "url2", etc})
@CrossOrigin
public class LoginCtrller {

    @Autowired
    private AdminSvc adminSvc;
    @Autowired
    private EmployeeSvc employeeSvc;

    @GetMapping("/{googleId}")
    public HashMap<String, Object> loginFromGoogle(@PathVariable String googleId) {
        System.out.println("\nLoginCtrller rec'd request from googleId " + googleId);

        HashMap<String, Object> responseData = new HashMap<>();

        // search admin table for matching googleId
        System.out.println("AdminSvc will query db to find matches");

        Optional<Admin> maybeAdmin = adminSvc.findByGoogleId(googleId);
        if (maybeAdmin.isPresent()) {
            System.out.println("FOUND: " + maybeAdmin.get());
            responseData.put("ADMIN", maybeAdmin.get());
        } else {
            System.out.println("EmployeeSvc will query db to find matches");
            Optional<Employee> maybeEmployee = employeeSvc.findByGoogleId(googleId);
            responseData.put("EMPLOYEE", maybeEmployee.get());
        }
        return responseData;
    }
}
