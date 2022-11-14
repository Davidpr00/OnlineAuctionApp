package com.example.myebay.users.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;


class ProductTest {

  @Test
  public void can_create_instance_of_product(){
    Product product = new Product("name", "description", "url", 2,20, new User());
    assertEquals("description", product.getDescription());
  }

  @Test
  public void can_create_instance_of_user2(){
    Product product = new Product();
    assertNull(product.getDescription());
  }

}