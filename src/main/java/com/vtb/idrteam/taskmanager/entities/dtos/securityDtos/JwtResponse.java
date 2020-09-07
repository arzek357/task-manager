package com.vtb.idrteam.taskmanager.entities.dtos.securityDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
