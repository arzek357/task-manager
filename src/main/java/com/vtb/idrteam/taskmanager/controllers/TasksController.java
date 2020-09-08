package com.vtb.idrteam.taskmanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.services.TaskParticipantService;
import com.vtb.idrteam.taskmanager.services.TaskService;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TasksController {
    private TaskService taskService;
    private TaskParticipantService taskParticipantService;


    @GetMapping("/{id}/participants")
    @JsonView(Views.BigUser.class)
    public List<User> getTaskParticipants(@PathVariable Long id){
        return taskService.findTaskParticipants(id);
    }


    //Создание нового таска в проекте. Обрабатываемый id - id проекта
    @PostMapping("/{id}")
    @JsonView(Views.Small.class)
    public Task createNewTaskInProject(@RequestBody Task task,@PathVariable Long id){
        return taskService.createNewTaskInProject(id,task);
    }
}
