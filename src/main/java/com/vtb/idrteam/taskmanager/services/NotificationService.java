package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Notification;
import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.entities.TaskParticipant;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationService {
    private NotificationRepository notificationRepository;
    private UserService userService;

    public Notification saveOrUpdate(Notification notification){
        return notificationRepository.save(notification);
    }

    public Optional<Notification> findById(Long id){
        return notificationRepository.findById(id);
    }

    public List<Notification> findByUsername(String username, Integer page){
        return notificationRepository.findByUsers(userService.findByUsername(username), PageRequest.of(page, 10));
    }

    public Notification notifyAboutNewTask(Task task){
        Notification notification = new Notification();
        notification.setTitle("Новая задача: " + task.getName());
        notification.setMessage(makeNotificationTextForNewTask(task));
        notification.setTask(task);

        for (TaskParticipant tp : task.getTaskParticipants()){
            notification.addUser(tp.getUser());
        }

        return saveOrUpdate(notification);
    }

    public Notification notifyAboutUpdatedTask(Task oldTask, Task alteredTask){
        Notification notification = new Notification();
        notification.setTitle("Изменения в задаче: " + oldTask.getName());
        notification.setMessage(makeNotificationTextForUpdatedTask(oldTask, alteredTask));
        notification.setTask(alteredTask);

        for (TaskParticipant tp : alteredTask.getTaskParticipants()){
            notification.addUser(tp.getUser());
        }

        return saveOrUpdate(notification);
    }


    private String makeNotificationTextForUpdatedTask(Task oldTask, Task alteredTask){
        StringBuilder message = new StringBuilder();

        if(!oldTask.getName().equals(alteredTask.getName())){
            message.append("Старое название: ").append(oldTask.getName()).append("\n");
            message.append("Новое название: ").append(alteredTask.getName()).append("\n");
        }
        if (!oldTask.getDescription().equals(alteredTask.getDescription())){
            message.append("Старое описание: \n").append(oldTask.getDescription()).append("\n");
            message.append("Новое описание: \n").append(alteredTask.getDescription()).append("\n");
        }
        if (!oldTask.getTaskStatus().getCodename().equals(alteredTask.getTaskStatus().getCodename())){
            message.append("Старый статус: ").append(oldTask.getTaskStatus().getName()).append("\n");
            message.append("Новый статус: ").append(alteredTask.getTaskStatus().getName()).append("\n");
        }
        if (oldTask.getDeadlineTime() != alteredTask.getDeadlineTime()){
            message.append("Старый срок сдачи: ").append(oldTask.getDeadlineTime()).append("\n");
            message.append("Новый срок сдачи: ").append(alteredTask.getDeadlineTime()).append("\n");
        }
        if (!oldTask.getArchived() && alteredTask.getArchived()){
            message.append("Задача перемещна в архив\n");
        }

        return message.toString();
    }

    private String makeNotificationTextForNewTask(Task newTask){
        StringBuilder message = new StringBuilder();

        message.append("Описание задачи: \n")
                .append(newTask.getDescription()).append("\n");
        message.append("Статус задачи: ")
                .append(newTask.getTaskStatus().getName()).append("\n");
        message.append("Срок сдачи: ")
                .append(newTask.getDeadlineTime());

        return message.toString();
    }

}
