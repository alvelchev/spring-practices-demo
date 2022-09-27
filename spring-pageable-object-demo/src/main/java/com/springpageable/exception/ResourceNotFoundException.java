package com.springpageable.exception;

public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -6960286429292643521L;

  public ResourceNotFoundException(final String message) {
    super(message);
  }
}
