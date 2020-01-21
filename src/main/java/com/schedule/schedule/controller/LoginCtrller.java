package com.schedule.schedule.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.schedule.schedule.model.*;
import com.schedule.schedule.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.*;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;


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

    @Value("#{environment.CLIENT_ID}")
    private String CLIENT_ID;

    public boolean checkWithGoogle (String googleAccessToken) {
        HttpTransport transport = new NetHttpTransport();
        JacksonFactory factory = new JacksonFactory();


        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, JacksonFactory.getDefaultInstance())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(CLIENT_ID))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();
        return true;    // for now

        // (Receive idTokenString by HTTPS POST)
//        try {
//            GoogleIdToken idToken = verifier.verify(googleAccessToken);
//            if (idToken != null) {
//                Payload payload = idToken.getPayload();
//
//                // Print user identifier
//                String userId = payload.getSubject();
//                System.out.println("User ID: " + userId);
//
//                // Get profile information from payload
//                String email = payload.getEmail();
//                boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
//                String name = (String) payload.get("name");
//                String pictureUrl = (String) payload.get("picture");
//                String locale = (String) payload.get("locale");
//                String familyName = (String) payload.get("family_name");
//                String givenName = (String) payload.get("given_name");
//
//                // Use or store profile information
//                // ...
//                return true;
//
//            } else {
//                System.out.println("Invalid ID token.");
//            }
//        } catch (Exception e) {
//            System.out.println("error");
//            e.printStackTrace();
//        }
//return false;
    }

    @PostMapping()
    public HashMap<String, Object> loginFromGoogle(@RequestBody LoginParams loginParams, @RequestHeader HttpHeaders headers) {
        // AUTHENTICATE ACCESS TOKEN HERE!!!!

        System.out.println("LOGGING IN: ");
        System.out.println(loginParams.getGoogleId());
        System.out.println(loginParams.getGoogleAccessToken());

        System.out.println("\nFROM THE HTTP HEADER...");
        System.out.println(headers.get("googleId") + " & " + headers.get("googleAccessToken"));
        System.out.println("NOW AUTHENTICATE ACCESS TOKEN! to https://oauth2.googleapis.com/tokeninfo?id_token=XYZ123");
        String googleAccessToken = headers.get("googleAccessToken").get(0);

        checkWithGoogle(googleAccessToken);




//  String specificUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + googleAccessToken;
//        System.out.println(specificUrl);
//        try {
//            URL url = new URL(specificUrl);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            int status = con.getResponseCode();
//            System.out.println("GOOGLE SAYS:" + status);
//        } catch(Exception e) {
//            System.out.println("errro" + e);
//        }


        return loginSvc.loginFromGoogle(loginParams.getGoogleId());
    }

    @PostMapping("/firstTime")
    public Map<String, Object> loginFromGoogleWithUuid(@RequestBody LoginParams loginParams) {
        String googleId = loginParams.getGoogleId();
        String uuid = loginParams.getUuid();

        System.out.println("FIRST TIME: " + loginParams.getGoogleId() + " AND " + loginParams.getUuid());

        return loginSvc.loginFromGoogleWithUuid(googleId, uuid);
    }
}
