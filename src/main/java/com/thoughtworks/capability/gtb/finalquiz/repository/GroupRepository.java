package com.thoughtworks.capability.gtb.finalquiz.repository;

import com.thoughtworks.capability.gtb.finalquiz.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<Team, Long> {
    List<Team> findAll();
}
