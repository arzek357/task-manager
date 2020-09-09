package com.vtb.idrteam.taskmanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.entities.Notification;
import com.vtb.idrteam.taskmanager.exceptions.ResourceNotFoundException;
import com.vtb.idrteam.taskmanager.services.NotificationService;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@AllArgsConstructor
public class NotificationController {
    private NotificationService notificationService;

    @GetMapping("/page/{page}")
    @JsonView(Views.Small.class)
    public List<Notification> getNotificationsByUser(@PathVariable Integer page, Principal principal){
        return notificationService.findByUsername(principal.getName(), page);
    }

    @GetMapping("/{id}")
    @JsonView(Views.BigNotification.class)
    public Notification getNotificationById(@PathVariable Long id){
        return notificationService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
    }
}
