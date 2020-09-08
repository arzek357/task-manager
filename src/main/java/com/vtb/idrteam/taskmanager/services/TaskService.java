package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.entities.TaskParticipant;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.entities.dtos.tasksDtos.TaskDto;
import com.vtb.idrteam.taskmanager.entities.dtos.userDtos.UserDto;
import com.vtb.idrteam.taskmanager.exceptions.ResourceNotFoundException;
import com.vtb.idrteam.taskmanager.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private ProjectService projectService;
    private UserService userService;
    private TaskParticipantService taskParticipantService;

//    public List<TaskDto> findByProjectAndNotArchived(Long projectId) {
//        return taskRepository.findAllByProjectAndArchivedFalse(projectService.findById(projectId));
//    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with " + id + " not found"));
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<User> findTaskParticipants(Long taskId) {
        Task task = findById(taskId);
        taskParticipantService.findByTask(task);
//        List<TaskParticipant> taskParticipants = taskParticipantService.findByTask(findById(taskId));
//        List<User> users = new ArrayList<>();
//        for (TaskParticipant tp :
//                taskParticipants) {
//            users.add(tp.getUser());
//        }
        return taskParticipantService.findUsersByTask(task);
    }


}
