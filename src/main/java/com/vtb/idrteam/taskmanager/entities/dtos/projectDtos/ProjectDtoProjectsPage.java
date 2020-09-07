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
    private User creator;

    public ProjectDtoProjectsPage(Project project) {
        this.name = project.getName();
        this.description = project.getDescription();
        this.creator=project.getCreator();
    }
}
