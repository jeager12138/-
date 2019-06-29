package com.sumflower.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Controller
public class RanderousController {

    @RequestMapping(path = "/setCookies")
    @ResponseBody
    public String setCookies(HttpServletResponse res)
    {
        Cookie cookie = new Cookie("ticket", "0b350893ed50452980915f0dd1ee44a0");
        res.addCookie(cookie);
        return "success";
    }

    @RequestMapping(path = "/getCookies")
    @ResponseBody
    public  String getCookies(HttpServletRequest res)
    {
        Cookie[] cookies = res.getCookies();
        if (cookies != null)
        {
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("sessionId"))
                {
                    return cookie.getValue();
                }
            }
            return "error";
        }
        else return "error";

    }

}
