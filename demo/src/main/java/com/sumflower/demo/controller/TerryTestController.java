package com.sumflower.demo.controller;

import com.sumflower.demo.model.StudentLogin;
import com.sumflower.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TerryTestController {

    @Autowired
    StudentService studentService;

    @RequestMapping(path = {"/testMysql"})
    @ResponseBody
    public String test() {
        StudentLogin student = studentService.getStudent("666");

        return student.getStudentName();
    }

}
