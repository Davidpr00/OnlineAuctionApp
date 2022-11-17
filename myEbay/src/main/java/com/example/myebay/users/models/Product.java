package com.example.myebay.users.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {
  private String name;
  private String description;
  private String url;
  private String seller;
  private long startingPrice;
  private long purchasePrice;
  private boolean isSold;
  private boolean isForSale;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonManagedReference
  private User user;

  public Product(
      String name,
      String description,
      String url,
      String seller,
      long startingPrice,
      long purchasePrice,
      User user) {
    this.name = name;
    this.description = description;
    this.url = url;
    this.seller = seller;
    this.startingPrice = startingPrice;
    this.purchasePrice = purchasePrice;
    this.user = user;
  }

  public Product() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public long getStartingPrice() {
    return startingPrice;
  }

  public void setStartingPrice(long startingPrice) {
    this.startingPrice = startingPrice;
  }

  public long getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(long purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isSold() {
    return isSold;
  }

  public void setSold(boolean sold) {
    isSold = sold;
  }

  public boolean isForSale() {
    return isForSale;
  }

  public void setForSale(boolean forSale) {
    isForSale = forSale;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getSeller(){
    return getUser().getUsername();
  }
}
