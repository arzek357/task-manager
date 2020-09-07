package com.vtb.idrteam.taskmanager.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Deprecated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDtoOld {
    private Long id;
    private String name;
    private String description;

    public ProjectDtoOld(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
