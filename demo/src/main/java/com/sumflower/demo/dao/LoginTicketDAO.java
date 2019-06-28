package com.sumflower.demo.dao;

import com.sumflower.demo.model.LoginTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginTicketDAO {
    String TABLE_NAME = "LoginTicket";
    String INSERT_FILEDS = " userId, expired, userType, ticket, ticketStatus ";
    String SELECT_FIELDS = " id, " + INSERT_FILEDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FILEDS,
    ") values (#{userId}, #{expired}, #{userType}, #{ticket}, #{ticketStatus})"})
    int addTicket(LoginTicket ticket);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where ticket=#{ticket}"})
    LoginTicket selectTicket(String ticket);



}
