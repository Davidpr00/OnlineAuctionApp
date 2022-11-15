package com.example.myebay.users.repositories;

import com.example.myebay.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findUserByEmail(String email);

  User findUserByUsername(String username);

  boolean existsUserByEmail(String email);

  User findUserById(Long id);
  User findUserByVerificationToken(String token);
}
