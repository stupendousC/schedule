package com.schedule.schedule.service;

import com.schedule.schedule.dao.UnavailRepository;
import com.schedule.schedule.model.Unavail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnavailSvc {

    @Autowired
    private UnavailRepository unavailRepository;

    public List<Unavail> findAll() {
        return (List<Unavail>) unavailRepository.findAll();
    }

    public Unavail addNewUnavail(Unavail unavail) {
        return unavailRepository.save(unavail);
    }

    public Optional<Unavail> getUnavailById(long id) {
        return unavailRepository.findById(id);
    }

    public Optional<Unavail> updateUnavail (long id, Unavail newInfoUnavail) {
        Optional<Unavail> unavailMaybe = unavailRepository.findById(id);
        unavailMaybe.ifPresent( entry -> {
            entry.setDay_off(newInfoUnavail.getDay_off());
            entry.setEmployee_id(newInfoUnavail.getEmployee_id());
        });
        return unavailMaybe;
    }

    public void deleteUnavail(long id) {
        unavailRepository.deleteById(id);
    }
}
