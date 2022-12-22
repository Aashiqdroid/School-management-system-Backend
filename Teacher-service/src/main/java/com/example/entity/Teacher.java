package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String mobile;
    private String subjectTaken;

    @OneToMany(mappedBy = "teacher")
    public List<Student> studentArrayList = new ArrayList<Student>();

    @OneToMany(mappedBy = "teacher")
    public List<Notice> noticeArrayList = new ArrayList<Notice>();


}
