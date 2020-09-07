package com.vtb.idrteam.taskmanager.entities.dtos.securityDtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
