package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.entities.dtos.securityDtos.dtos.RequestAddUserToProject;
import com.vtb.idrteam.taskmanager.exceptions.ResourceNotFoundException;
import com.vtb.idrteam.taskmanager.exceptions.TaskManagerException;
import com.vtb.idrteam.taskmanager.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Project createNewProject(Project project, String username) {
        User creator = userService.findByUsername(username);
        project.setCreator(creator);
        if (project.getDescription() == null) {
            project.setDescription("No description");
        }
//        project.setCreator(creator);
//        project.getUsers().add(creator);
//        creator.getProjects().add(project);
        creator.addProject(project);
        return saveOrUpdate(project);
    }

    public List<Project> getAllProjectsByUsername(String username) {
        User user = userService.findByUsername(username);
        List<Project> projects = projectRepository.findAllByUsers(user);
        log.info(String.valueOf(projects));
//        return projectRepository.findAllByUsers(user);
        return projects;
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public Project addUserToProject(RequestAddUserToProject requestAddUserToProject, Long projectId, String principalName) {
        User newUserInProject = userService.findByUsername(requestAddUserToProject.getUsername());
        if (newUserInProject == null){
            throw new ResourceNotFoundException("User not found");
        }

        User executor = userService.findByUsername(principalName);
        Project project = findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        if (executor.equals(project.getCreator())){
            project.addUser(newUserInProject);
        } else {
            throw new TaskManagerException("User " + executor.getUsername() + "cant add other user to project");
        }

        return saveOrUpdate(project);
    }
}
