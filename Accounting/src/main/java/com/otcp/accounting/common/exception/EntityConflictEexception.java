package com.otcp.accounting.common.exception;

import org.springframework.http.HttpStatus;

public class EntityConflictEexception extends BaseException{

    public EntityConflictEexception() {
        super(HttpStatus.CONFLICT, "5001");
    }
}
