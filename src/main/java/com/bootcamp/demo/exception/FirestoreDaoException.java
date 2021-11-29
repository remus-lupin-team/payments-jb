package com.bootcamp.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error connection to the data store")
public class FirestoreDaoException extends Exception {
    public FirestoreDaoException() {
    }

    public FirestoreDaoException(String message) {
        super(message);
    }

    public FirestoreDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public FirestoreDaoException(Throwable cause) {
        super(cause);
    }

    public FirestoreDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
