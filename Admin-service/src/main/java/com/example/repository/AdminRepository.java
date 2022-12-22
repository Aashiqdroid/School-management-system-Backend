package com.example.repository;

import com.example.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsernameAndPassword(String tempUsername, String tempPassword);
    Admin findByUsername(String username);

}
