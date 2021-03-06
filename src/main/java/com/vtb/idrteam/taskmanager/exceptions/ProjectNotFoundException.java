package com.vtb.idrteam.taskmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends ResourceNotFoundException {
    public ProjectNotFoundException(String message) {
        super(message);
    }
}
