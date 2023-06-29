package me.jiniworld.rabbit.exception;

public class MyBusinessException extends RuntimeException {
  public MyBusinessException(String message) {
    super(message);
  }
}
