package com.schedule.schedule.dao;

        import com.schedule.schedule.model.Shift;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Long> { }
