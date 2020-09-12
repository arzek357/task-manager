package com.vtb.idrteam.taskmanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.entities.dtos.RequestNewTaskDto;
import com.vtb.idrteam.taskmanager.entities.dtos.RequestUpdateTaskDto;
import com.vtb.idrteam.taskmanager.exceptions.ResourceNotFoundException;
import com.vtb.idrteam.taskmanager.services.TaskParticipantService;
import com.vtb.idrteam.taskmanager.services.TaskService;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.AllArgsConstructor;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TasksController {
    private TaskService taskService;
    private TaskParticipantService taskParticipantService;

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.findById(id);
    }

    //Создание нового таска в проекте. Обрабатываемый id - id проекта
    @PostMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @JsonView(Views.Small.class)
    public Task createNewTaskInProject(@Valid @RequestBody RequestNewTaskDto taskDto, @PathVariable Long id, Principal principal) {
        return taskService.createNewTask(id, taskDto, principal.getName());
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Task modifyTask(@RequestBody RequestUpdateTaskDto taskDto) {
        if (!taskService.existsById(taskDto.getId())){
            throw new ResourceNotFoundException("Task not found");
        }
        return taskService.updateTask(taskDto);
    }
}
