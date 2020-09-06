package com.vtb.idrteam.taskmanager.controllers;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.services.ProjectService;
import com.vtb.idrteam.taskmanager.utils.ProjectFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/projects")
@AllArgsConstructor
@Slf4j
public class ProjectsController {
    private ProjectService projectService;

    //    @GetMapping("/byuser/{id}")
    //    public List<Project> getAllProjects(@PathVariable Long id) {
    @GetMapping
    public List<Project> getAllProjects(@RequestParam(name = "user_id", required = false) Long userId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("user_id", userId.toString());
        ProjectFilter projectFilter = new ProjectFilter(params);

        return projectService.findAll(projectFilter.getSpec());
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
