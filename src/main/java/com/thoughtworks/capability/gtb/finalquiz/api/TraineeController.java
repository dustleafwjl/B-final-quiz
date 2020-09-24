package com.thoughtworks.capability.gtb.finalquiz.api;

import com.thoughtworks.capability.gtb.finalquiz.domain.Trainee;
import com.thoughtworks.capability.gtb.finalquiz.service.TraineeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainees")
@ResponseBody
public class TraineeController {
    private final TraineeService traineeService;
    TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trainee createTrainee(@RequestBody Trainee trainee) {
        return traineeService.createTrainee(trainee);
    }
}
