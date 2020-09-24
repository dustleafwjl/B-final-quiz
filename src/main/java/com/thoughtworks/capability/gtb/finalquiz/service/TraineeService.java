package com.thoughtworks.capability.gtb.finalquiz.service;

import com.thoughtworks.capability.gtb.finalquiz.domain.Trainee;
import com.thoughtworks.capability.gtb.finalquiz.repository.TraineeRepository;
import org.springframework.stereotype.Service;

@Service
public class TraineeService {
    private final TraineeRepository traineeRepository;
    TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }
    public Trainee createTrainee(Trainee trainee) {
        return traineeRepository.save(trainee);
    }
}
