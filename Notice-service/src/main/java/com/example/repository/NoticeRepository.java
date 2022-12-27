package com.example.repository;

import com.example.entity.Notice;
import com.example.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {
    @Query("SELECT n FROM Notice n ORDER BY n.date DESC")
    List<Notice> findAllOverRide();

}
