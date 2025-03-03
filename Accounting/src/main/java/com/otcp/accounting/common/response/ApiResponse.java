package com.otcp.accounting.common.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ApiResponse<T> {
    private T data;
    private int httpStatus;
    private ApiResponseStatus status;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .data(data)
                .status(ApiResponseStatus.SUCCESS)
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    public static <T> ApiResponse<T> empty() {
        return ApiResponse.<T>builder()
                .data(null)
                .status(ApiResponseStatus.SUCCESS)
                .httpStatus(HttpStatus.OK.value())
                .build();
    }
}
