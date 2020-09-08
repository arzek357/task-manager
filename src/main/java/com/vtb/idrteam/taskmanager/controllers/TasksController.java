package com.vtb.idrteam.taskmanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.entities.dtos.userDtos.UserDto;
import com.vtb.idrteam.taskmanager.services.TaskParticipantService;
import com.vtb.idrteam.taskmanager.services.TaskService;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TasksController {
    private TaskService taskService;
    private TaskParticipantService taskParticipantService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.findById(id);
    }

    @GetMapping("/{id}/participants")
    @JsonView(Views.BigUser.class)
    public List<User> getTaskParticipants(@PathVariable Long id){
        return taskService.findTaskParticipants(id);
//        return taskParticipantService.fi
    }

}
