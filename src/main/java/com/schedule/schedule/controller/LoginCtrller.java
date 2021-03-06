package com.schedule.schedule.controller;

import com.schedule.schedule.model.*;
import com.schedule.schedule.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @PostMapping()
    public HashMap<String, Object> loginFromGoogle(@RequestBody LoginParams loginParams, @RequestHeader HttpHeaders headers) {
        // FUTURE TODO: AUTHENTICATE ACCESS TOKEN HERE!!!!

        String googleId = headers.get("googleId").get(0);
        String googleAccessToken = headers.get("googleAccessToken").get(0);

        return loginSvc.loginFromGoogle(loginParams.getGoogleId());
    }

    @PostMapping("/firstTime")
    public Map<String, Object> loginFromGoogleWithUuid(@RequestBody LoginParams loginParams) {
        String googleId = loginParams.getGoogleId();
        String uuid = loginParams.getUuid();

        return loginSvc.loginFromGoogleWithUuid(googleId, uuid);
    }
}
