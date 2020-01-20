package com.schedule.schedule.service;

import com.schedule.schedule.dao.EmployeeRepository;
import com.schedule.schedule.dao.UnavailRepository;
import com.schedule.schedule.model.Employee;
import com.schedule.schedule.model.Unavail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnavailSvc {

    @Autowired
    private UnavailRepository unavailRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Unavail> findAll() {
        return (List<Unavail>) unavailRepository.findAll();
    }

    public Optional<List<Unavail>> findAllUnexpired() {
        List<Unavail> allUnavails = findAll();
        LocalDate today = LocalDate.now(Clock.systemDefaultZone());

        List<Unavail> allUnexpiredUnavails = allUnavails.stream()
                .filter( unavail -> unavail.getDay_off().isAfter(today))
                .collect(Collectors.toList());

        return Optional.of(allUnexpiredUnavails);
    }

    public Unavail addNewUnavail(Unavail unavail) {
//        System.out.println("SVC sees u want to add... day_off" + unavail.getDay_off() + " for " + unavail.getEmployee().getName());
        // don't add new entry if one already exists for same person & same date, return empty Unavail obj instead
        Employee employee = unavail.getEmployee();

        Optional<List<Unavail>> allEmpsCurrUnavailsMaybe = getUnavailsByEmpId(employee.getId());
        if (allEmpsCurrUnavailsMaybe.isPresent()) {
            List<Unavail> allEmpUnavails = allEmpsCurrUnavailsMaybe.get();
            for (Unavail existingUnavail : allEmpUnavails) {
                if (existingUnavail.getDay_off().equals(unavail.getDay_off())) {
                    System.out.println("you already have that day off");
                    return new Unavail();
                }
            }
        }

        return unavailRepository.save(unavail);
    }

    public Optional<Unavail> getUnavailById(long id) {
        return unavailRepository.findById(id);
    }

    public Optional<List<Unavail>> getUnavailsByEmpId(long id) {
        // admin & employee do NOT need the ones in the past
        Optional<List<Unavail>> allUnexpiredUnavailsOpt = findAllUnexpired();
        if (!allUnexpiredUnavailsOpt.isPresent()) return Optional.empty();

        List<Unavail> unavailsOfEmp = allUnexpiredUnavailsOpt.get().stream()
                .filter(unavail -> unavail.getEmployee_id() == id)
                .collect(Collectors.toList());

        return Optional.of(unavailsOfEmp);
    }

    public Optional<List<Unavail>> getUnavailsByDate(LocalDate date) {
        List<Unavail> allUnavails = findAll();
        List<Unavail> unavailsOfDate = allUnavails.stream()
                .filter(unavail -> unavail.getDay_off().equals(date))
                .collect(Collectors.toList());
        return Optional.of(unavailsOfDate);
    }

    // no PUT methods for Unavailabilities, because either a person is available (post) or not (delete)

    public void deleteUnavail(long id) {
        unavailRepository.deleteById(id);
    }


}

