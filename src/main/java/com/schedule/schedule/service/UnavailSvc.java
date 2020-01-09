package com.schedule.schedule.service;

import com.schedule.schedule.dao.EmployeeRepository;
import com.schedule.schedule.dao.UnavailRepository;
import com.schedule.schedule.model.Employee;
import com.schedule.schedule.model.Unavail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // ATTEMPTING TO FIX...
    public Unavail addNewUnavail(long employee_id, Unavail unavail) {
        System.out.println("SVC sees u want to add... day_off" + unavail.getDay_off() + " for emp #" + employee_id);

        // EmployeeCtrller sent arg unavail obj with only the date loaded from front-end
        // need to set add employee obj onto it
        Optional<Employee> foundEmployee = employeeRepository.findById(employee_id);
        if (foundEmployee.isPresent()) {
            unavail.setEmployee(foundEmployee.get());
        }

        return unavailRepository.save(unavail);
    }

    public Optional<Unavail> getUnavailById(long id) {
        return unavailRepository.findById(id);
    }

    public Optional<List<Unavail>> getUnavailsByEmpId(long id) {
        List<Unavail> allUnavails = findAll();
        List<Unavail> unavailsOfEmp = allUnavails.stream()
                .filter(unavail -> { return unavail.getEmployee_id() == id; })
                .collect(Collectors.toList());

        return Optional.of(unavailsOfEmp);

    }

    // no PUT methods for Unavailabilities, because either a person is available (post) or not (delete)

    public void deleteUnavail(long id) {
        unavailRepository.deleteById(id);
    }


}

