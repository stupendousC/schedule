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

        // just printing, delete this chunk later
        System.out.println("\nAdminSvc -> AdminRepository, to return #" + adminRepository.count() + "admin people");
         Iterable<Admin> allAdmins = adminRepository.findAll();
         allAdmins.forEach(staff ->
                 System.out.println(staff.getName()));

       return (List<Admin>) adminRepository.findAll();
    }

}


