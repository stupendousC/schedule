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
}
