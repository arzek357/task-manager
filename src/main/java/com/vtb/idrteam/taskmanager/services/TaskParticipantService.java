package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.entities.TaskParticipant;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.exceptions.ResourceNotFoundException;
import com.vtb.idrteam.taskmanager.repositories.TaskParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskParticipantService {
    private TaskParticipantRepository taskParticipantRepository;
    private UserService userService;
//    private TaskService taskService;

    public TaskParticipant findById(Long id){
        return taskParticipantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Participant not found"));
    }

//    public List<TaskParticipant> findByUsername(String username){
//        return taskParticipantRepository.findAllByUser(userService.findByUsername(username));
//    }

    public List<TaskParticipant> findByTask(Task task){
        return taskParticipantRepository.findAllByTask(task);
    }

    public List<User> findUsersByTask(Task task){
        List<TaskParticipant> participants = taskParticipantRepository.findAllByTask(task);
        List<User> users = new ArrayList<>();
        for (TaskParticipant tp : participants){
            users.add(tp.getUser());
        }

        return users;
    }
}
