package com.example.myebay.users.models;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  private String email;
  private String creationDate;
  private String verifiedAt;
  private long dollarsAmount;

  public User( String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email=email;
    this.creationDate = LocalDateTime.now().toString();
  }

  public User() {
    this.creationDate = LocalDateTime.now().toString();

  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
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
}
