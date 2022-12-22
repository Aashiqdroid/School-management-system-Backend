package com.example.service;

import com.example.entity.Parent;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.repository.ParentRepository;
import com.example.repository.StudentRepository;
import com.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ParentRepository parentRepository;
    public String hashedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest=
                MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[] resultByteArray = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b: resultByteArray){
            sb.append(String.format("%02x",b));
        }
        return sb.toString();
    }


    public <T> T saveUser(@RequestBody Teacher teacher) throws NoSuchAlgorithmException {
        String tempUsername = teacher.getUsername();
        if (tempUsername!=null){
            Teacher admin1 = teacherRepository.findByUsername(tempUsername);
            if (admin1!=null){
                return (T) ResponseEntity.internalServerError();
            }
        }
        teacher.setPassword(hashedPassword(teacher.getPassword()));


        teacherRepository.save(teacher);
        return (T)ResponseEntity.ok(teacher);
    }

    public Teacher findByUsernameAndPassword(String username, String password) throws NoSuchAlgorithmException {
        password=hashedPassword(password);

        return teacherRepository.findByUsernameAndPassword(username,password);
    }

    public List<Teacher> findAllTeacher() {
        return teacherRepository.findAll();
    }

    public void updateTeacher(String teacherName,String parentName, Student student) {
        Teacher updatedTeacher = teacherRepository.findByUsername(teacherName);
        Parent parent = parentRepository.findByUsername(parentName);
        updatedTeacher.studentArrayList.add(student);
        student.assignTeacher(updatedTeacher);
        student.assignParent(parent);
        studentRepository.save(student);
        teacherRepository.save(updatedTeacher);
    }
}
