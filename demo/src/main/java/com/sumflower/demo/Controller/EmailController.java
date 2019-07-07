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
        String[] receivers = (m.get("emails").toString()).split(",");
        String[] expertId = (m.get("expertId").toString()).split(",");
        String projectId = (m.get("projectId").toString());
        for(int i =0; i < receivers.length; ++i ) {
            String context = "亲爱的评委老师：\n向日葵小班诚挚地邀请您参与第三届向日葵杯科技竞赛的评审！请选择：接受邀请：(http://180.76.233.101/#/user/register-expert?expertId=" + expertId[i] +
                    "&projectId=" + projectId + ")，拒绝邀请：（http://180.76.233.101/#/user/invitation-result）\n 向日葵小班";
            emailService.send(receivers[i], context);
        }
        return "发送邀请邮件成功！";
    }
}