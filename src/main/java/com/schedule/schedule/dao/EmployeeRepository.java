package com.schedule.schedule.dao;

import com.schedule.schedule.model.Admin;
import com.schedule.schedule.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}