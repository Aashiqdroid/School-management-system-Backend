package com.example.controller;

import com.example.entity.Admin;
import com.example.repository.AdminRepository;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)


public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("")
    public ResponseEntity<?> saveUser(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        adminService.saveUser(admin);
        return ResponseEntity.ok(admin) ;
    }


    @PostMapping("login")
    public ResponseEntity adminLogin(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        String tempUsername = admin.getUsername();
        String tempPassword = admin.getPassword();

        if (tempUsername != null && tempPassword != null){
            Admin existUser = adminService.findByUsernameAndPassword(tempUsername,tempPassword);
            if (existUser!=null){
                return ResponseEntity.ok(admin);
            }
        }
        return   ResponseEntity.internalServerError().build();
    }
    @GetMapping("login/{username}/{password}")
    public ResponseEntity adminLog(@PathVariable String username,
                                   @PathVariable String password) throws NoSuchAlgorithmException {

        if (username != null && password != null){
            Admin existUser = adminService.findByUsernameAndPassword(username,password);
            if (existUser!=null){
                return ResponseEntity.ok(existUser);
            }
        }
        return   ResponseEntity.internalServerError().build();

    }
}
