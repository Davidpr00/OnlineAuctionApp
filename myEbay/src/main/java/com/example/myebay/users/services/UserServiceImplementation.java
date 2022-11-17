package com.example.myebay.users.services;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.myebay.common.dtos.LoginRequestDto;
import com.example.myebay.common.dtos.RegisterRequestDto;
import com.example.myebay.common.dtos.StatusDto;
import com.example.myebay.common.dtos.UserResponseDto;
import com.example.myebay.common.exceptions.AccountAlreadyVerifiedException;
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
  private final EmailSender emailSender;

  public UserServiceImplementation(UserRepository userRepository, RoleRepository roleRepository,
      EmailSender emailSender) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.emailSender = emailSender;
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
      sendVerificationEmail(registerRequestDto.getUsername());
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
    User user = userRepository.findUserByVerificationToken(verificationToken);
    if(user == null ){
      throw new InvalidTokenException();
    } else if ( user.getVerifiedAt() != null) {
      throw new AccountAlreadyVerifiedException();
    } else {
      LocalDateTime expirationDate = LocalDateTime.parse(user.getVerificationTokenExpiration());
      if(LocalDateTime.now().isBefore(expirationDate)){
        verifyVerificationEmail(user);
      }else {
        throw new TokenExpiredException("Expired token", null);
      }
    }
    return new StatusDto("Account verified successfully");
  }
  @Override
  public void sendVerificationEmail(String username){
    User user = userRepository.findUserByUsername(username);
    user.setVerificationToken(UUID.randomUUID().toString());
    user.setVerificationTokenExpiration(LocalDateTime.now().plusHours(1).toString()); //token is valid for 1 hour
    triggerVerificationMail(user);
    userRepository.save(user);
  }
  @Override
  public void triggerVerificationMail(User user){
    final String bodyOfEmail =
        "<html> <body> <p>Dear " + user.getUsername() + ",</p><p>Click on the link below to complete the registration to MyEbay.com"
            + "<br><br>"
            + "<table border='1' width='300px' style='text-align:center;font-size:20px;'><tr> <td colspan='2'>"
            + "</td></tr><tr><td>Click to verify:</td><td>"
            + "<a href=\""
            + "localhost:8080/verification/"
            + user.getVerificationToken()
            + "\">link text</a>"
            + "</td></tr></table> </body></html>";

    emailSender.send(
          emailSender.constructEmail("MyEbay Account Verification",
                                     bodyOfEmail,
                                      user)
    );
    //send email
  }

  @Override
  public void verifyVerificationEmail(User user){
      user.setVerifiedAt(LocalDateTime.now().toString());
      userRepository.save(user);
  }
  @Override
  public User findUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }
  @Override
  public boolean existsByEmail(String email) {
    return userRepository.existsUserByEmail(email);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
    User user = userRepository.findUserByUsername(username);
    Role role = roleRepository.findRoleByName(roleName);
    user.getRoles().add(role);
    userRepository.save(user);
  }
}
