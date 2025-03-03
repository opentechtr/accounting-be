package com.otcp.accounting.common.dto;


import org.springframework.beans.BeanUtils;

public class DtoConverter {

    public static <T> T convert(Object source, Class<T> targetClass) {
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Conversion error: " + e.getMessage(), e);
        }
    }
}
