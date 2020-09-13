package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Notification;
import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.entities.TaskParticipant;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.exceptions.ResourceNotFoundException;
import com.vtb.idrteam.taskmanager.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {
    private NotificationRepository notificationRepository;
    private UserService userService;

    public Notification saveOrUpdate(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    public List<Notification> findByUsername(String username, Integer page) {
        User user = userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User " + username + " not found"));
        return notificationRepository.findByUsers(user, PageRequest.of(page, 10));
    }

    public Notification notifyAboutNewTask(Task task) {
        Notification notification = new Notification();
        notification.setTitle("Новая задача: " + task.getName());
        notification.setMessage(makeNotificationTextForNewTask(task));
        notification.setTask(task);

        for (TaskParticipant tp : task.getTaskParticipants()) {
            notification.addUser(tp.getUser());
            tp.getUser().getNotifications().add(notification);
        }

        log.debug("New notif(new task): " + notification);
        return saveOrUpdate(notification);
    }

    public Notification notifyAboutUpdatedTask(Task task) {
        Notification notification = new Notification();
        notification.setTitle("Изменения в задаче " + task.getName());
        notification.setMessage(makeNotificationTextForUpdatedTask(task));
        notification.setTask(task);

        for (TaskParticipant tp : task.getTaskParticipants()) {
            notification.addUser(tp.getUser());
            tp.getUser().getNotifications().add(notification);
        }

        log.debug("New notif(upd task): " + notification);
        return saveOrUpdate(notification);
    }


    private String makeNotificationTextForUpdatedTask(Task alteredTask) {
        StringBuilder message = new StringBuilder();

        //хотели сделать подробый текст с содержанием старой и новой задачи, но не успели.
        message.append("Задача ").append(alteredTask.getName()).append(" изменена");

        return message.toString();
    }

    private String makeNotificationTextForNewTask(Task newTask) {
        StringBuilder message = new StringBuilder();
        message.append("Описание задачи: \n")
                .append(newTask.getDescription()).append("\n");
        message.append("Статус задачи: ")
                .append(newTask.getState().getRus()).append("\n");
//        message.append("Срок сдачи: ")
//                .append(newTask.getDeadlineTime());

        return message.toString();
    }

}
