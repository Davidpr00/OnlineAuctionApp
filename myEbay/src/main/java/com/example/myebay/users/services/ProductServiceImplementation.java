package com.example.myebay.users.services;

import com.example.myebay.common.dtos.ProductRequestDto;
import com.example.myebay.common.dtos.ProductResponseDto;
import com.example.myebay.security.JwtUtil;
import com.example.myebay.users.models.Product;
import com.example.myebay.users.models.User;
import com.example.myebay.users.repositories.ProductRepository;
import com.example.myebay.users.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImplementation implements ProductService {
  private final JwtUtil jwtUtil;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  public ProductServiceImplementation(
      JwtUtil jwtUtil, UserRepository userRepository, ProductRepository productRepository) {
    this.jwtUtil = jwtUtil;
    this.userRepository = userRepository;
    this.productRepository = productRepository;
  }

  @Override
  public ProductResponseDto createSellableProduct(
      ProductRequestDto productRequestDto, String token) {
    String username = jwtUtil.decodeToToken(token).getName();
    User user = userRepository.findUserByUsername(username);
    Product product =
        new Product(
            productRequestDto.getName(),
            productRequestDto.getDescription(),
            productRequestDto.getUrl(),
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
        product.getStartingPrice(),
        product.getPurchasePrice());
  }

  @Override
  public boolean isValidUrl(String url) {
    return false;
  }
}
