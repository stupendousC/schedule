package com.schedule.schedule.service;

import com.schedule.schedule.dao.TextRepository;
import com.schedule.schedule.model.Employee;
import com.schedule.schedule.model.Shift;
import com.schedule.schedule.model.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TextSvc {
    @Autowired
    TextRepository textRepository;

    public Text addNewText(Text text) {
        return textRepository.save(text);
    }

    public List<Text> findAll() {
        return (List<Text>) textRepository.findAll();
    }

    public Optional<Text> findById(long id) { return textRepository.findById(id); }

    public Optional<Text> findByUuid(String uuid) {
        List<Text> allTexts = findAll();

        for (Text text:allTexts) {
            if (text.getUuid().equals(uuid)) {
                return Optional.of(text);
            }
        }
        return Optional.empty();
    }

    public void deleteText(long id) {
        textRepository.deleteById(id);
    }




    // When someone accepts a shift, then all texts sharing same shift gets deleted
    public String deleteAllTextsOfSameShift(Shift shift) {
        List<Text> allTexts = findAll();

        for ( Text text: allTexts) {
            if (text.getShift() == shift) {
                textRepository.deleteById(text.getId());
            }
        }
        return "DELETED all text data rows re: shift id #" + shift.getId();
    }

}


