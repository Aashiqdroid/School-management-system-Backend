package com.example.controller;

import com.example.entity.Notice;
import com.example.entity.Student;
import com.example.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/notice")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //Assigning Teacher with notice
    @PostMapping("assign/{teacherName}")
    Notice assignTeacherToStudent(
            @RequestBody Notice notice,
            @PathVariable String teacherName
    ){
        notice.setDate(new Date());
        return noticeService.assignTNoticeToTeacher(notice,teacherName);
    }
    @GetMapping("")
    public List<Notice> getAllNotices(){
        return noticeService.getAllNotices();
    }
}
