package com.sumflower.demo.service;


import com.sumflower.demo.dao.CompetitionDAO;
import com.sumflower.demo.model.Competition;
import com.sumflower.demo.model.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CompetitionService {
    @Autowired
    HostHolder hostHolder;
    @Autowired
    CompetitionDAO competitionDAO;

    public Map<String, String> addCompetition(String competitionName, Date startTime, Date endTime, String description) {
        Map<String, String> map = new HashMap<>();
        Competition c = competitionDAO.selectByName(competitionName);
        if (c != null) {
            map.put("msg", "竞赛名称重复");
            return map;
        } else {
            c = new Competition();
            c.setCompetitionName(competitionName);
            c.setDescription(description);
            c.setStartTime(startTime);
            c.setEndTime(endTime);
            competitionDAO.addCompetition(c);
            map.put("success", "success");
            return map;
        }
    }


}
