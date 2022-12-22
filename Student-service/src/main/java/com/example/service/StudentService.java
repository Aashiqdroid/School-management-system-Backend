package com.example.service;

import com.example.entity.Parent;
import com.example.entity.Result;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.repository.ParentRepository;
import com.example.repository.StudentRepository;
import com.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ParentRepository parentRepository;


    public String hashedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest =
                MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[] resultByteArray = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : resultByteArray) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public ResponseEntity saveUser(Student student) throws NoSuchAlgorithmException {
        String tempUsername = student.getUsername();
        if (tempUsername != null) {
            Student student1 = studentRepository.findByUsername(tempUsername);
            if (student1 != null) {
                return ResponseEntity.internalServerError().build();
            }
        }
        student.setPassword(hashedPassword(student.getPassword()));

        studentRepository.save(student);
        return ResponseEntity.ok().build();
    }

    public Student findByUsernameAndPassword(String username, String password) throws NoSuchAlgorithmException {
        password = hashedPassword(password);

        return studentRepository.findByUsernameAndPassword(username, password);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student assignTeacherToStudent(Student student, String teacherName, String parentName) {
        Teacher teacher = teacherRepository.findByUsername(teacherName);
        Parent parent = parentRepository.findByUsername(parentName);
        student.assignTeacher(teacher);
        student.assignParent(parent);
        return studentRepository.save(student);
    }

    public Student assignParentToStudent(Student student, String parentName) {
        Parent parent = parentRepository.findByUsername(parentName);
        student.assignParent(parent);
        return studentRepository.save(student);
    }

    public void updateStudent(Long id, Student student) {
        Student updatedStudent = studentRepository.findById(id).get();
        updatedStudent.setUsername(student.getUsername());
        updatedStudent.setAddress(student.getAddress());
        updatedStudent.setAge(student.getAge());
        updatedStudent.setMobile(student.getMobile());
        updatedStudent.setGuardianName(student.getGuardianName());
        updatedStudent.setTeacherComment(student.getTeacherComment());
        studentRepository.save(updatedStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }


    public ResponseEntity<?> addResults(Result result, Long id) {
        Student student =studentRepository.findById(id).get();
        student.assignResult(result);
        return ResponseEntity.ok(studentRepository.save(student));
    }
    public List<Result> getResults(String parentName,String studentName){
        return  studentRepository.findStudentResults(parentName,studentName);
    }
    public List<Student> getStudentsOfParent(String parentName){
        return  studentRepository.findstudentsOfParent(parentName);
    }
}
