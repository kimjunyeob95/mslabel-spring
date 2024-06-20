package com.mslabel.mslabel.controller;

import java.io.IOException;
import com.mslabel.mslabel.controller.dto.AuthRequest;
import com.mslabel.mslabel.controller.dto.AuthResponse;
import com.mslabel.mslabel.model.User;
import com.mslabel.mslabel.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Slf4j
public class AuthController {
    private final AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest login) {
        String encodedPassword = passwordEncoder.encode(login.password());

        User user = User.builder()
                .email(login.email())
                .password(encodedPassword)
                .build();

        log.info("User created: {}", user);
        return ResponseEntity.ok(authService.authenticate(user));
        // authority : 회원가입 , 인가
        // authentication : 로그인 , 인증
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }
}