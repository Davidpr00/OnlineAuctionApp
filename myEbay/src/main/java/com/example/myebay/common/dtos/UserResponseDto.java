package com.example.myebay.common.dtos;

import java.util.List;

public class UserResponseDto {

  private String username;
  private String email;
  private String creationDate;
  private String verifiedAt;
  private long dollarsAmount;
  private List<String> roles;

  public UserResponseDto(String username, String email, String creationDate, String verifiedAt,
      long dollarsAmount, List<String> roles) {
    this.username = username;
    this.email = email;
    this.creationDate = creationDate;
    this.verifiedAt = verifiedAt;
    this.dollarsAmount = dollarsAmount;
    this.roles = roles;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

  public String getVerifiedAt() {
    return verifiedAt;
  }

  public void setVerifiedAt(String verifiedAt) {
    this.verifiedAt = verifiedAt;
  }

  public long getDollarsAmount() {
    return dollarsAmount;
  }

  public void setDollarsAmount(long dollarsAmount) {
    this.dollarsAmount = dollarsAmount;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }
}
