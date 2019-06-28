package com.sumflower.demo.Controller;

import com.sumflower.demo.model.Project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@CrossOrigin
@Controller
public class WorkFillController {



    @RequestMapping(path = "/workFillTmp")
    @ResponseBody
    public String workFill(@RequestBody Map m){
        Project p = new Project(m.get("projectName").toString(),m.get("college").toString(),Integer.parseInt(m.get("competitionType").toString()),m.get("studentNames").toString(),m.get("studentNumber").toString(),m.get("birthDay").toString(),m.get("education").toString(),m.get("major").toString(),m.get("entryYear").toString(),m.get("projectFullName").toString(),m.get("address").toString(),m.get("phone").toString(),m.get("email").toString(),m.get("friends").toString(),Integer.parseInt(m.get("projectType").toString()),m.get("details").toString(),m.get("invention").toString(),m.get("keywords").toString(),m.get("picUrl").toString(),m.get("docUrl").toString(),m.get("videoUrl").toString(),Double.parseDouble(m.get("averageScore").toString()),Integer.parseInt(m.get("submitStatus").toString()));

        return "Successful";
    }

}
