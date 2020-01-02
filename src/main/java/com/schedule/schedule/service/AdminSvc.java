package com.schedule.schedule.service;

import com.schedule.schedule.dao.AdminRepository;
import com.schedule.schedule.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminSvc {

    @Autowired
    // retrieve data from db via AdminRepo
    private AdminRepository adminRepository;

    public List<Admin> findAll() {
        System.out.println("\nAdminSvc -> AdminRepository, to return #" + adminRepository.count() + "admin people");
       return (List<Admin>) adminRepository.findAll();
    }


    public Admin addNewAdmin(Admin admin) {
        System.out.println("AdminSvc -> AdminDao, received new admin " + admin.getName());
        Admin newAdmin = adminRepository.save(admin);
        return newAdmin ;
    }

    public Optional<Admin> getAdminById(long id) {
        return adminRepository.findById(id);
    }

    public Optional<Admin> updateAdmin(long id, Admin newInfoAdmin) {
        Optional<Admin> adminMaybe = adminRepository.findById(id);

        adminMaybe.ifPresent( user -> {
            user.setAddress(newInfoAdmin.getAddress());
            user.setEmail(newInfoAdmin.getEmail());
            user.setName(newInfoAdmin.getName());
            user.setPhone(newInfoAdmin.getPhone());
            adminRepository.save(user);
        });

        return adminMaybe;
    }

    public void deleteAdmin(long id) {
        adminRepository.deleteById(id);
    }
}


