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
import java.util.HashMap;
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
    public Map<String, Object> studentReg(@RequestBody Map m,
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
        Map<String, Object> ret = new HashMap<>();
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath("/");
            response.addCookie(cookie);
            ret.put("ticket", map.get("ticket").toString());
            ret.put("user", map.get("user"));
            ret.put("msg", "success");
            return ret;
        } else {
            ret.put("msg", "fail");
            return ret;
        }
    }

    @RequestMapping(path = {"/expertReg/"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> expertReg(@RequestBody Map m,
                            HttpServletResponse response) {
        String email = (m.get("email")).toString();
        logger.error(m.get("email").toString());
        String passwords = (m.get("passwords")).toString();
        logger.error(m.get("passwords").toString());

        Map<String, Object> map = expertService.register(email, passwords);
        Map<String, Object> ret = new HashMap<>();
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath("/");
            response.addCookie(cookie);

            ret.put("ticket", map.get("ticket").toString());
            ret.put("user", map.get("user"));
            ret.put("msg", "success");
            return ret;
        } else {
            ret.put("msg", "fail");
            return ret;
        }
    }


    @RequestMapping(path = {"/login/"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> studentLogin(@RequestBody Map m,
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

        Map<String, Object> ret = new HashMap<>();


        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath("/");
            response.addCookie(cookie);

            ret.put("ticket", map.get("ticket").toString());
            ret.put("user", map.get("user"));
            ret.put("msg", "success");
            return ret;
        } else {
            ret.put("msg", "fail");
            return ret;
        }
    }

}

