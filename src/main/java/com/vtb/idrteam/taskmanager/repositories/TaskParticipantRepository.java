package com.vtb.idrteam.taskmanager.repositories;

import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.entities.TaskParticipant;
import com.vtb.idrteam.taskmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskParticipantRepository extends JpaRepository<TaskParticipant, Long> {
//    List<TaskParticipant> findAllByUser(User user);
//    List<TaskParticipant> findAllByTask(Task task);
}
