package com.thoughtworks.capability.gtb.finalquiz.api;

import com.thoughtworks.capability.gtb.finalquiz.domain.Team;
import com.thoughtworks.capability.gtb.finalquiz.exception.TrainerSizeIsToLessException;
import com.thoughtworks.capability.gtb.finalquiz.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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
    public List<Team> createGroup() throws TrainerSizeIsToLessException {
        return groupService.createGroups();
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getAllGroup() {
        return groupService.getAllGroup();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Team> changeGroupName(@PathVariable("id") long id, @RequestParam("name") String name) {
        return groupService.changeGroupName();
    }
}
