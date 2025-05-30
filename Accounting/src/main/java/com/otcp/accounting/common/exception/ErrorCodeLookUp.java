package com.otcp.accounting.common.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeLookUp {

    private ErrorCodeLookUp() {}

    private static final Map<String, String> errorCodes;

    static {
        errorCodes = new HashMap<>();
        errorCodes.put("4001","BAD_REQUEST_WAREHOUSE_EMPTY_NAME_EXCEPTION");
        errorCodes.put("4002","BAD_REQUEST_WAREHOUSE_EMPTY_LOCATION_EXCEPTION");
        errorCodes.put("4091", "CONFLICT_WAREHOUSE_DUPLICATE_NAME_EXCEPTION");
        errorCodes.put("5000", "ENTITY_NOT_FOUND_EXCEPTION");
        errorCodes.put("5001", "ENTITY_CONFLICT_EXCEPTION");
    }

    public static String getMessageKey(String errorCode) {
        return errorCodes.getOrDefault(errorCode, "GENERIC_EXCEPTION");
    }
}
