package com.thoughtworks.capability.gtb.finalquiz.service;

import com.thoughtworks.capability.gtb.finalquiz.domain.Trainee;
import com.thoughtworks.capability.gtb.finalquiz.domain.Trainer;
import com.thoughtworks.capability.gtb.finalquiz.exception.TraineeIsNotFoundException;
import com.thoughtworks.capability.gtb.finalquiz.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;
    TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public void initTrainers() {
        List<Trainer> trainers = new ArrayList(){
            {
                add(Trainer.builder().name("Trainer1").build());
                add(Trainer.builder().name("Trainer2").build());
                add(Trainer.builder().name("Trainer3").build());
                add(Trainer.builder().name("Trainer4").build());
            }
        };
        trainerRepository.saveAll(trainers);
    }

    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public List<Trainer> getTrainerByGrouped(boolean grouped) {
        if(!grouped) {
            return trainerRepository.findAll().stream().filter(trainee -> trainee.getTeam() == null).collect(Collectors.toList());
        }
        return null;
    }

    public void deleteTrainer(long id) throws TraineeIsNotFoundException {
        // GTB: - 异常使用错误
        Trainer trainer = trainerRepository.findById(id).orElseThrow(TraineeIsNotFoundException::new);
        trainerRepository.delete(trainer);
    }
}
