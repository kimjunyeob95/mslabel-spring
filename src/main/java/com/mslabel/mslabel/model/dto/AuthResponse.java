package com.mslabel.mslabel.model.dto;

import java.util.Date;

public record AuthResponse(String accessToken, String refreshToken, String expireDate) {
}
