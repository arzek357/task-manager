package com.vtb.idrteam.taskmanager.controllers;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.dtos.ProjectDto;
import com.vtb.idrteam.taskmanager.services.ProjectService;
import com.vtb.idrteam.taskmanager.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@AllArgsConstructor
@Slf4j
public class ProjectsController {
    private ProjectService projectService;
    private UserService userService;

    //    @GetMapping("/byuser/{id}")
    //    public List<Project> getAllProjects(@PathVariable Long id) {
    @GetMapping
//    public List<Project> getAllProjects(@RequestParam(name = "user_id", required = false) Long userId) {
    public List<ProjectDto> getAllProjects(Principal principal) {
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("user_id", userId.toString());
//        ProjectFilter projectFilter = new ProjectFilter(params);
//        return projectService.findAll(projectFilter.getSpec());

        return userService.findProjectsDtoByUsername(principal.getName());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project createNewProject(@RequestBody Project project, UserPrincipal principal) {
        if (project.getId() != null) {
            project.setId(null);
        }
        return projectService.createNewProject(project, principal.getName());
    }
}
