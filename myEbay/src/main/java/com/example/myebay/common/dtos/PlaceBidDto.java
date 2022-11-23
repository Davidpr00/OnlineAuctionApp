package com.example.myebay.common.dtos;

public class PlaceBidDto {
  private long bid;

  public PlaceBidDto(long bid) {
    this.bid = bid;
  }

  public PlaceBidDto() {
  }

  public long getBid() {
    return bid;
  }

  public void setBid(long bid) {
    this.bid = bid;
  }
}
