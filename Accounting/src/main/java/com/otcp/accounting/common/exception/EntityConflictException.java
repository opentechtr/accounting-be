package com.otcp.accounting.common.exception;

import org.springframework.http.HttpStatus;

public class EntityConflictException extends BaseException{

    public EntityConflictException() {
        super(HttpStatus.CONFLICT, "5001");
    }
}
