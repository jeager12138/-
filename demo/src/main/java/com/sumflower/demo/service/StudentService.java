package com.sumflower.demo.service;


import com.sumflower.demo.dao.StudentLoginDAO;
import com.sumflower.demo.model.StudentLogin;
import com.sumflower.demo.util.SunflowerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentLoginDAO studentLoginDAO;

    @Autowired
    UserService userService;

    public StudentLogin getStudent(String studentNumber) {
        return studentLoginDAO.selectByStudentNumber(studentNumber);
    }

    public Map<String, Object> register(String userName, String passwords, String studentName, String college, String major, String entryYear, String phone, String email) {
        Map<String, Object> map = new HashMap<>();

        StudentLogin student = studentLoginDAO.selectByStudentNumber(userName);
        if (student != null) {
            map.put("msg", "用户名已被注册");
            return map;
        }
        student = new StudentLogin();
        student.setStudentNumber(userName);
        student.setStudentName(studentName);
        student.setCollege(college);
        student.setEmail(email);
        student.setEntryYear(entryYear);
        student.setMajor(major);
        student.setPhone(phone);
        student.setSalt(UUID.randomUUID().toString().substring(0, 5));
        student.setPassword(SunflowerUtil.MD5(passwords + student.getSalt()));
        studentLoginDAO.addStudent(student);

        String ticket = userService.addLoginTicket(student.getId(), 1);
        map.put("ticket", ticket);

        return map;
    }

    public Map<String, Object> login(String userName, String passwords) {
        Map<String, Object> map = new HashMap<>();
        StudentLogin student = studentLoginDAO.selectByStudentNumber(userName);

        if (student == null) {
            map.put("msg", "用户名不存在");
            return map;
        }

        if (!SunflowerUtil.MD5(passwords + student.getSalt()).equals(student.getPassword())) {
            map.put("msg", "密码不正确");
            return map;
        }

        String ticket = userService.addLoginTicket(student.getId(), 1);
        map.put("ticket", ticket);
        return map;
    }

}
