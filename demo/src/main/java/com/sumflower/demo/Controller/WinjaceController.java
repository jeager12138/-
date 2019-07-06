package com.sumflower.demo.Controller;

import com.sumflower.demo.dao.WorkFillDAO;
import com.sumflower.demo.model.Judge;
import com.sumflower.demo.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class WinjaceController {
    @Autowired
    WorkFillDAO workFillDAO;

    @RequestMapping(path = {"/api2"}, method = {RequestMethod.GET} )
    @ResponseBody
    public String api2(){
        return "test";
    }

    @RequestMapping(path = {"/api5"})
    @ResponseBody
    public List<Project> api5(@RequestBody Map m){
        int id = Integer.parseInt((m.get("studentId")).toString());
        int competitionId = 29;
        List<Project> projectList = workFillDAO.getWorkList(id,competitionId);
        return  projectList;
    }
}
