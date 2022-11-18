package com.example.myebay.common.dtos;

import com.example.myebay.users.models.Bid;
import java.util.List;

public class ProductResponseWithListOfBidsDTO {
  private String name;
  private String description;
  private String url;
  private String buyer;
  private long purchasePrice;
  private String seller;
  private List<Bid> bidList;

  public ProductResponseWithListOfBidsDTO(String name, String description, String url, String buyer,
      long purchasePrice, String seller) {
    this.name = name;
    this.description = description;
    this.url = url;
    this.buyer = buyer;
    this.purchasePrice = purchasePrice;
    this.seller = seller;
  }

  public ProductResponseWithListOfBidsDTO(String name, String description,List<Bid> bidList, String url,long purchasePrice, String seller) {
    this.name = name;
    this.description = description;
    this.url = url;
    this.purchasePrice = purchasePrice;
    this.seller = seller;
    this.bidList = bidList;
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

  public List<Bid> getBidList() {
    return bidList;
  }

  public void setBidList(List<Bid> bidList) {
    this.bidList = bidList;
  }
}
