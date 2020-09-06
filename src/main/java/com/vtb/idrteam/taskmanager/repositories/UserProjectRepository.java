package com.vtb.idrteam.taskmanager.repositories;

import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.entities.bindtables.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserProjectRepository  extends JpaRepository<UserProject, Long>, JpaSpecificationExecutor<UserProject> {
    List<UserProject> findByUser(User user);
}
