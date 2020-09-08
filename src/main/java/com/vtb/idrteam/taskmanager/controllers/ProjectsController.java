package com.vtb.idrteam.taskmanager.controllers;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.dtos.NewProjectDto;
import com.vtb.idrteam.taskmanager.entities.dtos.projectDtos.ProjectDto;
import com.vtb.idrteam.taskmanager.entities.dtos.projectDtos.ProjectDtoProjectsPage;
import com.vtb.idrteam.taskmanager.services.ProjectService;
import com.vtb.idrteam.taskmanager.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/projects")
@AllArgsConstructor
@Slf4j
public class ProjectsController {
    private ProjectService projectService;
    private UserService userService;
    private ModelMapper modelMapper;

    //    @GetMapping("/byuser/{id}")
    //    public List<Project> getAllProjects(@PathVariable Long id) {
    @GetMapping
    @ResponseBody
//    public List<Project> getAllProjects(@RequestParam(name = "user_id", required = false) Long userId) {
//    public List<Project> getAllProjects(Principal principal) {
    public List<NewProjectDto> getAllProjects(Principal principal) {
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("user_id", userId.toString());
//        ProjectFilter projectFilter = new ProjectFilter(params);
//        return projectService.findAll(projectFilter.getSpec());

//        return userService.findProjectsDtoByUsername(principal.getName());
        List<Project> projects = projectService.getAllProjectsByCreator(principal.getName());
        return projects.stream().map(this::convertToDto).collect(Collectors.toList());
//        return null;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDtoProjectsPage createNewProject(@RequestBody ProjectDtoProjectsPage projectDtoProjectsPage, Principal principal) {
        return projectService.createNewProject(projectDtoProjectsPage, principal.getName());
    }

    private NewProjectDto convertToDto(Project project) {
        NewProjectDto newProjectDto = modelMapper.map(project, NewProjectDto.class);
//        newProjectDto.setSubmissionDate(post.getSubmissionDate(),
//                userService.getCurrentUser().getPreference().getTimezone());
        return newProjectDto;
    }

    private Project convertToEntity(NewProjectDto newProjectDto) throws ParseException {
        Project project = modelMapper.map(newProjectDto, Project.class);

        if (newProjectDto.getId() != null) {
            Project oldProject = projectService.getProjectById(newProjectDto.getId());
            project.setCreator(oldProject.getCreator()); //чисто для теста
        }

        return project;
    }
}
