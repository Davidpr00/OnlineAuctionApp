package com.example.myebay.users.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
  private String verificationToken;
  private String verificationTokenExpiration;
  private long dollarsAmount;

  @ManyToMany
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<Role> roles;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonBackReference
  private List<Product> products;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonBackReference
  private List<Bid> bidList;

  public User(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.creationDate = LocalDateTime.now().toString();
    this.roles = new ArrayList<>();
    this.products = new ArrayList<>();
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

  public void setPassword(String password) {
    this.password = password;
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

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public List<String> getRolesString() {
    return roles.stream().map(Role::getName).toList();
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public String getVerificationToken() {
    return verificationToken;
  }

  public void setVerificationToken(String verificationToken) {
    this.verificationToken = verificationToken;
  }

  public String getVerificationTokenExpiration() {
    return verificationTokenExpiration;
  }

  public void setVerificationTokenExpiration(String verificationTokenExpiration) {
    this.verificationTokenExpiration = verificationTokenExpiration;
  }
}
