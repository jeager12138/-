package com.sumflower.demo.Controller;

import com.sumflower.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin
@Controller
public class EmailController {
    @Autowired
    EmailService emailService;

    @RequestMapping("/inviteExperts")
    @ResponseBody
    public String SendEmail(@RequestBody Map m){
        String receivers = (m.get("receivers").toString());
        String context = "接受邀请：（link1），拒绝邀请：（link2）";
        emailService.send(receivers,context);
        return "发送邀请邮件成功！";
    }
}