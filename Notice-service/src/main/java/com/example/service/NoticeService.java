package com.example.service;

import com.example.entity.Notice;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.repository.NoticeRepository;
import com.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    public Notice assignTNoticeToTeacher(Notice notice, String username) {
        Teacher teacher = teacherRepository.findByUsername(username);
        notice.assignTeacher(teacher);
        return noticeRepository.save(notice);


    }
}
