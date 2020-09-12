package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.entities.TaskParticipant;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.entities.dtos.RequestNewTaskDto;
import com.vtb.idrteam.taskmanager.entities.dtos.RequestUpdateTaskDto;
import com.vtb.idrteam.taskmanager.exceptions.ProjectNotFoundException;
import com.vtb.idrteam.taskmanager.exceptions.TaskNotFoundException;
import com.vtb.idrteam.taskmanager.exceptions.UserNotFoundException;
import com.vtb.idrteam.taskmanager.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TaskService {
    private TaskRepository taskRepository;
    private ProjectService projectService;
    private UserService userService;
    private TaskParticipantService taskParticipantService;
    private NotificationService notificationService;

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return taskRepository.existsById(id);
    }

    @Transactional
    public Task createNewTask(Long projectId, RequestNewTaskDto requestNewTaskDto, String username) {
        log.info("Got " + requestNewTaskDto);
        Project project = projectService.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(String.format("Project with id = %d not found!", projectId)));
        User user = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User " + username + " not found"));

        Task task = new Task();
        task.setName(requestNewTaskDto.getName());
        task.addTaskParticipant(new TaskParticipant(user, TaskParticipant.Authority.CREATOR));
        task.setState(requestNewTaskDto.getState());

        project.addTask(task);
        log.info("New Task: " + task);

//        notificationService.notifyAboutNewTask(task);
        return saveOrUpdate(task);
    }

    @Transactional
    public Task updateTask(RequestUpdateTaskDto taskDto) {
        log.info("Got taskDto: " + taskDto);

        Task alteredTask = findById(taskDto.getId()).orElseThrow(() -> new TaskNotFoundException("Task not found, id = " + taskDto.getId()));

        alteredTask.setName(taskDto.getName());
        alteredTask.setDescription(taskDto.getDescription());
        alteredTask.setState(Task.State.valueOf(taskDto.getState()));
        alteredTask.setPriority(Task.Priority.valueOf(taskDto.getPriority()));
        alteredTask.setArchived(taskDto.getArchived());
//        alteredTask.setTaskParticipants(taskDto.getParticipants());

        alteredTask.setUpdatedAt(LocalDateTime.now());

//        notificationService.notifyAboutUpdatedTask(findById(alteredTask.getId()), alteredTask);

        log.info("Task for update: " + alteredTask);
        return saveOrUpdate(alteredTask);
    }

    public Task saveOrUpdate(Task task) {
        return taskRepository.save(task);
    }

}
