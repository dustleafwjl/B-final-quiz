package com.thoughtworks.capability.gtb.finalquiz.service;

import com.thoughtworks.capability.gtb.finalquiz.domain.Trainee;
import com.thoughtworks.capability.gtb.finalquiz.exception.TraineeIsNotFoundException;
import com.thoughtworks.capability.gtb.finalquiz.repository.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TraineeService {
    private final TraineeRepository traineeRepository;
    TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }
    public Trainee createTrainee(Trainee trainee) {
        return traineeRepository.save(trainee);
    }

    public List<Trainee> getTraineeByGrouped(boolean grouped) {
        if(!grouped) {
            return traineeRepository.findAll().stream().filter(trainee -> trainee.getTeam() == null).collect(Collectors.toList());
        }
        return null;
    }

    public void initTrainees() {
        List<Trainee> trainees = new ArrayList(){
            {
                add(Trainee.builder().name("沈乐棋").email("leqi@demo.com").github("leqi@demo.com").office("wuhan").build());
                add(Trainee.builder().name("徐慧慧").email("leqi@demo.com").github("leqi@demo.com").office("wuhan").build());
                add(Trainee.builder().name("陈思聪").email("leqi@demo.com").github("leqi@demo.com").office("wuhan").build());
                add(Trainee.builder().name("王江林").email("leqi@demo.com").github("leqi@demo.com").office("wuhan").build());
                add(Trainee.builder().name("王登宇").email("leqi@demo.com").github("leqi@demo.com").office("wuhan").build());
                add(Trainee.builder().name("杨思雨").email("leqi@demo.com").github("leqi@demo.com").office("wuhan").build());
                add(Trainee.builder().name("江雨舟").email("leqi@demo.com").github("leqi@demo.com").office("wuhan").build());
                add(Trainee.builder().name("廖燊").email("leqi@demo.com").github("leqi@demo.com").office("wuhan").build());
                add(Trainee.builder().name("胡晓").email("leqi@demo.com").github("leqi@demo.com").office("wuhan").build());
                add(Trainee.builder().name("但杰").email("leqi@demo.com").github("leqi@demo.com").office("wuhan").build());
            }
        };
        traineeRepository.saveAll(trainees);
    }

    public void deleteTrainee(long id) throws Exception {
        Trainee trainee = traineeRepository.findById(id).orElseThrow(TraineeIsNotFoundException::new);
        traineeRepository.delete(trainee);
    }
}
