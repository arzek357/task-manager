package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.entities.TaskParticipant;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.exceptions.ProjectNotFoundException;
import com.vtb.idrteam.taskmanager.exceptions.ResourceNotFoundException;
import com.vtb.idrteam.taskmanager.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TaskService {
    private TaskRepository taskRepository;
    private ProjectService projectService;
    private UserService userService;
    private TaskParticipantService taskParticipantService;
    private NotificationService notificationService;

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with " + id + " not found"));
    }

    public boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }

    @Transactional
    public Task createNewTask(Long projectId, Task task, String username) {
        log.info("Got" + task);
        Project project = projectService.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(String.format("Project with id = %d not found!", projectId)));
        User user = userService.findByUsername(username);
        task.setState(Task.State.CREATED);
        task.addTaskParticipant(new TaskParticipant(user, TaskParticipant.Authority.CREATOR));

        project.addTask(task);
        log.info("New Task: " + task);

//        notificationService.notifyAboutNewTask(task);
        return saveOrUpdate(task);
    }

    @Transactional
    public Task updateTask(Task alteredTask) {
        alteredTask.setUpdatedAt(LocalDateTime.now());
        notificationService.notifyAboutUpdatedTask(findById(alteredTask.getId()), alteredTask);
        return saveOrUpdate(alteredTask);
    }

    public Task saveOrUpdate(Task task) {
        return taskRepository.save(task);
    }

}
