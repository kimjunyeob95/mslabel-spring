package com.labelPrint.mslabel.controller;

import java.io.IOException;
import java.util.Map;

import com.labelPrint.mslabel.annotations.TokenOperation;
import com.labelPrint.mslabel.model.dto.AuthRequest;
import com.labelPrint.mslabel.model.entity.AdminData;
import com.labelPrint.mslabel.service.AuthService;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @TokenOperation
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticate(@Valid @RequestBody AuthRequest login) {
        AdminData adminData = AdminData.builder()
                .userId(login.user_id())
                .password(login.password())
                .build();

        return authService.authenticate(adminData);
    }

}