package com.thoughtworks.capability.gtb.finalquiz.api;

import com.thoughtworks.capability.gtb.finalquiz.domain.Trainee;
import com.thoughtworks.capability.gtb.finalquiz.service.TraineeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/trainees")
@ResponseBody
public class TraineeController {
    private final TraineeService traineeService;
    TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
        traineeService.initTrainees();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trainee createTrainee(@RequestBody Trainee trainee) {
        return traineeService.createTrainee(trainee);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Trainee> getTraineeByGrouped(@RequestParam("grouped") boolean grouped) {
        return traineeService.getTraineeByGrouped(grouped);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainee(@PathVariable("id") long id) throws Exception {
        traineeService.deleteTrainee(id);
    }
}
