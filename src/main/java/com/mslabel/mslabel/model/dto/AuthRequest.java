package com.mslabel.mslabel.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "로그인 요청 데이터")
public record AuthRequest(
    @Schema(description = "사용자 이메일", example = "user@example.com", required = true)
    String email,
    
    @Schema(description = "사용자 비밀번호", example = "password", required = true)
    String password) {
}