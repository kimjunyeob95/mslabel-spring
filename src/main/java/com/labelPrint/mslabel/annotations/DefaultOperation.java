package com.labelPrint.mslabel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(
            schema = @Schema(
                implementation = DefaultOperation.SuccessResponse.class
            )
        )
    ),
    @ApiResponse(
        responseCode = "400",
        description = "Bad Request",
        content = @Content(
            schema = @Schema(
                implementation = DefaultOperation.ErrorResponse400.class
            )
        )
    ),
    @ApiResponse(
        responseCode = "401",
        description = "Unauthorized",
        content = @Content(
            schema = @Schema(
                implementation = DefaultOperation.ErrorResponse401.class
            )
        )
    ),
    @ApiResponse(
        responseCode = "404",
        description = "Not Found",
        content = @Content(
            schema = @Schema(
                implementation = DefaultOperation.ErrorResponse404.class
            )
        )
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error",
        content = @Content(
            schema = @Schema(
                implementation = DefaultOperation.ErrorResponse500.class
            )
        )
    )
})
public @interface DefaultOperation {

    @Schema(name = "SuccessResponse")
    class SuccessResponse {
        @Schema(type = "integer", example = "200")
        public int status;
        
        @Schema(type = "object")
        public Meta meta;
        
        @Schema(type = "object")
        public Object data;
        
        public static class Meta {
            @Schema(type = "string", example = "2023-12-19 17:45:50")
            public String timestamp;
            
            @Schema(type = "string", example = "v1")
            public String apiVersion;
        }
    }

    @Schema(name = "ErrorResponse400")
    class ErrorResponse400 {
        @Schema(type = "integer", example = "400")
        public int status;
        
        @Schema(type = "object")
        public Meta meta;
        
        @Schema(type = "object")
        public Error error;
        
        public static class Meta {
            @Schema(type = "string", example = "2023-12-19 17:45:50")
            public String timestamp;
            
            @Schema(type = "string", example = "v1")
            public String apiVersion;
        }

        public static class Error {
            @Schema(type = "integer", example = "400")
            public int code;
            
            @Schema(type = "string", example = "비밀번호를 다시 확인해주세요.")
            public String message;
        }
    }

    @Schema(name = "ErrorResponse401")
    class ErrorResponse401 {
        @Schema(type = "integer", example = "401")
        public int status;
        
        @Schema(type = "object")
        public Meta meta;
        
        @Schema(type = "object")
        public Error error;
        
        public static class Meta {
            @Schema(type = "string", example = "2023-12-19 17:45:50")
            public String timestamp;
            
            @Schema(type = "string", example = "v1")
            public String apiVersion;
        }

        public static class Error {
            @Schema(type = "integer", example = "401")
            public int code;
            
            @Schema(type = "string", example = "토큰을 전달해주세요.")
            public String message;
        }
    }

    @Schema(name = "ErrorResponse404")
    class ErrorResponse404 {
        @Schema(type = "integer", example = "404")
        public int status;
        
        @Schema(type = "object")
        public Meta meta;
        
        @Schema(type = "object")
        public Error error;
        
        public static class Meta {
            @Schema(type = "string", example = "2023-12-19 17:45:50")
            public String timestamp;
            
            @Schema(type = "string", example = "v1")
            public String apiVersion;
        }

        public static class Error {
            @Schema(type = "integer", example = "404")
            public int code;
            
            @Schema(type = "string", example = "잘못된 접근입니다.")
            public String message;
        }
    }

    @Schema(name = "ErrorResponse500")
    class ErrorResponse500 {
        @Schema(type = "integer", example = "500")
        public int status;
        
        @Schema(type = "object")
        public Meta meta;
        
        @Schema(type = "object")
        public Error error;
        
        public static class Meta {
            @Schema(type = "string", example = "2023-12-19 17:45:50")
            public String timestamp;
            
            @Schema(type = "string", example = "v1")
            public String apiVersion;
        }

        public static class Error {
            @Schema(type = "integer", example = "500")
            public int code;
            
            @Schema(type = "string", example = "Too many requests")
            public String message;
        }
    }
}
