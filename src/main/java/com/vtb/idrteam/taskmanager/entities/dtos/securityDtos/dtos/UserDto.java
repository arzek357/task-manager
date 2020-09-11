package com.vtb.idrteam.taskmanager.entities.dtos.securityDtos.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
public class UserDto {
    @Length(min = 2, max = 30, message = "Username must contain from 2 to 30 characters")
    private String username;

    //todo min 5 chars for password
    private String password;

    private String passwordConfirm;

    @Length(min = 1, max = 30, message = "Name must contain from 1 to 30 characters")
    private String name;

    @Length(min = 1, max = 30, message = "Surname must contain from 1 to 30 characters")
    private String surname;

    @Email
    private String email;
}
