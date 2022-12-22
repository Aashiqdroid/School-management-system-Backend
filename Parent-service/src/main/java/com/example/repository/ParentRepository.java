package com.example.repository;

import com.example.entity.Parent;
import com.example.entity.Student;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent,Long> {

    Parent findByUsernameAndPassword(String tempUsername, String tempPassword);

    Parent findByUsername(String username);

    @Query("SELECT s FROM Student s WHERE s.parent.username = :parentName")
    List<Student> getCommentsOnStudents(String parentName);
}
