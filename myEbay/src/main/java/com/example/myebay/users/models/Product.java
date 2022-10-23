package com.example.myebay.users.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
  private String name;
  private String description;
  private String url;
  private long startingPrice;
  private long purchasePrice;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Product(String name, String description, String url, long startingPrice,
      long purchasePrice) {
    this.name = name;
    this.description = description;
    this.url = url;
    this.startingPrice = startingPrice;
    this.purchasePrice = purchasePrice;
  }

  public Product() {

  }

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

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
