package com.thoughtworks.capability.gtb.finalquiz.service;

import com.thoughtworks.capability.gtb.finalquiz.domain.Team;
import com.thoughtworks.capability.gtb.finalquiz.domain.Trainee;
import com.thoughtworks.capability.gtb.finalquiz.domain.Trainer;
import com.thoughtworks.capability.gtb.finalquiz.repository.GroupRepository;
import com.thoughtworks.capability.gtb.finalquiz.repository.TraineeRepository;
import com.thoughtworks.capability.gtb.finalquiz.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;
    GroupService(GroupRepository groupRepository,
                 TraineeRepository traineeRepository,
                 TrainerRepository trainerRepository) {
        this.groupRepository = groupRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    public Team createGroup() {
        groupRepository.deleteAll();
        List<Trainer> trainers = trainerRepository.findAll();

        return null;
//        return groupRepository.save();
    }

    public List<Team> getAllGroup() {
        return null;
    }
}
