package com.sumflower.demo.Controller;


import com.sumflower.demo.dao.CompetitionDAO;
import com.sumflower.demo.dao.ExpertLoginDAO;
import com.sumflower.demo.dao.JudgeDAO;
import com.sumflower.demo.model.Judge;
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
        if(list.size()==0) {
            judgeDAO.insertJudge(judge);
        }
        else {
            //nothing happened
        }


        return 0;
    }

}
