package com.vtb.idrteam.taskmanager.repositories;

import com.vtb.idrteam.taskmanager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    //List<TaskDto> findAllByProjectAndArchivedFalse(Project project);
}
