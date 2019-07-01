package com.sumflower.demo.Controller;

import com.sumflower.demo.model.HostHolder;
import com.sumflower.demo.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@CrossOrigin
@Controller
public class CompetitionController {
    @Autowired
    HostHolder hostHolder;
    @Autowired
    CompetitionService competitionService;

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

}
