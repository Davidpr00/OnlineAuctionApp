package com.example.myebay.common.dtos;

public class StatusDto {
  private String message;

  public StatusDto(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
