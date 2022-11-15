package com.example.myebay.users.services;

import com.example.myebay.common.dtos.LoginRequestDto;
import com.example.myebay.common.dtos.RegisterRequestDto;
import com.example.myebay.common.dtos.StatusDto;
import com.example.myebay.common.dtos.UserRequestDto;
import com.example.myebay.common.dtos.UserResponseDto;
import com.example.myebay.common.exceptions.AllFieldsMustBeProvidedException;
import com.example.myebay.common.exceptions.EmailIsMissingException;
import com.example.myebay.common.exceptions.EmailTakenException;
import com.example.myebay.common.exceptions.EmailTooShortException;
import com.example.myebay.common.exceptions.InvalidLoginCredentialsException;
import com.example.myebay.common.exceptions.InvalidTokenException;
import com.example.myebay.common.exceptions.PasswordIsMissingException;
import com.example.myebay.common.exceptions.UsernameTakenException;
import com.example.myebay.users.models.Role;
import com.example.myebay.users.models.User;
import com.example.myebay.users.repositories.RoleRepository;
import com.example.myebay.users.repositories.UserRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserServiceImplementation(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  public void validateAndRegister(RegisterRequestDto registerRequestDto) {
    if (registerRequestDto.getEmail() == null
        || registerRequestDto.getPassword() == null
        || registerRequestDto.getUsername() == null) {
      throw new AllFieldsMustBeProvidedException();
    } else if (registerRequestDto.getEmail().length() < 5) {
      throw new EmailTooShortException();
    } else if (userRepository.findUserByEmail(registerRequestDto.getEmail()) != null) {
      throw new EmailTakenException();
    } else if (userRepository.findUserByUsername(registerRequestDto.getUsername()) != null) {
      throw new UsernameTakenException();
    } else {
      userRepository.save(
          new User(
              registerRequestDto.getUsername(),
              registerRequestDto.getPassword(),
              registerRequestDto.getEmail()));
    }
  }

  @Override
  public User loginUser(LoginRequestDto loginRequestDto) {
    if (loginRequestDto.getEmail() == null) {
      throw new EmailIsMissingException();
    } else if (loginRequestDto.getPassword() == null) {
      throw new PasswordIsMissingException();
    } else if (!existsByEmail(loginRequestDto.getEmail())
        || !findUserByEmail(loginRequestDto.getEmail())
            .getPassword()
            .equals(loginRequestDto.getPassword())) {
      throw new InvalidLoginCredentialsException();
    }
    return findUserByEmail(loginRequestDto.getEmail());
  }

  @Override
  public UserResponseDto findUserById(Long id) {
    User user = userRepository.findUserById(id);
    return new UserResponseDto(
        user.getUsername(),
        user.getEmail(),
        user.getCreationDate(),
        user.getVerifiedAt(),
        user.getDollarsAmount(),
        user.getRolesString(),
        user.getProducts());
  }

  @Override
  public StatusDto verifyUser(String verificationToken) {
    User userToBeVerified = userRepository.findUserByVerificationToken(verificationToken);
    if(userToBeVerified == null ){
      throw new InvalidTokenException();
    } else {
      userToBeVerified.setVerifiedAt(LocalDateTime.now().toString());
    }
    return new StatusDto("Account verified successfully");
  }

  public StatusDto sendVerificationEmail(String username){
    User user = userRepository.findUserByUsername(username);
    user.setVerificationToken(UUID.randomUUID().toString());
    //TODO: finish verification email sending and logic of verifying. TJ: set tokenexpiration, is it expired? if not verify.
  }

  public User findUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsUserByEmail(email);
  }

  public void addRoleToUser(String username, String roleName) {
    User user = userRepository.findUserByUsername(username);
    Role role = roleRepository.findRoleByName(roleName);
    user.getRoles().add(role);
    userRepository.save(user);
  }
}
