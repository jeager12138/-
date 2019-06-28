package com.sumflower.demo.dao;

import com.sumflower.demo.model.LoginTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginTicketDAO {
    String TABLE_NAME = "LoginTicket";
    String INSERT_FILEDS = " userId, expired, userType, ticket, ticketStatus ";
    String SELECT_FIELDS = " id, " + INSERT_FILEDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FILEDS,
    ") values (#{userId}, #{expired}, #{userType}, #{ticket}, #{ticketStatus}"})
    int addTicket(LoginTicket ticket);

}
