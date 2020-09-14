package com.vtb.idrteam.taskmanager.entities.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegistrationUserRequestDto {
    @NotNull
    @NotEmpty
    @Length(min = 3, max = 30, message = "Username must contain from 3 to 30 characters")
    private String username;

    @NotNull
    @NotEmpty
    @Length(min = 6, max = 30, message = "Password must contain from 6 to 30 characters")
    private String password;

    @NotNull
    @NotEmpty
    @Length(min = 1, max = 30, message = "Name must contain from 6 to 30 characters")
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 1, max = 30, message = "Surname must contain from 6 to 30 characters")
    private String surname;

    @NotNull
    @NotEmpty
    @Length(min = 6, max = 30, message = "E-mail must contain from 6 to 30 characters")
    private String email;
}
