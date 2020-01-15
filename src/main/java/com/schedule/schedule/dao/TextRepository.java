package com.schedule.schedule.dao;

import com.schedule.schedule.model.Text;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends CrudRepository<Text, Long> {

}

