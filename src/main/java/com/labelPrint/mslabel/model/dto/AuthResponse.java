package com.labelPrint.mslabel.model.dto;

public record AuthResponse(String accessToken, String refreshToken, String expireDate) {
}
