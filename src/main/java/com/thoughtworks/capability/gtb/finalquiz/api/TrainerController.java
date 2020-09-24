package com.thoughtworks.capability.gtb.finalquiz.api;

import com.thoughtworks.capability.gtb.finalquiz.domain.Trainer;
import com.thoughtworks.capability.gtb.finalquiz.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/trainers")
@ResponseBody
public class TrainerController {
    private final TrainerService trainerService;
    TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
        trainerService.initTrainers();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trainer createTrainer(@RequestBody Trainer trainer) {
        return trainerService.createTrainer(trainer);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Trainer> getTrainerByGrouped(@RequestParam("grouped") boolean grouped) {
        return trainerService.getTrainerByGrouped(grouped);
    }
}
