package com.example.service;

import com.example.entity.Admin;
import com.example.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

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

    public <T> T saveUser(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        String tempUsername = admin.getUsername();
        if (tempUsername!=null){
            Admin admin1 = adminRepository.findByUsername(tempUsername);
            if (admin1!=null){
                return (T) ResponseEntity.internalServerError();
            }
        }
        admin.setPassword(hashedPassword(admin.getPassword()));


        adminRepository.save(admin);
        return (T)ResponseEntity.ok(admin);
    }



    public Admin findByUsernameAndPassword(String username, String password) throws NoSuchAlgorithmException {
        password=hashedPassword(password);

        return adminRepository.findByUsernameAndPassword(username,password);
    }


}
