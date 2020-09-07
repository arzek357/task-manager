package com.vtb.idrteam.taskmanager.entities.dtos;

import lombok.Data;

@Data
public class ProjectDto {
    private String name;
    private String description;

    public ProjectDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
