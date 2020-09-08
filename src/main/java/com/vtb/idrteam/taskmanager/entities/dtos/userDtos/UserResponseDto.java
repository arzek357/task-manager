package com.vtb.idrteam.taskmanager.entities.dtos.userDtos;

import com.vtb.idrteam.taskmanager.entities.User;
import lombok.Data;

@Data
public class UserResponseDto {
    String username;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
    }
}
