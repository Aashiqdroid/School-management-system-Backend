package com.example.repository;

import com.example.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent,Long> {

    Parent findByUsernameAndPassword(String tempUsername, String tempPassword);

    Parent findByUsername(String username);


}
