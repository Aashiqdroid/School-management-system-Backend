package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String age;
    private String mobile;
    private String address;
    private String guardianName;
    private String teacherComment;

    @ManyToOne

    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    private Teacher teacher;

    @ManyToOne

    @JoinColumn(name = "parent_id",referencedColumnName = "id")
    private Parent parent;

    public void assignTeacher(Teacher teacher) {

        this.teacher=teacher;

    }

    public void assignParent(Parent parent) {

        this.parent=parent;

    }
}
