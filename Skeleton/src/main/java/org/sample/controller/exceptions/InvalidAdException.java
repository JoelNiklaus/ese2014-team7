package org.sample.controller.exceptions;

public class InvalidAdException extends RuntimeException {

    public InvalidAdException(String s) {
        super(s);
    }
}
