package com.vtb.idrteam.taskmanager.entities.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class RequestUpdateTaskDto {

    @NotNull
    @Positive
    private Long id;

    @NotNull
    @NotEmpty
    @Length(max = 100)
    private String name;

    @NotNull
    @NotEmpty
    @Length(max = 1500)
    private String description;

    @NotNull
    @NotEmpty
    private String state;

    @NotNull
    @NotEmpty
    private String priority;

    @NotNull
    private Boolean archived;

    //todo deadlinetime
}
