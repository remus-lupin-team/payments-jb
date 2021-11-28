package com.bootcamp.demo.exception;

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
