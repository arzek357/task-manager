package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Notification;
import com.vtb.idrteam.taskmanager.entities.Task;
import com.vtb.idrteam.taskmanager.entities.TaskParticipant;
import com.vtb.idrteam.taskmanager.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    private NotificationRepository notificationRepository;
//    private TaskService taskService;
    private UserService userService;

    public Notification saveOrUpdate(Notification notification){
        return notificationRepository.save(notification);
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

    //TODO
    private String makeNotificationTextForNewTask(Task newTask){
        StringBuilder message = new StringBuilder();

//        message.append("Новая задача:")

        return message.toString();
    }

}
