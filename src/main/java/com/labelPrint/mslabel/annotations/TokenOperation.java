package com.labelPrint.mslabel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.labelPrint.mslabel.model.dto.AuthRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
    tags = {"인증"},
    summary = "토큰 발급", 
    description = "토큰 발급 endPoint",
    requestBody = @RequestBody(
        description = "로그인 요청 데이터",
        content = @Content(
            schema = @Schema(
                implementation = AuthRequest.class
            )
        )
    )
)
@DefaultOperation
public @interface TokenOperation {
    
}
