package com.example.myebay.users.controllers;

import com.example.myebay.common.dtos.LoginRequestDto;
import com.example.myebay.common.dtos.RegisterRequestDto;
import com.example.myebay.security.JwtUtil;
import com.example.myebay.users.models.User;
import com.example.myebay.users.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody RegisterRequestDto registerRequestDto) {
    userService.validateAndRegister(registerRequestDto);
    return ResponseEntity.ok().body("ok");
  }

  @PostMapping("/verification/{token}")
  public ResponseEntity verify(@PathVariable String token) {
    return ResponseEntity.ok().body(userService.verifyUser(token));
  }

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {
    ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
    builder.scheme("http");
    String urlPath = builder.build().toString();
    User user = userService.loginUser(loginRequestDto);
    JwtUtil jwtUtil = new JwtUtil();
    final String jwt = jwtUtil.createAccess(user, urlPath);
    return ResponseEntity.status(200).body(jwt);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity show(@PathVariable Long id) {
    return ResponseEntity.ok().body(userService.findUserById(id));
  }

  @PostMapping("/users/{id}")
  public ResponseEntity store(@RequestBody RegisterRequestDto registerRequestDto) {
    userService.validateAndRegister(registerRequestDto);
    return ResponseEntity.ok().body("ok");
  }
}
