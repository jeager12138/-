package com.sumflower.demo.dao;

import com.sumflower.demo.model.ExpertLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ExpertLoginDAO {
    String TABLE_NAME = " ExpertLogin ";
    String INSERT_FIELDS = " email, passwords, salt, expertName, major ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Update({"update ExpertLogin set salt=#{salt}, passwords=#{passwords} where email=#{email}"})
    int updateExpert(ExpertLogin expert);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where email=#{email}"})
    ExpertLogin getExpertByEmail(String email);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, "where id=#{id}"})
    ExpertLogin selectById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, "where major=#{major}"})
    List<ExpertLogin> getExpertList(String major);
}
