package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.entities.bindtables.UserProject;
import com.vtb.idrteam.taskmanager.exceptions.ResourceNotFoundException;
import com.vtb.idrteam.taskmanager.repositories.ProjectRepository;
import com.vtb.idrteam.taskmanager.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {
    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public List<Project> findAll(Specification<Project> spec){
        return projectRepository.findAll((Sort) spec);
    }

    public Optional<Project> findById(Long id){
        return projectRepository.findById(id);
    }

    public void deleteById(Long id){
        projectRepository.deleteById(id);
    }

    public Project saveOrUpdate(Project project){
        return projectRepository.save(project);
    }

    public Project createNewProject(Project project, String username){
        User creator =  userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        List<UserProject> userProjects = List.of(
                new UserProject(creator, project, true)
        );

        project.setUserProjects(userProjects);

        return saveOrUpdate(project);
    }
}
