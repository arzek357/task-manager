package com.vtb.idrteam.taskmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotEnoughRightsException extends ResourceNotFoundException {
    public NotEnoughRightsException(String message) {
        super(message);
    }
}
