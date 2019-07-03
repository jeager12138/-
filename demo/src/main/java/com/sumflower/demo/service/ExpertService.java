package com.sumflower.demo.service;

import com.sumflower.demo.dao.ExpertLoginDAO;
import com.sumflower.demo.model.ExpertLogin;
import com.sumflower.demo.util.SunflowerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ExpertService {
    @Autowired
    private ExpertLoginDAO expertLoginDAO;

    @Autowired
    UserService userService;

    public Map<String, Object> register(String email, String passwords) {
        Map<String, Object> map = new HashMap<>();
        ExpertLogin expert = expertLoginDAO.getExpertByEmail(email);

        expert.setSalt(UUID.randomUUID().toString().substring(0, 5));
        expert.setPassword(SunflowerUtil.MD5(passwords + expert.getSalt()));
        expertLoginDAO.updateExpert(expert);

        String ticket = userService.addLoginTicket(expert.getId(), 1);
        map.put("ticket", ticket);
        return map;
    }

    public Map<String, Object> login(String email, String passwords) {
        Map<String, Object> map = new HashMap<>();
        ExpertLogin expert = expertLoginDAO.getExpertByEmail(email);

        if (expert == null) {
            map.put("msg", "用户名不存在");
            return map;
        }
        if (!SunflowerUtil.MD5(passwords + expert.getSalt()).equals(expert.getPassword())) {
            map.put("msg", "密码不正确");
            return map;
        }
        String ticket = userService.addLoginTicket(expert.getId(), 1);
        map.put("ticket", ticket);
        map.put("user", expert);
        return map;
    }

}
