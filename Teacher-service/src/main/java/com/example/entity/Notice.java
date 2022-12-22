package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    private Teacher teacher;

    public void assignTeacher(Teacher teacher) {
        this.teacher=teacher;}

}
