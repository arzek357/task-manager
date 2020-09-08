package com.vtb.idrteam.taskmanager.entities.dtos.projectDtos;

import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.entities.dtos.userDtos.UserDto;
import lombok.NoArgsConstructor;

import java.util.List;
public interface ProjectDto {
    String getId();
    String getName();
    String getDescription();
    User getCreator();
}
