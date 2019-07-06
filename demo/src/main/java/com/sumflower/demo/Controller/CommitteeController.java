package com.sumflower.demo.Controller;

import com.sumflower.demo.dao.CompetitionDAO;
import com.sumflower.demo.dao.JudgeDAO;
import com.sumflower.demo.dao.WorkFillDAO;
import com.sumflower.demo.interceptor.PassportInterceptor;
import com.sumflower.demo.model.Judge;
import com.sumflower.demo.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class CommitteeController {

    private static final Logger logger = LoggerFactory.getLogger(CommitteeController.class);

    @Autowired
    WorkFillDAO workFillDAO;
    @Autowired
    JudgeDAO judgeDAO;
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

    @RequestMapping(path = {"/findJudgedList"})
    @ResponseBody
    public Map<String, Object> findJudgedList(@RequestBody Map m) {
        Map<String, Object> map = new HashMap<>();
        int competitionId = competitionDAO.selectLastId();
        map.put("List", workFillDAO.getJudgedList(competitionId));
        map.put("competitionStatus", competitionDAO.getStatus(competitionId).equals("doing")?0:1);
        return map;
    }

    @RequestMapping(path = {"/getJudgeDetails"})
    @ResponseBody
    public List<Judge> getJudgeDetails(@RequestBody Map m) {
        logger.error("getJudgeDetails interface");
        int projectId = Integer.parseInt(m.get("projectId").toString());
        return judgeDAO.selectJudge(projectId);
    }

    @RequestMapping(path = {"/passFirstTest"})
    @ResponseBody
    public int passFirstTest(@RequestBody Map m) {
        String projectIdList = m.get("projectId").toString();
        StringBuffer sb = new StringBuffer("(");
        sb.append(projectIdList);
        sb.append(")");
        String str = sb.toString();
        workFillDAO.passProjectList(str);
        return 0;
    }



}
