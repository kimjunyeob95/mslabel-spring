package com.labelPrint.mslabel.exception;

import com.labelPrint.mslabel.constants.TokenErrorMessageConstant;
import com.labelPrint.mslabel.utils.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return JsonResponse.createResponse(HttpStatus.BAD_REQUEST.value(), null, TokenErrorMessageConstant.getNotHaveErrorMessage("USER"));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentialsException(BadCredentialsException ex) {
        return JsonResponse.createResponse(HttpStatus.BAD_REQUEST.value(), null, TokenErrorMessageConstant.getNotHaveErrorMessage("PASSWORD"));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleAuthenticationException(AuthenticationException ex) {
        return JsonResponse.createResponse(HttpStatus.UNAUTHORIZED.value(), null, TokenErrorMessageConstant.getFitErrorMessage("AUTH"));
    }
}
