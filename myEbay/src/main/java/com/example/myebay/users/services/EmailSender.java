package com.example.myebay.users.services;

import com.example.myebay.users.models.User;
import org.springframework.mail.SimpleMailMessage;

public interface EmailSender {
  SimpleMailMessage constructEmail(String subject, String body, User user);

  void send(SimpleMailMessage message);
}
