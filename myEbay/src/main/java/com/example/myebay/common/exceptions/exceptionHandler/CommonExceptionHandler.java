package com.example.myebay.common.exceptions.exceptionHandler;

import com.example.myebay.common.dtos.ErrorResponseDto;
import com.example.myebay.common.exceptions.AccountAlreadyVerifiedException;
import com.example.myebay.common.exceptions.AllFieldsMustBeProvidedException;
import com.example.myebay.common.exceptions.EmailIsMissingException;
import com.example.myebay.common.exceptions.EmailTooShortException;
import com.example.myebay.common.exceptions.InvalidLoginCredentialsException;
import com.example.myebay.common.exceptions.InvalidTokenException;
import com.example.myebay.common.exceptions.PasswordIsMissingException;
import com.example.myebay.common.exceptions.PriceMustBePositiveException;
import com.example.myebay.common.exceptions.UsernameTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

  private final Environment environment;

  @Autowired
  protected CommonExceptionHandler(Environment environment) {
    this.environment = environment;
  }

  @ExceptionHandler(value = {AllFieldsMustBeProvidedException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleAllFieldsMustBeProvidedException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.fields_not_provided"));
  }

  @ExceptionHandler(value = {EmailIsMissingException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleEmailIsMissingException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.email_is_missing"));
  }

  @ExceptionHandler(value = {EmailTooShortException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleEmailTooShortException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.email_too_short"));
  }

  @ExceptionHandler(value = {UsernameTakenException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleUsernameTakenException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.username_taken"));
  }

  @ExceptionHandler(value = {PasswordIsMissingException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handlePasswordIsMissingException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.password_is_missing"));
  }

  @ExceptionHandler(value = {InvalidLoginCredentialsException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleInvalidLoginCredentialsException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.invalid_login_credentials"));
  }

  @ExceptionHandler(value = {InvalidTokenException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleInvalidTokenException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.invalid_token"));
  }

  @ExceptionHandler(value = {AccountAlreadyVerifiedException.class})
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public ErrorResponseDto handleAccountAlreadyVerifiedException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.account_already_verified"));
  }

  @ExceptionHandler(value = {PriceMustBePositiveException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handlePriceMustBePositiveException() {
    return new ErrorResponseDto(environment.getProperty("config.errors.price_must_be_positive"));
  }
}
