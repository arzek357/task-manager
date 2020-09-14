package com.vtb.idrteam.taskmanager.entities.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RequestAddUserToProject {
    @NotNull
    @NotEmpty
    private String username;
}
