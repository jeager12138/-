package com.sumflower.demo.Controller;

import com.sumflower.demo.dao.WorkFillDAO;
import com.sumflower.demo.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@CrossOrigin
@Controller
public class WorkFillController {
    @Autowired
    WorkFillDAO workFillDAO;

    @RequestMapping(path = "/api/CreateWork")
    @ResponseBody
    public int CreateWork(@RequestBody Map m){
        int res = workFillDAO.createProject(Integer.parseInt(m.get("studentId").toString()));
        return res > 0 ? 1:0;
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
        String friends = (m.get("friends")).toString();
        int projectType = Integer.parseInt((m.get("projectType")).toString());
        String details = (m.get("details")).toString();
        String invention = (m.get("invention")).toString();
        String keywords =(m.get("keywords")).toString();
        String picUrl = "";
        String docUrl = "";
        String videoUrl =  "";
        double averageScore = 0;
        int submitStatus = 1;
        int id = Integer.parseInt((m.get("id")).toString());
        Project p = new Project(id,projectName,college,competitionType,studentName,studentNumber,birthDay,education,major,entryYear,projectFullName,address,phone,email,friends,projectType,details,invention,keywords,picUrl,docUrl,videoUrl,averageScore,submitStatus);
        int res = workFillDAO.updateProject(p);
        System.out.println(p.toString());
        return res > 0 ? 1:0;
    }

    @RequestMapping(path = "/api/FinishWork")
    @ResponseBody
    public int FinishWork(@RequestBody Map m){
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
        String friends = (m.get("friends")).toString();
        int projectType = Integer.parseInt((m.get("projectType")).toString());
        String details = (m.get("details")).toString();
        String invention = (m.get("invention")).toString();
        String keywords =(m.get("keywords")).toString();
        String picUrl = "";
        String docUrl = "";
        String videoUrl =  "";
        double averageScore = 0;
        int submitStatus = 0;
        int id = Integer.parseInt((m.get("id")).toString());
        Project p = new Project(id,projectName,college,competitionType,studentName,studentNumber,birthDay,education,major,entryYear,projectFullName,address,phone,email,friends,projectType,details,invention,keywords,picUrl,docUrl,videoUrl,averageScore,submitStatus);
        int res = workFillDAO.updateProject(p);
        System.out.println(p.toString());
        return res > 0 ? 1:0;
    }


    @RequestMapping(path = {"/api/ViewWorkInfo"})
    @ResponseBody
    public Project ViewWorkInfo(@RequestBody Map m)
    {
        int id = Integer.parseInt((m.get("id")).toString());
        Project project = new Project();
        project = workFillDAO.getInfo(id);
        return project;
    }

    @RequestMapping(path = "/api/DeleteWork")
    @ResponseBody
    public int DeleteWork(@RequestBody Map m){
        int id = Integer.parseInt((m.get("id")).toString());
        int res = workFillDAO.deleteProject(id);
        return res > 0 ? 1:0;
    }

}