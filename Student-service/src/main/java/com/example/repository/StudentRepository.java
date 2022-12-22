package com.example.repository;

import com.example.entity.Result;
import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByUsernameAndPassword(String tempUsername, String tempPassword);


    Student findByUsername(String username);

    @Query("SELECT r FROM Result r " +
            "WHERE r.student.parent.username = :parentName AND r.student.username = :studentName")
    List<Result> findStudentResults(String parentName,String studentName);

    @Query(value = "SELECT s FROM Student s WHERE s.parent.username = :parentName")
    List<Student> findstudentsOfParent(@Param("parentName") String parentName);

}
