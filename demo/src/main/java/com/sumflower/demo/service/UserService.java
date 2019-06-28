package com.sumflower.demo.service;


import com.sumflower.demo.dao.LoginTicketDAO;
import com.sumflower.demo.model.LoginTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    LoginTicketDAO loginTicketDAO;

    public String addLoginTicket(int userId, int userType) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        ticket.setExpired(date);
        ticket.setTicketStatus(0);
        ticket.setUserType(userType);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDAO.addTicket(ticket);
        return ticket.getTicket();
    }



}
