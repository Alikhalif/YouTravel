package com.youcode.youtravel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PointsValidationException extends RuntimeException{
    public PointsValidationException(String message) {
        super(message);
    }
}
