package com.example.myebay.users.services;

import com.example.myebay.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSenderImplementation implements EmailSender{
  @Autowired
  private JavaMailSender mailSender;

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

