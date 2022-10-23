package com.example.myebay.users.services;

import com.example.myebay.common.dtos.RegisterRequestDto;
import com.example.myebay.common.exceptions.AllFieldsMustBeProvidedException;
import com.example.myebay.common.exceptions.EmailTakenException;
import com.example.myebay.common.exceptions.EmailTooShortException;
import com.example.myebay.common.exceptions.UsernameTakenException;
import com.example.myebay.users.models.User;
import com.example.myebay.users.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

  private final UserRepository userRepository;

  public UserServiceImplementation(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void validateAndRegister(RegisterRequestDto registerRequestDto) {
    if(registerRequestDto.getEmail().length() > 5){
      throw new EmailTooShortException();
    }
    else if (registerRequestDto.getEmail() == null || registerRequestDto.getPassword() == null || registerRequestDto.getUsername() == null ){
      throw new AllFieldsMustBeProvidedException();
    }
    else if (userRepository.findUserByEmail(registerRequestDto.getEmail()) != null){
      throw new EmailTakenException();
    }
    else if (userRepository.findUserByUsername(registerRequestDto.getUsername()) != null){
      throw new UsernameTakenException();
    }
    else {
      userRepository.save(new User(registerRequestDto.getUsername(), registerRequestDto.getPassword(), registerRequestDto.getEmail()));
    }
  }
}
