package com.schedule.schedule.dao;

import com.schedule.schedule.model.Unavail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnavailRepository extends CrudRepository<Unavail, Long> { }
