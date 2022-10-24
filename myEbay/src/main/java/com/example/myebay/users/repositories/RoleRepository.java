package com.example.myebay.users.repositories;

import com.example.myebay.users.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
Role findRoleByName(String name);
}
