package com.sumflower.demo.Controller;

import com.sumflower.demo.dao.CompetitionDAO;
import com.sumflower.demo.dao.WorkFillDAO;
import com.sumflower.demo.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class CommitteeController {
    @Autowired
    WorkFillDAO workFillDAO;
    @Autowired
    CompetitionDAO competitionDAO;

    @RequestMapping(path = {"/showAlreadyList"})
    @ResponseBody
    public List<Project> showAlreadyList() {
        return workFillDAO.getAlreadyList(competitionDAO.selectLastId());
    }

    @RequestMapping(path = {"/passProject"})
    @ResponseBody
    public int passProject(@RequestBody Map m) {
        int id = Integer.parseInt((m.get("projectId")).toString());
        workFillDAO.passProject(id);
        return 1;
    }

    @RequestMapping(path = {"/rejectProject"})
    @ResponseBody
    public int rejectProject(@RequestBody Map m) {
        int id = Integer.parseInt((m.get("projectId")).toString());
        workFillDAO.rejectProject(id);
        return 1;
    }

    @RequestMapping(path = {"/giveBackProject"})
    @ResponseBody
    public int giveBackProject(@RequestBody Map m) {
        int id = Integer.parseInt((m.get("projectId")).toString());
        workFillDAO.giveBackProject(id);
        return 1;
    }

}
