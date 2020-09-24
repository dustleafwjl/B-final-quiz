package com.thoughtworks.capability.gtb.finalquiz.repository;

import java.util.List;
import com.thoughtworks.capability.gtb.finalquiz.domain.Trainee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TraineeRepository extends CrudRepository<Trainee, Long> {
    List<Trainee> findAll();
}
