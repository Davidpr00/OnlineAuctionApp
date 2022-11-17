package com.example.myebay.users.controllers;

import com.example.myebay.common.dtos.ProductRequestDto;
import com.example.myebay.security.JwtUtil;
import com.example.myebay.users.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
  private final ProductService productService;
  private final JwtUtil jwtUtil;

  public ProductController(ProductService productService, JwtUtil jwtUtil) {
    this.productService = productService;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/products")
  public ResponseEntity storeProduct(
      @RequestHeader("token") String token, @RequestBody ProductRequestDto productRequestDto) {
    return ResponseEntity.ok(productService.createSellableProduct(productRequestDto, token));
  }
  @GetMapping("/products")
  public ResponseEntity showProducts(@RequestParam(required=false) Integer page) {
    return ResponseEntity.ok(productService.showSellableProducts(page));
  }
}
