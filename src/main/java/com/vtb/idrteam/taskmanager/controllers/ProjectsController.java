package com.vtb.idrteam.taskmanager.controllers;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.dtos.projectDtos.ProjectDto;
import com.vtb.idrteam.taskmanager.entities.dtos.projectDtos.ProjectDtoProjectsPage;
import com.vtb.idrteam.taskmanager.entities.dtos.tasksDtos.TaskDto;
import com.vtb.idrteam.taskmanager.services.ProjectService;
import com.vtb.idrteam.taskmanager.services.TaskService;
import com.vtb.idrteam.taskmanager.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@AllArgsConstructor
@Slf4j
public class ProjectsController {
    private ProjectService projectService;
    private TaskService taskService;
    private UserService userService;


    @GetMapping
    public List<ProjectDto> getAllProjects(Principal principal) {
        return projectService.getAllProjectsByUsername(principal.getName());
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id){
        return projectService.findById(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksPreviewByProject(@PathVariable Long id){
        return taskService.findByProjectAndNotArchived(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDtoProjectsPage createNewProject(@RequestBody ProjectDtoProjectsPage projectDtoProjectsPage, Principal principal) {
        return projectService.createNewProject(projectDtoProjectsPage, principal.getName());
    }
}
