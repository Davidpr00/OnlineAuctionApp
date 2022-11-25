package com.example.myebay.common.dtos;

public class ProductResponseDto extends ProductResponseAbstract {
  private String name;
  private String description;
  private String url;
  private long startingPrice;
  private long purchasePrice;

  private String seller;

  public ProductResponseDto(
      String name,
      String description,
      String url,
      String seller,
      long startingPrice,
      long purchasePrice) {
    this.name = name;
    this.description = description;
    this.url = url;
    this.startingPrice = startingPrice;
    this.purchasePrice = purchasePrice;
    this.seller = seller;
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

  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
  }
}
