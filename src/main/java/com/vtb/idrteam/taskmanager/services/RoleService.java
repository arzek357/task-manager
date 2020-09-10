package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Role;
import com.vtb.idrteam.taskmanager.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public Role findByName(String name){
        return roleRepository.findByName(name);
    }
}
