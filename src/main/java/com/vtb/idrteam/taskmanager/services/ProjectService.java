package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.entities.dtos.RequestAddUserToProject;
import com.vtb.idrteam.taskmanager.entities.dtos.RequestNewProjectDto;
import com.vtb.idrteam.taskmanager.exceptions.NotEnoughRightsException;
import com.vtb.idrteam.taskmanager.exceptions.ProjectNotFoundException;
import com.vtb.idrteam.taskmanager.exceptions.ResourceNotFoundException;
import com.vtb.idrteam.taskmanager.exceptions.UserNotFoundException;
import com.vtb.idrteam.taskmanager.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectService {
    private ProjectRepository projectRepository;
    private UserService userService;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public List<Project> findAll(Specification<Project> spec) {
        return projectRepository.findAll((Sort) spec);
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    public Project saveOrUpdate(Project project) {
        return projectRepository.save(project);
    }

    public Project createNewProject(RequestNewProjectDto requestNewProjectDto, String username) {
        log.debug("Got requestNewProjectDto: " + requestNewProjectDto);
        User creator = userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User " + username + " not found"));

        Project project = new Project();
        project.setName(requestNewProjectDto.getName());
        project.setCreator(creator);

        if (requestNewProjectDto.getDescription() == null) {
            project.setDescription("No description");
        } else {
            project.setDescription(requestNewProjectDto.getDescription());
        }

        creator.addProject(project);
        log.debug("New project before save: " + project);
        return saveOrUpdate(project);
    }

    public List<Project> getAllProjectsByUsername(String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User " + username + " not found"));
        List<Project> projects = projectRepository.findAllByUsers(user);
        log.info(String.valueOf(projects));
//        return projectRepository.findAllByUsers(user);
        return projects;
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public Project addUserToProject(RequestAddUserToProject requestAddUserToProject, Long projectId, String principalName) {
        User newUserInProject = userService.findByUsername(requestAddUserToProject.getUsername()).orElseThrow(() -> new UserNotFoundException("User " + requestAddUserToProject.getUsername() + " not found"));
//        User newUserInProject = userService.findByUsername(requestAddUserToProject.getUsername());
        if (newUserInProject == null) {
            throw new ResourceNotFoundException("User not found");
        }

        User executor = userService.findByUsername(principalName).orElseThrow(() -> new UserNotFoundException("User " + principalName + " not found"));
//        User executor = userService.findByUsername(principalName);
        Project project = findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project not found, id = " + projectId));

        if (executor.equals(project.getCreator())) {
            project.addUser(newUserInProject);
        } else {
            throw new NotEnoughRightsException("User " + executor.getUsername() + "cant add other user to project");
        }

        return saveOrUpdate(project);
    }
}
