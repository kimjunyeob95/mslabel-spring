package com.mslabel.mslabel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mslabel.mslabel.model.User;
import com.mslabel.mslabel.service.UserService;


@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public List<User> home() {
        return this.userService.findAll();
    }
    
}
