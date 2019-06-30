package com.sumflower.demo.Controller;

import com.sumflower.demo.service.CommitteeService;
import com.sumflower.demo.service.ExpertService;
import com.sumflower.demo.service.StudentService;
import com.sumflower.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@CrossOrigin
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

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
    public String studentReg(@RequestBody Map m,
                             HttpServletResponse response) {
        String userName = (m.get("studentNumber")).toString();
        String passwords = (m.get("password")).toString();
        String studentName = (m.get("studentName")).toString();
        String college = (m.get("college")).toString();
        String major = (m.get("major")).toString();
        String entryYear = (m.get("entryYear")).toString();
        String phone = (m.get("mobile")).toString();
        String email = (m.get("email")).toString();
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
    public String expertReg(@RequestBody Map m,
                            HttpServletResponse response) {
        String email = (m.get("email")).toString();
        logger.error(m.get("email").toString());
        String passwords = (m.get("passwords")).toString();
        logger.error(m.get("passwords").toString());

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
    public String studentLogin(@RequestBody Map m,
                               HttpServletResponse response) {
        String userName = (m.get("userName")).toString();
        String passwords = (m.get("passwords")).toString();
        String userType = (m.get("userType")).toString();

        Map<String, Object> map = null;
        if (userType.equals("0")) {
            map = studentService.login(userName, passwords);
        } else if (userType.equals("1")) {
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

