package com.thoughtworks.capability.gtb.finalquiz.service;

import com.thoughtworks.capability.gtb.finalquiz.domain.Team;
import com.thoughtworks.capability.gtb.finalquiz.domain.Trainee;
import com.thoughtworks.capability.gtb.finalquiz.domain.Trainer;
import com.thoughtworks.capability.gtb.finalquiz.exception.TrainerSizeIsToLessException;
import com.thoughtworks.capability.gtb.finalquiz.repository.GroupRepository;
import com.thoughtworks.capability.gtb.finalquiz.repository.TraineeRepository;
import com.thoughtworks.capability.gtb.finalquiz.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GroupService {

    public static final int GROUP_MAX_SIZE = 2;
    @Autowired
    TrainerService trainerService;
    @Autowired
    TraineeService traineeService;
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

    public List<Team> createGroups() throws TrainerSizeIsToLessException {
        if(trainerRepository.findAll().size() < GROUP_MAX_SIZE) {
            throw new TrainerSizeIsToLessException();
        }
        groupRepository.deleteAll();
        traineeRepository.deleteAll();
        trainerRepository.deleteAll();
        trainerService.initTrainers();
        traineeService.initTrainees();
        List<Trainer> trainers = trainerRepository.findAll();
        List<Team> teams = new ArrayList<>();
        int teamNumbers = trainers.size() / GROUP_MAX_SIZE;
        for(int i = 0; i < teamNumbers; i ++) {
            Team team = Team.builder().name(i+1 + "组").trainees(new ArrayList<>()).trainers(new ArrayList<>()).build();
            pushTrainerToGroup(trainers, team);
            teams.add(team);
        }
        pushTraineeToGroup(teams);

        teams.stream().forEach(team -> {
            Team saveTeam = groupRepository.save(team);
            team.getTrainers().stream().forEach(trainer -> {
                trainer.setTeam(saveTeam);
                trainerRepository.save(trainer);
            });
            team.getTrainees().stream().forEach(trainee -> {
                trainee.setTeam(saveTeam);
                traineeRepository.save(trainee);
            });
        });
        return teams;
    }

    private void pushTraineeToGroup(List<Team> teams) {
        Random random = new Random();
        int index = 0;
        List<Trainee> trainees = traineeRepository.findAll();
        int size = trainees.size();
        for (int i = 0; i < size; i ++) {
            int randNum = random.nextInt(trainees.size());
            teams.get(index).getTrainees().add(trainees.remove(randNum));
            index = ++ index % teams.size();
        }
    }

    private void pushTrainerToGroup(List<Trainer> trainers, Team team) {
        Random random = new Random();
        int randNum = random.nextInt(trainers.size());
        Trainer trainer = trainers.remove(randNum);
        team.getTrainers().add(trainer);
        randNum = random.nextInt(trainers.size());
        trainer = trainers.remove(randNum);
        team.getTrainers().add(trainer);
    }

    public List<Team> getAllGroup() {
        return groupRepository.findAll();
    }
}
