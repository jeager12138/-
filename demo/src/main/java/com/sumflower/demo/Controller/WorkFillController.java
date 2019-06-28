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
        return "";
    }

    @RequestMapping(path = "/test")
    @ResponseBody
    public String test(@RequestBody Map m){
        System.out.println(m.get("projectName").toString());
        Project p2 = new Project("","",1,"","","","","","","","","","","",1,"","","","","","",1,1);
        System.out.println(p2.toString());
        return "Success";
    }
}
