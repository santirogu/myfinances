package com.mf.myfinances.exception;

public class CashFlowTypeNotNullOrEmptyException extends RuntimeException {

    public CashFlowTypeNotNullOrEmptyException(String message) {
        super(message);
    }

    public CashFlowTypeNotNullOrEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
