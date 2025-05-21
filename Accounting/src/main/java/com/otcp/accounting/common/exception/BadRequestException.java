package com.otcp.accounting.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {

    public BadRequestException(String errorCode) {
        super(ErrorCodeLookUp.getMessageKey(errorCode), HttpStatus.BAD_REQUEST, errorCode,null);
    }
}
