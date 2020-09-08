package com.vtb.idrteam.taskmanager.entities.dtos.projectDtos;

import com.vtb.idrteam.taskmanager.entities.User;

public interface ProjectDto {
    Long getId();
    String getId();
    String getName();
    String getDescription();
    User getCreator();
}
