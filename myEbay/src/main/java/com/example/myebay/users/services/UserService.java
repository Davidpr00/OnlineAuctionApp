package com.example.myebay.users.services;

import com.example.myebay.common.dtos.LoginRequestDto;
import com.example.myebay.common.dtos.RegisterRequestDto;
import com.example.myebay.common.dtos.StatusDto;
import com.example.myebay.common.dtos.UserResponseDto;
import com.example.myebay.users.models.User;

public interface UserService {

  void validateAndRegister(RegisterRequestDto registerRequestDto);

  User loginUser(LoginRequestDto loginRequestDto);

  UserResponseDto findUserById(Long id);

  StatusDto verifyUser(String verificationToken);

  void sendVerificationEmail(String username);

  void triggerVerificationMail(User user);

  void verifyVerificationEmail(User user);

  User findUserByEmail(String email);

  boolean existsByEmail(String email);

  void addRoleToUser(String username, String roleName);
}
