package com.labelPrint.mslabel.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "로그인 요청 데이터")
public record AuthRequest(
    @NotBlank(message = "사용자 아이디는 필수 입력 값입니다.")
    @Schema(description = "사용자 아이디", example = "tester123", required = true)
    String user_id,

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Schema(description = "사용자 비밀번호", example = "password", required = true)
    String password) {
}