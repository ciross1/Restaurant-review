package com.myself.restaurant.exceptions;

// Unchecked exceptions (RuntimeException):
//The compiler doesn’t require you to handle them. You can, but it’s optional.

// Checked exceptions (Exception):
//The compiler forces you to either catch them with try/catch or declare them with throws.
public class BaseException extends RuntimeException{

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }


}
