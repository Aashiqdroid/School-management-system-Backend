package com.example.repository;

import com.example.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    Teacher findByUsernameAndPassword(String tempUsername, String tempPassword);

    Teacher findByUsername(String username);


}