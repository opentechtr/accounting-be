package com.otcp.accounting.common.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ApiResponse<T> {
    private T data;
    private String message;
    private int httpStatus;
    private ApiResponseStatus status;

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .data(data)
                .message(message)
                .status(ApiResponseStatus.SUCCESS)
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    public static <T> ApiResponse<T> empty(String message) {
        return ApiResponse.<T>builder()
                .data(null)
                .message(message)
                .status(ApiResponseStatus.SUCCESS)
                .httpStatus(HttpStatus.OK.value())
                .build();
    }
}
