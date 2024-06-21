package com.mslabel.mslabel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mslabel.mslabel.model.entity.User;
import com.mslabel.mslabel.service.UserService;

import io.swagger.v3.oas.annotations.Hidden;


@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    @Hidden
    public List<User> home() {
        return this.userService.findAll();
    }
    
}
