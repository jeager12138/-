package com.sumflower.demo.Controller;

import com.sumflower.demo.model.StudentLogin;
import com.sumflower.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@CrossOrigin
@Controller
public class TerryTestController {

    @Autowired
    StudentService studentService;

    @RequestMapping(path = {"/api/post"})
    @ResponseBody
    public  Map post( @RequestBody Map map)
    {
        System.out.println(map.get("id"));
        return  map;
    }

    @RequestMapping(path = {"/api/get"})
    @ResponseBody
    public  int  get( HttpServletRequest res)
    {
        System.out.println(res.getParameter("b"));
        return  234348385;
    }


    @RequestMapping(path = {"/testMysql"})
    @ResponseBody
    public String test() {
        StudentLogin student = studentService.getStudent("666");

        return student.getStudentName();
    }

}
