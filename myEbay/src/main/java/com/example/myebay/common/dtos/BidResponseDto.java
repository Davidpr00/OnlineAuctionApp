package com.example.myebay.common.dtos;

public class BidResponseDto {
  private String username;
  private long amount;

  public BidResponseDto() {
  }

  public BidResponseDto(String username, long amount) {
    this.username = username;
    this.amount = amount;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }
}
