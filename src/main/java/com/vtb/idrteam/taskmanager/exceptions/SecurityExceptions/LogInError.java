package com.vtb.idrteam.taskmanager.exceptions.SecurityExceptions;

import lombok.Data;

import java.util.Date;

@Data
public class LogInError {
    private int status;
    private String message;
    private Date timestamp;

    public LogInError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
