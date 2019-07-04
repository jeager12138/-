package com.sumflower.demo.dao;


import com.sumflower.demo.model.Judge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JudgeDAO {
    String TABLE_NAME = " Judge ";
    String INSERT_FILEDS = " projectId, expertId, expertName, score, suggestion, judgeStatus, competitionId ";
    String SELECT_FIELDS = " id," + INSERT_FILEDS;

    @Select({"select * from ", TABLE_NAME, " where projectId = #{projectId}"})
    List<Judge> selectJudge(int projectId);


}
