package com.mf.myfinances.exception;

public class CashFlowTypeNotFoundException extends RuntimeException {

    public CashFlowTypeNotFoundException(String message) {
        super(message);
    }

    public CashFlowTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
