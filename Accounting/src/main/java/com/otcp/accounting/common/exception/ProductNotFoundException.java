package com.otcp.accounting.common.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BaseException{

    public ProductNotFoundException() {
        super(HttpStatus.NOT_FOUND, "5001");
    }
}
