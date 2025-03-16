package com.otcp.accounting.common.exception;

import org.springframework.http.HttpStatus;

public class DuplicateException extends BaseException{

    public DuplicateException() {
        super(HttpStatus.CONFLICT, "5001");
    }
}
