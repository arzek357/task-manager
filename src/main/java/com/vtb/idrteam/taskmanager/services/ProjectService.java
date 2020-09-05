package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.repositories.ProjectRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {
    private ProjectRepo projectRepo;

    public List<Project> findAll(){
        return projectRepo.findAll();
    }

    public Optional<Project> findById(Long id){
        return projectRepo.findById(id);
    }

    public List<Project> findByCreatorId(Long creatorId){
        return projectRepo.findByCreatorId(creatorId);
    }

    public void deleteById(Long id){
        projectRepo.deleteById(id);
    }

    public Project saveOrUpdate(Project project){
        return projectRepo.save(project);
    }
}
