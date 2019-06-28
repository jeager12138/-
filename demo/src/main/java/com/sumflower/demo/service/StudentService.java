package com.sumflower.demo.service;


import com.sumflower.demo.dao.LoginTicketDAO;
import com.sumflower.demo.dao.StudentLoginDAO;
import com.sumflower.demo.model.StudentLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentLoginDAO studentLoginDAO;

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    public StudentLogin getStudent(String studentNumber) {
        return studentLoginDAO.selectByStudentNumber(studentNumber);
    }

    public Map<String, Object> register(String userName, String passwords, String studentName, String college, String major, String EntryYear, String phone, String email) {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

}
