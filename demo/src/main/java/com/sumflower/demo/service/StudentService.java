package com.sumflower.demo.service;


import com.sumflower.demo.dao.StudentLoginDAO;
import com.sumflower.demo.model.StudentLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentLoginDAO studentLoginDAO;




    public StudentLogin getStudent(String studentNumber) {
        return studentLoginDAO.selectByStudentNumber(studentNumber);
    }
}
