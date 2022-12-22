package com.example.controller;

import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "http://localhost:4200/",maxAge = 3600)
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("")
    public ResponseEntity registerTeacher(@RequestBody Teacher teacher) throws NoSuchAlgorithmException {
        teacherService.saveUser(teacher);
        return ResponseEntity.ok(teacher);
    }
    @GetMapping("login/{username}/{password}")
    public ResponseEntity teacherLog(@PathVariable String username,
                                   @PathVariable String password) throws NoSuchAlgorithmException {

        if (username != null && password != null){
            Teacher existUser = teacherService.findByUsernameAndPassword(username,password);
            if (existUser!=null){
                return ResponseEntity.ok(existUser);
            }
        }
        return   ResponseEntity.internalServerError().build();
    }
    @GetMapping("")
    public List<Teacher> getAllTeachers(){
        return teacherService.findAllTeacher();
    }

    @PutMapping("/{teacherName}/{parentName}")
    public ResponseEntity<?> updateStudent(@PathVariable String teacherName,
                                           @PathVariable String parentName,
                                           @RequestBody Student student) {
        teacherService.updateTeacher(teacherName,parentName, student);
        return ResponseEntity.ok(student);
    }

}
