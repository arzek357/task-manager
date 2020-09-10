package com.vtb.idrteam.taskmanager.entities.dtos.securityDtos.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDto {
    private String username;

    private String password;

    private String passwordConfirm;

    private String name;

    private String surname;

    private String email;
}
