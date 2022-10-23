package com.example.myebay.Models;

import static org.junit.jupiter.api.Assertions.*;

import com.example.myebay.users.models.User;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  public void can_create_instance_of_user(){
    User user = new User("username","password","email");
    assertEquals("password", user.getPassword());
  }

  @Test
  public void can_create_instance_of_user2(){
    User user = new User();
    assertNull(user.getPassword());
  }

}