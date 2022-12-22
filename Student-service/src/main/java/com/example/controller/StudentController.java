package com.example.controller;

import com.example.entity.Result;
import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200/",maxAge = 3600)
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PutMapping("")
    public ResponseEntity registerStudent(@RequestBody Student student) throws NoSuchAlgorithmException {
        studentService.saveUser(student);
        return ResponseEntity.ok(student);
    }


    @GetMapping("login/{username}/{password}")
    public ResponseEntity studentLog(@PathVariable String username,
                                     @PathVariable String password) throws NoSuchAlgorithmException {

        if (username != null && password != null){
            Student existUser = studentService.findByUsernameAndPassword(username,password);
            if (existUser!=null){
                return ResponseEntity.ok(existUser);
            }
        }
        return   ResponseEntity.internalServerError().build();

    }
    @GetMapping("")
    public List<Student> getAllStudents(){
        return studentService.findAllStudents();
    }

    //Assigning students with teacher
    @PostMapping("assign/{teacherName}/{parentName}")
    Student assignTeacherToStudent(
            @RequestBody Student student,
            @PathVariable String teacherName,
            @PathVariable String parentName
    ){
        return studentService.assignTeacherToStudent(student,teacherName,parentName);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.ok(student);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok(id);
    }
    @PutMapping("addresult/{id}")
    public ResponseEntity<?> addResults(@RequestBody Result result,
                                        @PathVariable Long id){
        return studentService.addResults(result,id);
    }
    @GetMapping("{parentName}/{studentName}")
    public ResponseEntity<List<Result>> getCommentsOnStudents(@PathVariable String parentName,
                                                              @PathVariable String studentName){
        return ResponseEntity.ok(studentService.getResults(parentName,studentName));
    }
    @GetMapping("{parentName}")
    public List<Student> getStudentsOfParent(@PathVariable String parentName){
        return studentService.getStudentsOfParent(parentName);
    }
}
