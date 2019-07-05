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
    @Autowired
    CompetitionDAO competitionDAO;



    @RequestMapping(path = "/api/CreateWork")
    @ResponseBody
    public int CreateWork(@RequestBody Map m) {
        int competitionId = competitionDAO.selectLastId();
        int studentId = Integer.parseInt(m.get("studentId").toString());
        Project p = new Project();
        p.setStudentId(studentId);
        p.setCompetitionId(competitionId);
        workFillDAO.createProject(p);
        List<Project> projectList = workFillDAO.getWorkList(studentId, competitionId);
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
            int competitionId = competitionDAO.selectLastId();
            Project p = new Project();
            p.setStudentId(loginTicket.getUserId());
            p.setCompetitionId(competitionId);
            workFillDAO.createProject(p);
            int res = workFillDAO.createProject(p);
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
        String friends = "";
        if(m.get("friends") != null) {
            friends = m.get("friends").toString();
        }

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
        int submitStatus = 0;
        int id = Integer.parseInt((m.get("id")).toString());

        String additionalMessage = m.get("additionalMessage").toString();

        logger.error(additionalMessage);

        int competitionId = competitionDAO.selectLastId();

        Project p = new Project(id,projectName,college,competitionType,studentName,studentNumber,birthDay,education,major,entryYear,projectFullName,address,phone,email,friends,projectType,details,invention,keywords,picUrl,docUrl,videoUrl,averageScore,submitStatus,studentId,competitionId,additionalMessage);
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
        int competitionId = competitionDAO.selectLastId();
        List<Project> projectList = workFillDAO.getWorkList(id, competitionId);
        return projectList;
    }

    @RequestMapping(path = {"/api/ViewWorkInfo"})
    @ResponseBody
    public Project ViewWorkInfo(@RequestBody Map m) {
        int id = Integer.parseInt((m.get("id")).toString());
        Project project = workFillDAO.getInfo(id);
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


    @RequestMapping(path = {"/api/ViewWorkListForJane"})
    @ResponseBody
    public List<Project> ViewWorkListForJane(@RequestBody Map m) {
        int id = Integer.parseInt((m.get("studentId")).toString());
        int competitionId = competitionDAO.selectLastId();
        List<Project> projectList = workFillDAO.getWorkList(id, competitionId);
        for(Project p : projectList) {
            StringBuffer newAdditionMessage = new StringBuffer();
            if(p.getProjectType()==0) {
                if(p.getAdditionalMessage()==null) {
                    continue;
                }
                if(p.getAdditionalMessage().contains("\"0\"")) {
                    newAdditionMessage.append(" 实物、产品 ");
                }
                if(p.getAdditionalMessage().contains("\"1\"")) {
                    newAdditionMessage.append(" 模型 ");
                }
                if(p.getAdditionalMessage().contains("\"2\"")) {
                    newAdditionMessage.append(" 图纸 ");
                }
                if(p.getAdditionalMessage().contains("\"3\"")) {
                    newAdditionMessage.append(" 磁盘 ");
                }
                if(p.getAdditionalMessage().contains("\"4\"")) {
                    newAdditionMessage.append(" 现场演示 ");
                }
                if(p.getAdditionalMessage().contains("\"5\"")) {
                    newAdditionMessage.append(" 图片 ");
                }
                if(p.getAdditionalMessage().contains("\"6\"")) {
                    newAdditionMessage.append(" 录像 ");
                }
                if(p.getAdditionalMessage().contains("\"7\"")) {
                    newAdditionMessage.append(" 样品 ");
                }
                p.setAdditionalMessage(newAdditionMessage.toString());
            } else {
                if(p.getAdditionalMessage()==null) {
                    continue;
                }
                if(p.getAdditionalMessage().contains("\"0\"")) {
                    newAdditionMessage.append(" 走访 ");
                }
                if(p.getAdditionalMessage().contains("\"1\"")) {
                    newAdditionMessage.append(" 问卷 ");
                }
                if(p.getAdditionalMessage().contains("\"2\"")) {
                    newAdditionMessage.append(" 现场采访 ");
                }
                if(p.getAdditionalMessage().contains("\"3\"")) {
                    newAdditionMessage.append(" 人员介绍 ");
                }
                if(p.getAdditionalMessage().contains("\"4\"")) {
                    newAdditionMessage.append(" 个别交谈 ");
                }
                if(p.getAdditionalMessage().contains("\"5\"")) {
                    newAdditionMessage.append(" 亲临实践 ");
                }
                if(p.getAdditionalMessage().contains("\"6\"")) {
                    newAdditionMessage.append(" 会议 ");
                }
                if(p.getAdditionalMessage().contains("\"7\"")) {
                    newAdditionMessage.append(" 图片、照片 ");
                }
                if(p.getAdditionalMessage().contains("\"8\"")) {
                    newAdditionMessage.append(" 书报刊物 ");
                }
                if(p.getAdditionalMessage().contains("\"9\"")) {
                    newAdditionMessage.append(" 统计报表 ");
                }
                if(p.getAdditionalMessage().contains("\"10\"")) {
                    newAdditionMessage.append(" 影视资料 ");
                }
                if(p.getAdditionalMessage().contains("\"11\"")) {
                    newAdditionMessage.append(" 文件 ");
                }
                if(p.getAdditionalMessage().contains("\"12\"")) {
                    newAdditionMessage.append(" 集体组织 ");
                }
                if(p.getAdditionalMessage().contains("\"13\"")) {
                    newAdditionMessage.append(" 自发 ");
                }
                if(p.getAdditionalMessage().contains("\"14\"")) {
                    newAdditionMessage.append(" 其他 ");
                }
                p.setAdditionalMessage(newAdditionMessage.toString());
            }
        }
        return projectList;
    }

}