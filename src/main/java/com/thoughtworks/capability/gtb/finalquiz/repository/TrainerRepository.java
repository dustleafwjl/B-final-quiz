package com.thoughtworks.capability.gtb.finalquiz.repository;

import com.thoughtworks.capability.gtb.finalquiz.domain.Trainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Long> {
}
