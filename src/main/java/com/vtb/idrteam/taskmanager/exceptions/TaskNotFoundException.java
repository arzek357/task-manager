package com.vtb.idrteam.taskmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends ResourceNotFoundException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
