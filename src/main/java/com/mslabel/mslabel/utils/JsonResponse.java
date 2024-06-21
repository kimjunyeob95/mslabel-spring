package com.mslabel.mslabel.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JsonResponse {

    private static final String DEFAULT_MESSAGE = "정상 처리 되었습니다.";

    // 기본 메시지를 사용하는 메서드
    public static ResponseEntity<Map<String, Object>> createResponse(int status, Map<String, Object> params) {
        return createResponse(status, params, DEFAULT_MESSAGE);
    }

    public static ResponseEntity<Map<String, Object>> createResponse(int status, Map<String, Object> params, String message) {
        String apiVersion = "v1"; // 이 부분을 필요에 따라 동적으로 변경할 수 있습니다.

        Map<String, Object> result = new HashMap<>();
        result.put("status", status);

        Map<String, Object> meta = new HashMap<>();
        meta.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        meta.put("apiType", apiVersion);
        result.put("meta", meta);

        if (status == HttpStatus.OK.value()) {
            if (params != null) {
                result.put("data", params);
            }
            if (!message.isEmpty()) {
                result.put("message", message);
            }
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("code", status);
            error.put("message", message.isEmpty() ? "잘못 된 접근입니다." : message);

            if (params != null && params.containsKey("data")) {
                error.put("data", params.get("data"));
            }
            result.put("error", error);
        }

        return new ResponseEntity<>(result, HttpStatus.valueOf(status));
    }

    public static void writeResponse(HttpServletResponse response, ResponseEntity<Map<String, Object>> responseEntity) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(responseEntity.getStatusCodeValue());
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseEntity.getBody()));
    }

    public static void handleException(HttpServletRequest request, HttpServletResponse response, int status, String message) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("path", request.getRequestURI());
        
        ResponseEntity<Map<String, Object>> responseEntity = createResponse(status, params, message);
        writeResponse(response, responseEntity);
    }
}
