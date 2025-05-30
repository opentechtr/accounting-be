package com.otcp.accounting.common.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex, Locale locale) {
        return createErrorResponse(
                ex.getErrorCode(),
                getLocalizedMessage(ex.getMessageKey(), null, locale),
                null,
                ex.getHttpStatus()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, Locale locale) {
        Map<String, Object> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), messageSource.getMessage(error.getCode(), null, locale));
        }
        return createErrorResponse(
                getLocalizedMessage("VALIDATION_ERROR", null, locale),
                getLocalizedMessage("VALIDATION_ERROR_MESSAGE", null, locale),
                errors,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, Locale locale) {
        Map<String, Object> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), messageSource.getMessage(violation.getMessage(), null, locale)));
        return createErrorResponse(
                getLocalizedMessage("CONSTRAINT_VIOLATION", null, locale),
                getLocalizedMessage("CONSTRAINT_VIOLATION_MESSAGE", null, locale),
                errors,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonParseException(HttpMessageNotReadableException ex, Locale locale) {
        return createErrorResponse(
                getLocalizedMessage("JSON_PARSE_ERROR", null, locale),
                getLocalizedMessage("JSON_PARSE_ERROR_MESSAGE", null, locale),
                null,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingRequestParameter(MissingServletRequestParameterException ex, Locale locale) {
        return createErrorResponse(
                getLocalizedMessage("MISSING_PARAMETER", null, locale),
                getLocalizedMessage("MISSING_PARAMETER_MESSAGE", new Object[]{ex.getParameterName()}, locale),
                null,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, Locale locale) {
        return createErrorResponse(
                getLocalizedMessage("METHOD_NOT_ALLOWED", null, locale),
                getLocalizedMessage("METHOD_NOT_ALLOWED_MESSAGE", null, locale),
                null,
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, Locale locale) {
        return createErrorResponse(
                getLocalizedMessage("UNSUPPORTED_MEDIA_TYPE", null, locale),
                getLocalizedMessage("UNSUPPORTED_MEDIA_TYPE_MESSAGE", null, locale),
                null,
                HttpStatus.UNSUPPORTED_MEDIA_TYPE
        );
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> handleHttpClientErrorException(HttpClientErrorException ex, Locale locale) {
        return createErrorResponse(
                getLocalizedMessage("HTTP_CLIENT_ERROR", null, locale),
                getLocalizedMessage("HTTP_CLIENT_ERROR_MESSAGE", null, locale),
                null,
                (HttpStatus) ex.getStatusCode()
        );
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> handleResourceAccessException(ResourceAccessException ex, Locale locale) {
        return createErrorResponse(
                getLocalizedMessage("EXTERNAL_SERVICE_UNAVAILABLE", null, locale),
                getLocalizedMessage("EXTERNAL_SERVICE_UNAVAILABLE_MESSAGE", null, locale),
                null,
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorResponse> handleRestClientException(RestClientException ex, Locale locale) {
        return createErrorResponse(
                getLocalizedMessage("REST_CLIENT_ERROR", null, locale),
                getLocalizedMessage("REST_CLIENT_ERROR_MESSAGE", null, locale),
                null,
                HttpStatus.BAD_GATEWAY
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex, Locale locale) {
        return createErrorResponse(
                getLocalizedMessage("INTERNAL_SERVER_ERROR", null, locale),
                getLocalizedMessage("INTERNAL_SERVER_ERROR_MESSAGE", null, locale),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    private String getLocalizedMessage(String messageKey, Object[] args, Locale locale) {
        return messageSource.getMessage(messageKey, args, locale);
    }

    private static ResponseEntity<ErrorResponse> createErrorResponse(String errorCode, String message, Map<String, Object> details, HttpStatus httpStatus) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(errorCode)
                .message(message)
                .httpStatus(httpStatus.value())
                .details(details)
                .build();
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
