package com.example.myebay.common.dtos;

public class ErrorResponseDto extends RuntimeException {
  private String message;

  public ErrorResponseDto(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
