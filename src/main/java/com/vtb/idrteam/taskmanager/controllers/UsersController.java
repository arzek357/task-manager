package com.vtb.idrteam.taskmanager.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.entities.dtos.securityDtos.dtos.UserDto;
import com.vtb.idrteam.taskmanager.services.UserService;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UsersController {
    private UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ExceptionHandler(IllegalStateException.class)
    @JsonView(Views.Small.class)
    public User createNewUser(@Valid @RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @GetMapping("/{id}")
    @JsonView(Views.BigUser.class)
    public User getById(@PathVariable Long id){
        return userService.findById(id);
    }
}
