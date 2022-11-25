package com.example.myebay.common.dtos;

public class ProductResponseSoldItemDto extends ProductResponseAbstract {
  private String name;
  private String description;
  private String url;
  private String buyer;
  private long purchasePrice;
  private String seller;

  public ProductResponseSoldItemDto(
      String name,
      String description,
      String url,
      String buyer,
      long purchasePrice,
      String seller) {
    // constructor for sold item
    this.name = name;
    this.description = description;
    this.url = url;
    this.buyer = buyer;
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

  public String getBuyer() {
    return buyer;
  }

  public void setBuyer(String buyer) {
    this.buyer = buyer;
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
