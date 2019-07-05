package com.sumflower.demo.dao;

import com.sumflower.demo.model.Competition;
import com.sumflower.demo.model.ExpertLogin;
import com.sumflower.demo.model.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CompetitionDAO {
    String TABLE_NAME = " Competition ";
    String INSERT_FIELDS = " competitionName, startTime, endTime, description, competitionStatus ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
    ") values (#{competitionName},#{startTime},#{endTime},#{description},#{competitionStatus})"})
    int addCompetition(Competition competition);


    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where competitionName=#{competitionName}"})
    Competition selectByName(String competitionName);

    @Select({"select * from ", TABLE_NAME, " order by id desc"})
    List<Competition> getInfo();

    @Select({"select max(id) from ", TABLE_NAME})
    int selectLastId();

    @Update({"update ", TABLE_NAME, " set competitionName=#{competitionName},startTime=#{startTime},endTime=#{endTime},description=#{description} where id=#{id}"})
    int updateCompetition(Competition competition);

    @Select({"select competitionStatus from ", TABLE_NAME, " where id=#{id}"})
    String getStatus(int id);

}
