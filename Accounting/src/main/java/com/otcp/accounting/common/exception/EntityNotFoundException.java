package com.otcp.accounting.common.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BaseException{

    public EntityNotFoundException() {
        super(HttpStatus.NOT_FOUND, "5000");
    }
}
