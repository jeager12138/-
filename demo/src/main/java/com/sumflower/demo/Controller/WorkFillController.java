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


    @RequestMapping(path = "/workFillTmp")
    @ResponseBody
    public String workFill(@RequestBody Map m){
        /*
        Project p = new Project(m.get("projectName").toString(),m.get("college").toString(),Integer.parseInt(m.get("competitionType").toString()),m.get("studentNames").toString(),m.get("studentNumber").toString(),m.get("birthDay").toString(),m.get("education").toString(),m.get("major").toString(),m.get("entryYear").toString(),m.get("projectFullName").toString(),m.get("address").toString(),m.get("phone").toString(),m.get("email").toString(),m.get("friends").toString(),Integer.parseInt(m.get("projectType").toString()),m.get("details").toString(),m.get("invention").toString(),m.get("keywords").toString(),m.get("picUrl").toString(),m.get("docUrl").toString(),m.get("videoUrl").toString(),Double.parseDouble(m.get("averageScore").toString()),Integer.parseInt(m.get("submitStatus").toString()));
        int res = workFillDAO.addProject(p);
        if(res > 0) return "Successful";
        else return "Failed";
        */
        Project p2 = new Project();
        p2.setProjectName((m.get("projectName")).toString());
        int res = workFillDAO.addProject(p2);
        if(res > 0) return "Successful";
        else return "Failed";
    }

    @RequestMapping(path = "test")
    @ResponseBody
    public String test(){
        Project p2 = new Project();
        p2.setProjectName("Winjace");
        p2.setCompetitionType(1);
        p2.setAverageScore(1);
        p2.setProjectType(1);
        p2.setSubmitStatus(1);
        p2.setKeywords("");
        int res = workFillDAO.addProject(p2);
        if(res > 0) return "Successful";
        else return "Failed";
    }
}
