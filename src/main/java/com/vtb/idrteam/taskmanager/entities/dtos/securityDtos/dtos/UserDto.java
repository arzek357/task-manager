package com.vtb.idrteam.taskmanager.entities.dtos.securityDtos.dtos;

import lombok.Data;

@Data
public class UserDto {
    private String username;

    private String password;

    private String passwordConfirm;

    private String name;

    private String surname;

    private String email;
}
