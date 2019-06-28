package com.sumflower.demo.service;

import com.sumflower.demo.dao.CommitteeLoginDAO;
import com.sumflower.demo.model.CommitteeLogin;
import com.sumflower.demo.util.SunflowerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommitteeService {
    @Autowired
    CommitteeLoginDAO committeeLoginDAO;
    @Autowired
    UserService userService;

    public Map<String, Object> login(String userName, String passwords) {
        Map<String, Object> map = new HashMap<>();
        CommitteeLogin committee = committeeLoginDAO.getCommittee(userName);

        if (committee == null) {
            map.put("msg", "用户名不存在");
            return map;
        }
        if (!SunflowerUtil.MD5(passwords + committee.getSalt()).equals(committee.getPassword())) {
            map.put("msg", "密码不正确");
            return map;
        }
        String ticket = userService.addLoginTicket(committee.getId(), 3);
        map.put("ticket", ticket);
        return map;
    }

}
