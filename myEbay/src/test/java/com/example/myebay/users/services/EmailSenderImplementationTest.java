package com.example.myebay.users.services;

import com.example.myebay.users.models.User;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

class EmailSenderImplementationTest {
  private final JavaMailSender javaMailSender = Mockito.mock(JavaMailSender.class);
  private final EmailSender emailSender = new EmailSenderImplementation(javaMailSender);

  @Test
  void constructEmail() {
    SimpleMailMessage simpleMailMessage = emailSender.constructEmail("testSubject","testBody",new User());
    assertEquals(simpleMailMessage.getSubject(), "testSubject");
    assertEquals(simpleMailMessage.getText(), "testBody");
  }
}