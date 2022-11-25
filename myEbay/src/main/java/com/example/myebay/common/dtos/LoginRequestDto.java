package com.example.myebay.common.dtos;

import java.util.Objects;

public class LoginRequestDto {

  private String email;

  private String password;

  public LoginRequestDto(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public LoginRequestDto() {}

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginRequestDto that = (LoginRequestDto) o;
    return Objects.equals(email, that.email) && Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password);
  }
}
