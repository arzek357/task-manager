package com.vtb.idrteam.taskmanager.repositories;

import com.vtb.idrteam.taskmanager.entities.TaskParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskParticipantRepository extends JpaRepository<TaskParticipant, Long> {

}
