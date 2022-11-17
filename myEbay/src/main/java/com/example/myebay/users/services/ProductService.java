package com.example.myebay.users.services;

import com.example.myebay.common.dtos.ProductRequestDto;
import com.example.myebay.common.dtos.ProductResponseDto;
import java.util.List;

public interface ProductService {
  ProductResponseDto createSellableProduct(ProductRequestDto productRequestDto, String token);

  boolean isValidUrl(String url);

  List<ProductResponseDto> showSellableProducts(Integer page);
}
