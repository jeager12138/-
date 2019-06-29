package com.sumflower.demo.Controller;

import com.sumflower.demo.service.CommitteeService;
import com.sumflower.demo.service.ExpertService;
import com.sumflower.demo.service.StudentService;
import com.sumflower.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    ExpertService expertService;
    @Autowired
    CommitteeService committeeService;

    @RequestMapping(path = {"/studentReg/"}, method = {RequestMethod.POST})
    @ResponseBody
    public String studentReg(@RequestParam("userName") String userName,
                             @RequestParam("passwords") String passwords,
                             @RequestParam("studentName") String studentName,
                             @RequestParam("college") String college,
                             @RequestParam("major") String major,
                             @RequestParam("entryYear") String entryYear,
                             @RequestParam("phone") String phone,
                             @RequestParam("email") String email,
                             HttpServletResponse response) {
        Map<String, Object> map = studentService.register(userName, passwords, studentName, college, major, entryYear, phone, email);
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath("/");
            response.addCookie(cookie);

            return "success";
        } else {
            return map.get("msg").toString();
        }
    }

    @RequestMapping(path = {"/expertReg/"}, method = {RequestMethod.POST})
    @ResponseBody
    public String expertReg(@RequestParam("email") String email,
                            @RequestParam("passwords") String passwords,
                            HttpServletResponse response) {
        Map<String, Object> map = expertService.register(email, passwords);
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath("/");
            response.addCookie(cookie);

            return "success";
        } else {
            return map.get("msg").toString();
        }
    }

    @RequestMapping(path = {"/login/"}, method = {RequestMethod.POST})
    @ResponseBody
    public String studentLogin(@RequestParam("userName") String userName,
                               @RequestParam("passwords") String passwords,
                               @RequestParam("userType") String userType,
                               HttpServletResponse response) {
        Map<String, Object> map = null;
        if (userType.equals("1")) {
            map = studentService.login(userName, passwords);
        } else if (userType.equals("2")) {
            map = expertService.login(userName, passwords);
        } else {
            map = committeeService.login(userName, passwords);
        }

        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath("/");
            response.addCookie(cookie);

            return "success";
        } else {
            return map.get("msg").toString();
        }
    }


}

