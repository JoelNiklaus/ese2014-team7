package org.sample.controller.exceptions;

public class InvalidAdException extends RuntimeException {

	private static final long serialVersionUID = -2745324429711760838L;

	public InvalidAdException(String s) {
        super(s);
    }
}
