package com.schedule.schedule.model;

import com.schedule.schedule.dao.EmployeeRepository;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="unavails")
public class Unavail {
    @Id
    @SequenceGenerator(name="unavails_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="unavails_id_seq")
    private long id;

    private LocalDate day_off;

    @ManyToOne
//    // this is the better choice for 'Owner' side per https://www.baeldung.com/hibernate-one-to-many
    @JoinColumn(name="employee_id")
    private Employee employee;

    public Unavail(Employee employee, LocalDate day_off) {
        this.employee= employee;
        this.day_off = day_off;
    }


    public Unavail() {}

    //////////////////////// TEST JPA
    public long getEmployee_id() {
        return this.employee.getId();
    }
    public Employee getEmployee() {
        return employee;
    }


    // GETTERS only

    public long getId() {
        return id;
    }

    public LocalDate getDay_off() {
        return day_off;
    }
}

//////////////////////////////////////////////
//  PREVIOUS SAFE VERSION
//package com.schedule.schedule.model;
//
//        import com.schedule.schedule.dao.EmployeeRepository;
//
//        import javax.persistence.*;
//        import java.time.LocalDate;
//
//@Entity
//@Table(name="unavails")
//public class Unavail {
//    @Id
//    @SequenceGenerator(name="unavails_id_seq", allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="unavails_id_seq")
//    private long id;
//
//    // employee_id is now declared as a joinColumn, as part of the @ManyToOne
//    private long employee_id;
//
//    private LocalDate day_off;
//
////    @ManyToOne
////    // this is the better choice for 'Owner' side per https://www.baeldung.com/hibernate-one-to-many
////    @JoinColumn(name="employee_id")
////    private Employee employee;
//
//    // OLD
//    public Unavail(long employee_id, LocalDate day_off) {
//        this.employee_id = employee_id;
//        this.day_off = day_off;
//    }
//
////    public Unavail(Employee employee, LocalDate day_off) {
////        this.employee= employee;
////        this.day_off = day_off;
////    }
//
//
//    public Unavail() {}
//
//    //////////////////////// TEST JPA
////    public long getEmployee_id() {
////        return this.employee.getId();
////    }
////    public Employee getEmployee() {
//////        return employee;
//////    }
//
//
//    // GETTERS only
//
//    public long getId() {
//        return id;
//    }
//
//    // OLD
//    public long getEmployee_id() {
//        return employee_id;
//    }
//
//    public LocalDate getDay_off() {
//        return day_off;
//    }
//}
