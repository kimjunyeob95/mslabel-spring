package com.labelPrint.mslabel.exception;

import com.labelPrint.mslabel.utils.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return JsonResponse.createResponse(HttpStatus.NOT_FOUND.value(), null, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String message = "";
        if (!ex.getBindingResult().getFieldErrors().isEmpty()) {
            FieldError error = ex.getBindingResult().getFieldErrors().get(0);
            message = String.format("%s %s", error.getField(), error.getDefaultMessage());
        }
        return JsonResponse.createResponse(HttpStatus.UNAUTHORIZED.value(), null, message);
    }
}