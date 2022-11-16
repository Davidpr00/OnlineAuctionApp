package com.example.myebay.users.services;

import com.example.myebay.users.models.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImplementation implements EmailSender{

  private final JavaMailSender mailSender;

  public EmailSenderImplementation(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public SimpleMailMessage constructEmail(String subject, String body, User user) {
    SimpleMailMessage email = new SimpleMailMessage();
    email.setSubject(subject);
    email.setText(body);
    email.setTo(user.getEmail());
    return email;
  }

  @Override
  public void send(SimpleMailMessage message) {
    mailSender.send(message);
  }

}

