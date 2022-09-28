package com.springpageable.exception;

public class ConflictException extends RuntimeException {

  private static final long serialVersionUID = 8972560933550881722L;

  public ConflictException(String message) {
    super(message);
  }
}
