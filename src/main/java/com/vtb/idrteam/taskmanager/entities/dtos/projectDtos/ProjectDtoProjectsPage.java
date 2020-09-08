package com.vtb.idrteam.taskmanager.entities.dtos.projectDtos;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDtoProjectsPage {
    private String name;
    private String description;
    private Long id;

    public ProjectDtoProjectsPage(Project project) {
        this.id=project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
    }
}
