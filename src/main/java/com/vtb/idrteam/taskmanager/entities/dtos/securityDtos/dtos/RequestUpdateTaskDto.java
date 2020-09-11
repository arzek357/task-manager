package com.vtb.idrteam.taskmanager.entities.dtos.securityDtos.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RequestUpdateTaskDto {

    @NotNull
    @Min(0)
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

    private Boolean archived;

//    @NotNull
//    private List<TaskParticipant> participants;

    //todo deadlinetime
}
