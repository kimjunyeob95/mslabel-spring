package com.labelPrint.mslabel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.labelPrint.mslabel.config.service.JwtService;
import com.labelPrint.mslabel.model.dto.AuthResponse;
import com.labelPrint.mslabel.constants.TokenType;
import com.labelPrint.mslabel.model.entity.AdminData;
import com.labelPrint.mslabel.model.entity.Tokens;
import com.labelPrint.mslabel.repository.TokenRepository;
import com.labelPrint.mslabel.utils.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;


    public ResponseEntity<Map<String, Object>> authenticate(AdminData adminData) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(adminData.getUserId(), adminData.getPassword()));
        String jwtToken = jwtService.generateToken(adminData);
        String refreshToken = jwtService.generateRefreshToken(adminData);
        String expireDate = jwtService.expireDate(jwtToken);

//        revokeAllUserTokens(adminData);
//        saveToken(adminData, jwtToken);
        AuthResponse authResponse = new AuthResponse(jwtToken, refreshToken, expireDate);

        Map<String, Object> params = new HashMap<>();
        params.put("accessToken", authResponse.accessToken());
        params.put("expireDate", authResponse.expireDate());

        return JsonResponse.createResponse(HttpStatus.OK.value(), params);
    }

    private void revokeAllUserTokens(AdminData adminData) {
        List<Tokens> validTokens = tokenRepository.findAllValidTokenByUserName(adminData.getUserId());
        if (!validTokens.isEmpty()) {
            validTokens.forEach( t-> {
                t.setExpired(true);
                t.setRevoked(true);
            });
            tokenRepository.saveAll(validTokens);
        }
    }
    private void saveToken (AdminData adminData, String jwtToken) {
        Tokens token = Tokens.builder()
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .userName(adminData.getUsername())
                .build();
        tokenRepository.save(token);
    }

}