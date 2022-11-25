package com.example.myebay.users.services;

import com.example.myebay.common.dtos.BidResponseDto;
import com.example.myebay.common.dtos.ProductRequestDto;
import com.example.myebay.common.dtos.ProductResponseAbstract;
import com.example.myebay.common.dtos.ProductResponseDto;
import com.example.myebay.common.dtos.ProductResponseWithListOfBidsDto;
import com.example.myebay.users.models.Bid;
import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface ProductService {
  ProductResponseDto createSellableProduct(ProductRequestDto productRequestDto, String token);

  boolean isValidUrl(String url);

  List<ProductResponseDto> showSellableProducts(Integer page);

  ProductResponseAbstract showOneProduct(long id) throws NotFoundException;

  ProductResponseWithListOfBidsDto placeBid(long id, String token, long bidAmount);
  List<BidResponseDto> changeBidlistToDtoList(List<Bid> bidList);
}
