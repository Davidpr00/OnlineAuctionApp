package com.example.myebay.users.repositories;

import com.example.myebay.users.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Long, Product> {

}
