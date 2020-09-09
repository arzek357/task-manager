package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Notification;
import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    private NotificationRepository notificationRepository;
    private TaskService taskService;
    private UserService userService;

    public Notification saveOrUpdate(Notification notification){
        return notificationRepository.save(notification);
    }

    private String makeNotificationTextForTaskUpdate(Task oldTask, Task alteredTask){
        StringBuilder message = new StringBuilder();

        if(!oldTask.getName().equals(alteredTask.getName())){
            message.append("Название задачи ")
                    .append("\"").append(oldTask.getName()).append("\" изменено на ")
                    .append("\"").append(alteredTask.getName()).append("\"\n");
        }
        if (!oldTask.getDescription().equals(alteredTask.getDescription())){
            message.append("Описание задачи ")
                    .append("\"").append(oldTask.getName()).append("\" ")
                    .append("изменено:\n")
                    .append("\"").append(alteredTask.getDescription()).append("\"\n");
        }
        if (!oldTask.getArchived() && alteredTask.getArchived()){
            message.append("Задача ")
                    .append("\"").append(oldTask.getName()).append("\" ")
                    .append("перемещена в архив\n");
        }
        if (!oldTask.getTaskStatus().getCodename().equals(alteredTask.getTaskStatus().getCodename())){
            message.append("Статус задачи ")
                    .append("\"").append(oldTask.getName()).append("\" ")
                    .append("изменен:\n").append(alteredTask.getTaskStatus().getName());
        }
        if (oldTask.getDeadlineTime() != alteredTask.getDeadlineTime()){
            message.append("Статус задачи ")
                    .append("\"").append(oldTask.getName()).append("\" ")
                    .append("изменен:\n")
                    .append("Было: ").append(oldTask.getDeadlineTime())
                    .append("Стало: ").append(alteredTask.getDeadlineTime());
        }

        return message.toString();
    }

    private String MakeNotificationTextForNewTask(Task newTask){
        StringBuilder message = new StringBuilder();

//        message.append("Новая задача:")

        return message.toString();
    }

}
