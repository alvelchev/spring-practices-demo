package com.springpageable.exception;

public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = 7251559255487074264L;

  public BadRequestException(String message) {
    super(message);
  }
}
