package com.sumflower.demo.Controller;

import com.sumflower.demo.dao.WorkFillDAO;
import com.sumflower.demo.model.HostHolder;
import com.sumflower.demo.model.StudentLogin;
import com.sumflower.demo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class TerryTestController {
    private static final Logger logger = LoggerFactory.getLogger(TerryTestController.class);


    @Autowired
    StudentService studentService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    WorkFillDAO workFillDAO;

    @RequestMapping(path = {"/testCookie"})
    @ResponseBody
    public String testCookie() {

        logger.error("!!!!!!!!!!!!!!!!!");
        logger.error(hostHolder.getLoginTicket().getTicket());
        if(hostHolder.getLoginTicket().getUserType() == 0) {
            return hostHolder.getStudent().getStudentName();
        } else {
            return hostHolder.getExpert().getExpertName();
        }
    }

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
        String a = "(1,3)";
        workFillDAO.giveReward(a, 1);

        return "yes";
    }

}
