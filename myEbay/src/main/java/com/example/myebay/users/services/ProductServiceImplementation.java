package com.example.myebay.users.services;

import com.example.myebay.common.dtos.BidResponseDto;
import com.example.myebay.common.dtos.ErrorResponseDto;
import com.example.myebay.common.dtos.ProductRequestDto;
import com.example.myebay.common.dtos.ProductResponseAbstract;
import com.example.myebay.common.dtos.ProductResponseDto;
import com.example.myebay.common.dtos.ProductResponseSoldItemDto;
import com.example.myebay.common.dtos.ProductResponseWithListOfBidsDto;
import com.example.myebay.common.exceptions.AllFieldsMustBeProvidedException;
import com.example.myebay.common.exceptions.PriceMustBePositiveException;
import com.example.myebay.security.JwtUtil;
import com.example.myebay.users.models.Bid;
import com.example.myebay.users.models.Product;
import com.example.myebay.users.models.User;
import com.example.myebay.users.repositories.BidRepository;
import com.example.myebay.users.repositories.ProductRepository;
import com.example.myebay.users.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImplementation implements ProductService {
  private final JwtUtil jwtUtil;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;
  private final BidRepository bidRepository;

  public ProductServiceImplementation(
      JwtUtil jwtUtil, UserRepository userRepository, ProductRepository productRepository,
      BidRepository bidRepository) {
    this.jwtUtil = jwtUtil;
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.bidRepository = bidRepository;
  }

  @Override
  public ProductResponseDto createSellableProduct(
      ProductRequestDto productRequestDto, String token) {
    if(productRequestDto.getName() == null || productRequestDto.getDescription() == null
       || productRequestDto.getUrl() == null || productRequestDto.getPurchasePrice() == 0){
      throw new AllFieldsMustBeProvidedException();
    } else if(productRequestDto.getPurchasePrice() < 0 || productRequestDto.getStartingPrice() < 0){
      throw new PriceMustBePositiveException();
    }
    String username = jwtUtil.decodeToToken(token).getName();
    User user = userRepository.findUserByUsername(username);
    Product product =
        new Product(
            productRequestDto.getName(),
            productRequestDto.getDescription(),
            productRequestDto.getUrl(),
            user.getUsername(),
            productRequestDto.getStartingPrice(),
            productRequestDto.getPurchasePrice(),
            user);
    productRepository.save(product);
    user.getProducts().add(product);
    userRepository.save(user);
    return new ProductResponseDto(
        product.getName(),
        product.getDescription(),
        product.getUrl(),
        product.getSeller(),
        product.getStartingPrice(),
        product.getPurchasePrice());
  }

  @Override
  public boolean isValidUrl(String url) {
    return false;
  }

  @Override
  public List<ProductResponseDto> showSellableProducts(Integer page) {
    if(page==null){
      page=0;
    }
    List<ProductResponseDto> list = new ArrayList<>();
    PageRequest firstPageWithTwoElements = PageRequest.of(page, 2);
    for ( Product product : productRepository.findAll(firstPageWithTwoElements) ) {
        if(!product.isSold()){
          list.add(new ProductResponseDto(product.getName(), product.getDescription(), product.getUrl(), product.getSeller(),
              product.getStartingPrice(), product.getPurchasePrice()));
        }
    }

    return list;
  }

  @Override
  public ProductResponseAbstract showOneProduct(long id) throws NotFoundException {
    Product product = productRepository.findProductById(id);
    if(product == null){
      throw new NotFoundException();
    } else if(product.isSold()){
      return new ProductResponseSoldItemDto(
          product.getName(),
          product.getDescription(),
          product.getUrl(),
          product.getBuyer(),
          product.getPurchasePrice(),
          product.getSeller());
    } else {
      return new ProductResponseWithListOfBidsDto(
          product.getName(),
          product.getDescription(),
          null,
          product.getUrl(),
          product.getPurchasePrice(),
          product.getSeller());
    }
  }
  @Override
  public ProductResponseWithListOfBidsDto placeBid(long id, String token, long bidAmount) {
    User user = userRepository.findUserByUsername(jwtUtil.decodeToToken(token).getName());
    Product product = productRepository.findProductById(id);
    Bid bid = new Bid(user.getUsername(), bidAmount,product,user);
    bidRepository.save(bid);
    List<BidResponseDto> list =
        product.getBidList().stream().map(
            bid1 -> {
              return new BidResponseDto(bid.getUsername(), bid.getAmount());
            }).toList();
    return new ProductResponseWithListOfBidsDto(product.getName(), product.getDescription(), list,
        product.getUrl(), product.getPurchasePrice(), product.getSeller());
  }
}
