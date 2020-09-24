package com.thoughtworks.capability.gtb.finalquiz.api;

import com.thoughtworks.capability.gtb.finalquiz.domain.Team;
import com.thoughtworks.capability.gtb.finalquiz.domain.Trainee;
import com.thoughtworks.capability.gtb.finalquiz.service.GroupService;
import com.thoughtworks.capability.gtb.finalquiz.service.TraineeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@ResponseBody
public class GroupController {
    private final GroupService groupService;
    GroupController(GroupService  groupService) {
        this.groupService = groupService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Team> createGroup() {
        return groupService.createGroups();
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getAllGroup() {
        return groupService.getAllGroup();
    }
}
