package com.sumflower.demo.Controller;


import com.sumflower.demo.dao.CompetitionDAO;
import com.sumflower.demo.dao.ExpertLoginDAO;
import com.sumflower.demo.dao.JudgeDAO;
import com.sumflower.demo.dao.WorkFillDAO;
import com.sumflower.demo.model.ExpertJudge;
import com.sumflower.demo.model.ExpertLogin;
import com.sumflower.demo.model.Judge;
import com.sumflower.demo.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
@CrossOrigin
@Controller
public class ExpertController {

    @Autowired
    JudgeDAO judgeDAO;
    @Autowired
    CompetitionDAO competitionDAO;
    @Autowired
    ExpertLoginDAO expertLoginDAO;
    @Autowired
    WorkFillDAO workFillDAO;

    @RequestMapping(path = {"/updateJudgeDetail"})
    @ResponseBody
    public String updateJudgeDetail(@RequestBody Map m) {
        int projectId = Integer.parseInt(m.get("projectId").toString());
        int expertId = Integer.parseInt(m.get("expertId").toString());
        int score = Integer.parseInt(m.get("score").toString());
        String suggestion = m.get("suggestion").toString();
        Judge judge = new Judge();
        judge.setExpertId(expertId);
        judge.setProjectId(projectId);
        judge.setScore(score);
        judge.setSuggestion(suggestion);
        judgeDAO.updateJudge(judge);

        return "success";
    }

    @RequestMapping(path = {"/getJudgeDetail"})
    @ResponseBody
    public Judge getJudgeDetail(@RequestBody Map m) {
        int projectId = Integer.parseInt(m.get("projectId").toString());
        int expertId = Integer.parseInt(m.get("expertId").toString());
        Judge judge = new Judge();
        judge.setExpertId(expertId);
        judge.setProjectId(projectId);

        List<Judge> list = judgeDAO.getJudge(judge);
        return list.get(0);
    }

    @RequestMapping(path = {"/acceptInvite"})
    @ResponseBody
    public int acceptInvite(@RequestBody Map m) {
        int projectId = Integer.parseInt(m.get("projectId").toString());
        int expertId = Integer.parseInt(m.get("expertId").toString());
        String expertName = expertLoginDAO.selectById(expertId).getExpertName();
        Judge judge = new Judge();
        judge.setSuggestion("");
        judge.setScore(0);
        judge.setProjectId(projectId);
        judge.setExpertId(expertId);
        judge.setCompetitionId(competitionDAO.selectLastId());
        judge.setJudgeStatus(1);
        judge.setExpertName(expertName);
        List<Judge> list = judgeDAO.getJudge(judge);
        workFillDAO.addJudgeNum(projectId);
        if(list.size()==0) {
            judgeDAO.insertJudge(judge);
        }
        else {
            //nothing happened
        }

        return 0;
    }

    @RequestMapping(path = {"/getJudgeListForExpert"})
    @ResponseBody
    public List<ExpertJudge> getJudgeListForExpert(@RequestBody Map m) {
        int expertId = Integer.parseInt(m.get("expertId").toString());
        List<Judge> judgeList = judgeDAO.getListByExpertId(expertId);
        List<ExpertJudge> retList = new ArrayList<>();
        for(Judge j : judgeList) {
            int projectId = j.getProjectId();
            Project p = workFillDAO.getInfo(projectId);
            if(p == null) {
                continue;
            }
            ExpertJudge ej = new ExpertJudge();
            ej.setJudgeStatus(j.getJudgeStatus());
            ej.setCompetitionType(p.getCompetitionType());
            ej.setKeywords(p.getKeywords());
            ej.setProjectName(p.getProjectName());
            ej.setProjectId(p.getId());
            ej.setJudgeId(j.getId());
            retList.add(ej);
        }
        return retList;
    }

    @RequestMapping(path = {"/finishJudge"})
    @ResponseBody
    public int finishJudge(@RequestBody Map m) {
        int id = Integer.parseInt(m.get("judgeId").toString());
        judgeDAO.finishJudge(id);

        Judge j = judgeDAO.selectJudgeById(id);
        Project p = workFillDAO.getInfo(j.getProjectId());
        double newAve = ((p.getJudgeNum()-1)*p.getAverageScore()+j.getScore())/p.getJudgeNum();
        workFillDAO.updateAverage(newAve, p.getId());
        return 0;
    }

}
