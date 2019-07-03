package com.sumflower.demo.Controller;

import com.sumflower.demo.dao.ExpertLoginDAO;
import com.sumflower.demo.dao.WorkFillDAO;
import com.sumflower.demo.model.ExpertLogin;
import com.sumflower.demo.model.Project;
import com.sumflower.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class EmailController {
    @Autowired
    EmailService emailService;
    @Autowired
    ExpertLoginDAO expertLoginDAO;
    @Autowired
    WorkFillDAO workFillDAO;

    @RequestMapping(path = {"/viewExpertList"}, method = RequestMethod.POST)
    @ResponseBody
    public List<ExpertLogin> ViewExpertList(@RequestBody Map m){
        int id = Integer.parseInt((m.get("projectId")).toString());
        String major = workFillDAO.getInfo(id).getMajor();
        List<ExpertLogin> expertList = expertLoginDAO.getExpertList(major);
        return expertList;
    }

    @RequestMapping(path = {"/inviteExperts"}, method = RequestMethod.POST)
    @ResponseBody
    public String SendEmail(@RequestBody Map m){
        String receivers = (m.get("receivers").toString());
        String context = "接受邀请：（link1），拒绝邀请：（link2）";
        emailService.send(receivers,context);
        return "发送邀请邮件成功！";
    }
}