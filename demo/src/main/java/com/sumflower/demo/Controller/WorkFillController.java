package com.sumflower.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sumflower.demo.dao.*;
import com.sumflower.demo.model.*;
import com.sun.javafx.collections.MappingChange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@CrossOrigin
@Controller
public class WorkFillController {
    private static final Logger logger = LoggerFactory.getLogger(WorkFillController.class);

    @Autowired
    WorkFillDAO workFillDAO;
    @Autowired
    LoginTicketDAO loginTicketDAO;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    StudentLoginDAO studentLoginDAO;
    @Autowired
    ExpertLoginDAO expertLoginDAO;
    @Autowired
    CommitteeLoginDAO committeeLoginDAO;



    @RequestMapping(path = "/api/CreateWork")
    @ResponseBody
    public int CreateWork(@RequestBody Map m){
        int studentId = Integer.parseInt(m.get("studentId").toString());
        workFillDAO.createProject(studentId);
        List<Project> projectList = workFillDAO.getWorkList(studentId);
        if(projectList.size() > 0){
            int id = projectList.get(projectList.size()-1).getId();
            return id;
        }else{
            return 1000;
        }
    }

    @RequestMapping(path = "/api/CreateWorkCookie")
    @ResponseBody
    public int CreateWorkCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        String ticket = null;
        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }

        if (ticket != null) {
            LoginTicket loginTicket = loginTicketDAO.selectTicket(ticket);
            if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getTicketStatus() != 0) {
                return 1;
            }

            int res = workFillDAO.createProject(loginTicket.getUserId());
            return res > 0 ? 1 : 0;
        }

        return 1;
    }

    @RequestMapping(path = "/api/UpdateWork")
    @ResponseBody
    public int UpdateWork(@RequestBody Map m){
        String projectName = (m.get("projectName")).toString();
        String college = (m.get("college")).toString();
        int competitionType = Integer.parseInt((m.get("competitionType")).toString());
        String studentName = (m.get("studentName")).toString();
        String studentNumber = (m.get("studentNumber")).toString();
        String birthDay = (m.get("birthDay")).toString();
        String education = (m.get("education")).toString();
        String major = (m.get("major")).toString();
        String entryYear =(m.get("entryYear")).toString();
        String projectFullName =  (m.get("projectFullName")).toString();
        String address = (m.get("address")).toString();
        String phone = (m.get("phone")).toString();
        String email =  (m.get("email")).toString();
        ArrayList< HashMap<String, Object> > friend = (ArrayList< HashMap<String, Object>>)(m.get("friends"));
        String friends = JSONArray.toJSONString(friend);

        logger.error(friends);

        int projectType = Integer.parseInt((m.get("projectType")).toString());
        String details = (m.get("details")).toString();
        String invention = (m.get("invention")).toString();
        String keywords =(m.get("keywords")).toString();
        int studentId = Integer.parseInt((m.get("studentId")).toString());
        String picUrl = "";
        String docUrl = "";
        String videoUrl =  "";
        double averageScore = 0;
        int submitStatus = 1;
        int id = Integer.parseInt((m.get("id")).toString());
        Project p = new Project(id,projectName,college,competitionType,studentName,studentNumber,birthDay,education,major,entryYear,projectFullName,address,phone,email,friends,projectType,details,invention,keywords,picUrl,docUrl,videoUrl,averageScore,submitStatus,studentId);
        int res = workFillDAO.updateProject(p);
        System.out.println(p.toString());
        return res > 0 ? 1:0;
    }

    @RequestMapping(path = "/api/FinishWork")
    @ResponseBody
    public int FinishWork(@RequestBody Map m){
        int id = Integer.parseInt((m.get("id")).toString());
        int res = workFillDAO.updateProjectStatus(id);
        return res > 0 ? 1:0;
    }

    @RequestMapping(path = {"/api/ViewWorkList"})
    @ResponseBody
    public List<Project> ViewWorkList(@RequestBody Map m){
        int id = Integer.parseInt((m.get("studentId")).toString());
        List<Project> projectList = workFillDAO.getWorkList(id);
        return projectList;
    }


    @RequestMapping(path = {"/api/ViewWorkInfo"})
    @ResponseBody
    public Project ViewWorkInfo(@RequestBody Map m) {
        int id = Integer.parseInt((m.get("id")).toString());
        Project project = new Project();
        project = workFillDAO.getInfo(id);
        return project;
    }

//    @RequestMapping(path = {"/api/FindFriends"})
//    @ResponseBody
//    public List< HashMap<String, Object> > FindFriends(@RequestBody Map m) {
//        int id = Integer.parseInt((m.get("id")).toString());
//        Project project = new Project();
//        project = workFillDAO.getInfo(id);
//        String friendStr = project.getFriends();
//
//        List< HashMap<String, Object> > list = (List< HashMap<String, Object> >)(JSONObject.parseArray(friendStr));
//
//        return list;
//    }


    @RequestMapping(path = "/api/DeleteWork")
    @ResponseBody
    public int DeleteWork(@RequestBody Map m){
        int id = Integer.parseInt((m.get("id")).toString());
        int res = workFillDAO.deleteProject(id);
        return res > 0 ? 1:0;
    }

}