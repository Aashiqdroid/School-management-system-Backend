package com.example.controller;

import com.example.entity.Parent;
import com.example.entity.Student;
import com.example.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/parent")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ParentController {

    @Autowired
    private ParentService parentService;

    @PostMapping("")
    public ResponseEntity registerTeacher(@RequestBody Parent parent) throws NoSuchAlgorithmException {
        parentService.saveUser(parent);
        return ResponseEntity.ok(parent);
    }

    @GetMapping("login/{username}/{password}")
    public ResponseEntity adminLogin(@PathVariable String username,
                                     @PathVariable String password) throws NoSuchAlgorithmException {


        if (username != null && password != null){
            Parent existUser = parentService.findByUsernameAndPassword(username,password);
            if (existUser!=null){
                return ResponseEntity.ok(existUser);
            }
        }
        return   ResponseEntity.internalServerError().build();
    }
    @GetMapping("")
    public List<Parent> getAllParents(){
        return parentService.getAllParent();
    }

    @GetMapping("{parentName}")
    public List<Student> getCommentsOnStudents(@PathVariable String parentName){
        return parentService.getCommentsOnStudents(parentName);
    }

}
