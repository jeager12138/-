package com.sumflower.demo.dao;


import com.sumflower.demo.model.Judge;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface JudgeDAO {
    String TABLE_NAME = " Judge ";
    String INSERT_FILEDS = " projectId, expertId, expertName, score, suggestion, judgeStatus, competitionId ";
    String SELECT_FIELDS = " id," + INSERT_FILEDS;

    @Select({"select * from ", TABLE_NAME, " where projectId = #{projectId}"})
    List<Judge> selectJudge(int projectId);

    @Update({"update ", TABLE_NAME, " set score=#{score},suggestion=#{suggestion} where projectId=#{projectId} and expertId=#{expertId}"})
    int updateJudge(Judge judge);

    @Select({"select * from ", TABLE_NAME, " where projectId=#{projectId} and expertId=#{expertId}"})
    List<Judge> getJudge(Judge judge);

    @Insert({"insert into ", TABLE_NAME, " (", INSERT_FILEDS, ") values (#{projectId}, #{expertId}, #{expertName}, 0, #{suggestion}, 1, #{competitionId})"})
    int insertJudge(Judge judge);

    @Select({"select * from ", TABLE_NAME, " where expertId=#{expertId}"})
    List<Judge> getListByExpertId(int expertId);

    @Update({"update ", TABLE_NAME, " set judgeStatus=0 where id=#{id}"})
    int finishJudge(int id);

    @Select({"select * from ", TABLE_NAME, " where id=#{id}"})
    Judge selectJudgeById(int id);
}
