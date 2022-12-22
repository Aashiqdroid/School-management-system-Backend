package com.example.service;

import com.example.entity.Parent;
import com.example.entity.Student;
import com.example.repository.ParentRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class ParentService {
    @Autowired
    ParentRepository parentRepository;
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

    public <T> T saveUser(@RequestBody Parent parent) throws NoSuchAlgorithmException {
        String tempUsername = parent.getUsername();
        if (tempUsername!=null){
            Parent admin1 = parentRepository.findByUsername(tempUsername);
            if (admin1!=null){
                return (T) ResponseEntity.internalServerError();
            }
        }
        parent.setPassword(hashedPassword(parent.getPassword()));


        parentRepository.save(parent);
        return (T)ResponseEntity.ok(parent);
    }

    public Parent findByUsernameAndPassword(String tempUsername, String tempPassword) throws NoSuchAlgorithmException {
        tempPassword=hashedPassword(tempPassword);

        return parentRepository.findByUsernameAndPassword(tempUsername,tempPassword);
    }

    public List<Parent> getAllParent() {
        return parentRepository.findAll();
    }

    public List<Student> getCommentsOnStudents(String parentName){
        return parentRepository.getCommentsOnStudents(parentName);
    }
}
