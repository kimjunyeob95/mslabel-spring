package com.mslabel.mslabel.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mslabel.mslabel.config.service.JwtService;
import com.mslabel.mslabel.model.dto.AuthResponse;
import com.mslabel.mslabel.constants.TokenType;
import com.mslabel.mslabel.model.entity.Tokens;
import com.mslabel.mslabel.model.entity.User;
import com.mslabel.mslabel.repository.TokenRepository;
import com.mslabel.mslabel.repository.UserRepository;
import com.mslabel.mslabel.utils.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    // public User authenticate(User user) {
    public ResponseEntity<Map<String, Object>> authenticate(User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            String jwtToken = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            String expireDate = jwtService.expireDate(jwtToken);

            revokeAllUserTokens(user);
            saveToken(user, jwtToken);
            AuthResponse authResponse = new AuthResponse(jwtToken, refreshToken, expireDate);

            Map<String, Object> params = new HashMap<>();
            params.put("accessToken", authResponse.accessToken());
            params.put("expireDate", authResponse.expireDate());

            return JsonResponse.createResponse(HttpStatus.OK.value(), params, "인증 성공");
        } catch(UsernameNotFoundException e){
            return JsonResponse.createResponse(HttpStatus.UNAUTHORIZED.value(), null, e.getMessage());
        } catch (AuthenticationException e) {
            return JsonResponse.createResponse(HttpStatus.UNAUTHORIZED.value(), null, "인증 실패");
        } catch (Exception e) {
            return JsonResponse.createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "서버 오류");
        }
    }

    private void revokeAllUserTokens(User user) {
        List<Tokens> validTokens = tokenRepository.findAllValidTokenByUserName(user.getEmail());
        if (!validTokens.isEmpty()) {
            validTokens.forEach( t-> {
                t.setExpired(true);
                t.setRevoked(true);
            });
            tokenRepository.saveAll(validTokens);
        }
    }
    private void saveToken (User user, String jwtToken) {
        Tokens token = Tokens.builder()
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .userName(user.getUsername())
                .build();
        tokenRepository.save(token);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail; // username
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            User user= this.userRepository.findByEmail(userEmail).get();
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                String expireDate = jwtService.expireDate(accessToken);
                AuthResponse authResponse = new AuthResponse(accessToken, refreshToken, expireDate);
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

}