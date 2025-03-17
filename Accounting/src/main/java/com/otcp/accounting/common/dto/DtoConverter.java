package com.otcp.accounting.common.dto;


import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    public static <S, T> List<T> convertList(List<S> sourceList, Class<T> targetClass) {
        return sourceList.stream()
                .map(source -> convert(source, targetClass))
                .collect(Collectors.toList());
    }
}
