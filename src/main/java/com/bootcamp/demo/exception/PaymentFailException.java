package com.bootcamp.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error processing payment")
public class PaymentFailException extends Exception{
    public PaymentFailException() {
    }

    public PaymentFailException(String message) {
        super(message);
    }

    public PaymentFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentFailException(Throwable cause) {
        super(cause);
    }

    public PaymentFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
