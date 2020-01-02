package com.schedule.schedule.service;

import com.schedule.schedule.dao.AdminRepository;
import com.schedule.schedule.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSvc {

    @Autowired
    // retrieve data from db via AdminRepo
    private AdminRepository adminRepository;

    public List<Admin> findAll() {
        System.out.println("\nAdminSvc -> AdminRepository, to return #" + adminRepository.count() + "admin people");
       return (List<Admin>) adminRepository.findAll();
    }
}


