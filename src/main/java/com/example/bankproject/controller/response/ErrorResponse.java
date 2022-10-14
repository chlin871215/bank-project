package com.example.bankproject.controller.response;

import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorResponse {
    private List<Map<String, String>> fieldError;
    private String error;

    public ErrorResponse(MethodArgumentNotValidException e) {
        this.fieldError = new ArrayList<>();

        e.getBindingResult().getFieldErrors().stream().forEach(m -> {
            Map<String, String> fieldMap = new HashMap<>();

            fieldMap.put("fields", m.getField());
            fieldMap.put("code", m.getCode());
            fieldMap.put("message", m.getDefaultMessage());
            fieldError.add(fieldMap);
        });
    }

    public ErrorResponse(ConstraintViolationException e) {
        this.fieldError = new ArrayList<>();

        e.getConstraintViolations().stream().forEach(c -> {
            String fieldName = null;
            for (Path.Node node : c.getPropertyPath()) {
                fieldName = node.getName();
            }
            Map<String, String> map = new HashMap<>();
            map.put("code", c.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName());
            map.put("message", c.getMessage());
            map.put("field", fieldName);
            fieldError.add(map);
        });
    }

    public ErrorResponse(Exception e) {
        this.error = e.getMessage();
    }

    public List<Map<String, String>> getFieldError() {
        return fieldError;
    }

    public void setFieldError(List<Map<String, String>> fieldError) {
        this.fieldError = fieldError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
