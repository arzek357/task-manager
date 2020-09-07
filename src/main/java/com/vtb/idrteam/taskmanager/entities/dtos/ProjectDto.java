package com.vtb.idrteam.taskmanager.entities.dtos;

import com.vtb.idrteam.taskmanager.entities.User;

import java.util.List;

public interface ProjectDto {
    Long getId();
    String getName();
    String getDescription();
    List<UserDto> getUsers();

}
