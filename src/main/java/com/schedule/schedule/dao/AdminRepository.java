
package com.schedule.schedule.dao;

import com.schedule.schedule.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Used to access data from database
@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    // extending from CrudRepository, means we'll have some methods for our data repository,
    // including findAll(), to save boilerplate time

}