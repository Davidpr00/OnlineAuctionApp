package com.example.myebay.users.controllers;

import com.example.myebay.common.dtos.PlaceBidDto;
import com.example.myebay.common.dtos.ProductRequestDto;
import com.example.myebay.security.JwtUtil;
import com.example.myebay.users.services.ProductService;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;
  private final JwtUtil jwtUtil;

  public ProductController(ProductService productService, JwtUtil jwtUtil) {
    this.productService = productService;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/")
  public ResponseEntity storeProduct(
      @RequestHeader("token") String token, @RequestBody ProductRequestDto productRequestDto) {
    return ResponseEntity.ok(productService.createSellableProduct(productRequestDto, token));
  }

  @GetMapping("/")
  public ResponseEntity showProducts(@RequestParam(required = false) Integer page) {
    return ResponseEntity.ok(productService.showSellableProducts(page));
  }

  @GetMapping("/{id}")
  public ResponseEntity showOneProduct(@PathVariable long id) throws NotFoundException {
    return ResponseEntity.ok(productService.showOneProduct(id));
  }

  @PostMapping("/bid/{id}")
  public ResponseEntity placeBid(
      @RequestHeader("token") String token,
      @RequestBody PlaceBidDto placeBidDto,
      @PathVariable long id) {
    return ResponseEntity.ok(productService.placeBid(id, token, placeBidDto.getBid()));
  }


  @PostMapping("/purchase/{id}")
  public ResponseEntity purchaseProduct(
      @RequestHeader("token") String token,
      @PathVariable long id) throws NotFoundException {
    return ResponseEntity.ok(productService.purchaseProduct(id, token));
  }
}
