package com.sumflower.demo.Controller;

import com.sumflower.demo.dao.CompetitionDAO;
import com.sumflower.demo.dao.WorkFillDAO;
import com.sumflower.demo.model.Competition;
import com.sumflower.demo.model.HostHolder;
import com.sumflower.demo.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@CrossOrigin
@Controller
public class CompetitionController {
    @Autowired
    HostHolder hostHolder;
    @Autowired
    CompetitionService competitionService;
    @Autowired
    CompetitionDAO competitionDAO;
    @Autowired
    WorkFillDAO workFillDAO;

    @RequestMapping(path = {"/addCompetition"}, method = {RequestMethod.POST})
    @ResponseBody
    public String addCompetition(@RequestBody Map m) {
        String competitionName = (m.get("competitionName")).toString();
        String startTime = (m.get("startTime")).toString();
        String endTime = (m.get("endTime")).toString();
        String description = (m.get("description")).toString();

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        ParsePosition pos2 = new ParsePosition(0);
        Date startDate = formatter1.parse(startTime, pos1);
        Date endDate = formatter2.parse(endTime, pos2);

        Map<String, String> map = competitionService.addCompetition(competitionName, startDate, endDate, description);
        if (map.containsKey("msg")) {
            return map.get("msg");
        } else {
            return "success";
        }
    }

    @RequestMapping(path = "/getCompetitionInfo")
    @ResponseBody
    public List<Competition> competition() {
        return competitionDAO.getInfo();
    }

    @RequestMapping(path = "/updateCompetitonInfo")
    @ResponseBody
    public int updateCompetitionInfo(@RequestBody Map m) {
        int competitionId = competitionDAO.selectLastId();
        String competitionName = (m.get("competitionName")).toString();
        String startTime = (m.get("startTime")).toString();
        String endTime = (m.get("endTime")).toString();
        String description = (m.get("description")).toString();

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        ParsePosition pos2 = new ParsePosition(0);
        Date startDate = formatter1.parse(startTime, pos1);
        Date endDate = formatter2.parse(endTime, pos2);

        Competition c = new Competition();
        c.setCompetitionName(competitionName);
        c.setDescription(description);
        c.setStartTime(startDate);
        c.setEndTime(endDate);
        competitionDAO.updateCompetition(c);

        return 1;
    }

    @RequestMapping(path = {"/giveRewards"})
    @ResponseBody
    public int giveRewards(@RequestBody Map m) {
        int rewardLevel = Integer.parseInt(m.get("rewardLevel").toString());
        String rewardProject = m.get("rewardProject").toString();
        StringBuffer sb = new StringBuffer("(");
        sb.append(rewardProject);
        sb.append(")");
        System.out.println(sb);
        String projectId = sb.toString();

        workFillDAO.giveReward(projectId, rewardLevel);
        return 0;
    }

}
