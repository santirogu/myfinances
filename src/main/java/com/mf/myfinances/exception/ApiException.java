package com.mf.myfinances.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
public class ApiException {

    private final String message;
    private final HttpStatus httpStatus;
    private final String code;
    private final ZonedDateTime timestamp;

    public ApiException(String message, HttpStatus httpStatus, String code, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.code = code;
        this.timestamp = timestamp;
    }
}
