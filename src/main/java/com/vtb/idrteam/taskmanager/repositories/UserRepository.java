package com.vtb.idrteam.taskmanager.repositories;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);

//    @Query("select new Project (p)")
//    @Query("select p from User.userProjects where ")
//    List<Project> findProjectsByUsername(String username);
}
