package com.example.myebay.users.services;

import com.example.myebay.common.dtos.RegisterRequestDto;

public interface UserService {

  void validateAndRegister(RegisterRequestDto registerRequestDto);
}
