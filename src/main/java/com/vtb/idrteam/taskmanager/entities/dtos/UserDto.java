package com.vtb.idrteam.taskmanager.entities.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @Length(min = 2, max = 30, message = "Username must contain from 2 to 30 characters")
    private String username;

    @Length(min = 5)
    private String password;

    @Length(min = 5)
    private String passwordConfirm;

    @Length(min = 1, max = 30, message = "Name must contain from 1 to 30 characters")
    private String name;

    @Length(min = 1, max = 30, message = "Surname must contain from 1 to 30 characters")
    private String surname;

    @NotNull
    @NotEmpty
    @Email
    private String email;
}
