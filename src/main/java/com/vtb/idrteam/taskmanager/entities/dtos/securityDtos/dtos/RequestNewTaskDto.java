package com.vtb.idrteam.taskmanager.entities.dtos.securityDtos.dtos;

import com.vtb.idrteam.taskmanager.entities.Task;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RequestNewTaskDto {
    @NotNull
    @NotEmpty
    @Length(max = 100)
    private String name;

    private Task.State state;
}
