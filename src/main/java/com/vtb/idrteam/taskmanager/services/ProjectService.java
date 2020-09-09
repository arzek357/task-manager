package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.User;
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

    public Project createNewProject(Project project, String username) {
        User creator =  userService.findByUsername(username);
        if (project.getDescription()==null){
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
        return projectRepository.findAllByUsers(user);
    }

    public Optional<Project> findById(Long id){
        return projectRepository.findById(id);
    }
}
