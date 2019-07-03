package com.sumflower.demo.interceptor;

import com.sumflower.demo.Controller.LoginController;
import com.sumflower.demo.dao.CommitteeLoginDAO;
import com.sumflower.demo.dao.ExpertLoginDAO;
import com.sumflower.demo.dao.LoginTicketDAO;
import com.sumflower.demo.dao.StudentLoginDAO;
import com.sumflower.demo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class PassportInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(PassportInterceptor.class);

    @Autowired
    LoginTicketDAO loginTicketDAO;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    StudentLoginDAO studentLoginDAO;
    @Autowired
    ExpertLoginDAO expertLoginDAO;
    @Autowired
    CommitteeLoginDAO committeeLoginDAO;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = null;

        ticket = httpServletRequest.getHeader("cookies");

        logger.error("############");
        logger.error(ticket);


        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }

        if (ticket != null) {
            LoginTicket loginTicket = loginTicketDAO.selectTicket(ticket);
            if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getTicketStatus() != 0) {
                return true;
            }

            hostHolder.setLoginTicket(loginTicket);
            if (loginTicket.getUserType() == 0) {
                StudentLogin student = studentLoginDAO.selectById(loginTicket.getUserId());
                hostHolder.setStudents(student);
            } else if (loginTicket.getUserType() == 1) {
                ExpertLogin expert = expertLoginDAO.selectById(loginTicket.getUserId());
                hostHolder.setExperts(expert);
            } else {
                CommitteeLogin committee = committeeLoginDAO.selectById(loginTicket.getUserId());
                hostHolder.setCommittees(committee);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        hostHolder.clear();
    }

}
