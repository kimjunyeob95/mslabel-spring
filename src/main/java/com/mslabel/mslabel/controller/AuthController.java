package com.mslabel.mslabel.controller;

import java.io.IOException;
import java.util.Map;

import com.mslabel.mslabel.annotations.DefaultOperation;
import com.mslabel.mslabel.annotations.TokenOperation;
import com.mslabel.mslabel.model.dto.AuthRequest;
import com.mslabel.mslabel.model.entity.User;
import com.mslabel.mslabel.service.AuthService;

import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @DefaultOperation
    @TokenOperation
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody AuthRequest login) {
        User user = User.builder()
                .email(login.email())
                .password(login.password())
                .build();

        return authService.authenticate(user);
    }

    @Hidden
    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }
}