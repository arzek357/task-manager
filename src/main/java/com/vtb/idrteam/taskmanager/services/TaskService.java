package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.exceptions.ProjectNotFoundException;
import com.vtb.idrteam.taskmanager.exceptions.ResourceNotFoundException;
import com.vtb.idrteam.taskmanager.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private ProjectService projectService;
    private UserService userService;
    private TaskParticipantService taskParticipantService;

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with " + id + " not found"));
    }

    public Task createNewTaskInProject(Long projectId,Task task){
        Project project = projectService.findById(projectId).orElseThrow( () -> new ProjectNotFoundException(String.format("Project with id = %d not found!",projectId)));
        project.getTasks().add(task);
        task.setProject(project);
        return saveOrUpdate(task);
    }

    @Transactional
    public Task updateTask(Task task){
        //todo logic with notification
        return saveOrUpdate(task);
    }

    public Task saveOrUpdate(Task task){
        return taskRepository.save(task);
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
