package com.example.myebay.users.repositories;

import com.example.myebay.users.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Product findProductById(long id);
}
