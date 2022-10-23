package com.example.myebay.users.controllers;

import com.example.myebay.common.dtos.RegisterRequestDto;
import com.example.myebay.users.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity store(RegisterRequestDto registerRequestDto){
    userService.validateAndRegister(registerRequestDto);
    return ResponseEntity.ok().body("ok");
  }
}
