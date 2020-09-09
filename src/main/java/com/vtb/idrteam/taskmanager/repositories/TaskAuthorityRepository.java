package com.vtb.idrteam.taskmanager.repositories;

import com.vtb.idrteam.taskmanager.entities.simpletables.TaskAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAuthorityRepository extends JpaRepository<TaskAuthority, Long> {
    TaskAuthority findByName(String name);
}
