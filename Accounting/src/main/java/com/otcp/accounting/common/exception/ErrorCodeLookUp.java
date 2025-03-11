package com.otcp.accounting.common.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeLookUp {

    private ErrorCodeLookUp() {}

    private static final Map<String, String> errorCodes;

    static {
        errorCodes = new HashMap<>();
        errorCodes.put("5000", "ENTITY_NOT_FOUND_EXCEPTION");
        errorCodes.put("5001", "PRODUCT_NOT_FOUND_EXCEPTION");
    }

    public static String getMessageKey(String errorCode) {
        return errorCodes.getOrDefault(errorCode, "GENERIC_EXCEPTION");
    }
}
