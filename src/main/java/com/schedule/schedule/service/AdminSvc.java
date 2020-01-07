package com.schedule.schedule.service;

import com.schedule.schedule.dao.AdminRepository;
import com.schedule.schedule.model.Admin;
import com.schedule.schedule.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminSvc extends Person {

    @Autowired
    // retrieve data from db via AdminRepo
    private AdminRepository adminRepository;

    public Optional<Admin> findByGoogleId(String googleId) {
        List<Admin> allActives = findAllActives();

        for (Admin person : allActives) {
            System.out.println("looking at admin: " + person.getName());
            if (person.getOauthid().equals(googleId)) {
                System.out.println("FOUND!");
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public List<Admin> findAllActives() {
        System.out.println("\nAdminSvc -> AdminRepository, which has #" + adminRepository.count() + "admin people total, both active & not");

        Iterable<Admin> allAdmin = adminRepository.findAll();
        List<Admin> allAdminList = (List<Admin>) allAdmin;

        List<Admin> allActives = allAdminList.stream()
                .filter(Admin::getActive)
                .collect(Collectors.toList());

        return allActives;
    }


    public Admin addNewAdmin(Admin admin) {
        System.out.println("AdminSvc received new admin " + admin.getName());
        return adminRepository.save(admin);
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
            user.setActive(newInfoAdmin.getActive());
            adminRepository.save(user);
        });

        return adminMaybe;
    }

    public void deleteAdmin(long id) {
        Optional<Admin> departingPerson = adminRepository.findById(id);
        departingPerson.ifPresent( person -> {
            person.setActive(false);
            adminRepository.save(person);
        });
    }
}


