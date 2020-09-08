package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.entities.dtos.projectDtos.ProjectDto;
import com.vtb.idrteam.taskmanager.entities.dtos.projectDtos.ProjectDtoProjectsPage;
import com.vtb.idrteam.taskmanager.exceptions.ResourceNotFoundException;
import com.vtb.idrteam.taskmanager.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
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

    public ProjectDtoProjectsPage createNewProject(ProjectDtoProjectsPage projectDtoProjectsPage, String username) {
        User creator =  userService.findByUsername(username);
        Project project = new Project(projectDtoProjectsPage.getName(),projectDtoProjectsPage.getDescription());
        project.setCreator(creator);
        project.getUsers().add(creator);
        creator.getProjects().add(project);
        saveOrUpdate(project);
        return new ProjectDtoProjectsPage(project);
    }

//    public List<ProjectDto> getAllProjectsByUsername(String username) {
    public List<Project> getAllProjectsByUsername(String username) {
        User user = userService.findByUsername(username);
        return projectRepository.findAllByUsers(user);
    }

    public Project findById(Long id){
        return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project with " + id + " not found"));
    }
}
