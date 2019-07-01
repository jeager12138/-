package com.sumflower.demo.dao;

import com.sumflower.demo.model.Competition;
import com.sumflower.demo.model.ExpertLogin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CompetitionDAO {
    String TABLE_NAME = " Competiton ";
    String INSERT_FIELDS = " competitionName, startTime, endTime, description ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
    ") values (#{competitionName},#{startTime},#{endTime},#{description})"})
    int addCompetition(Competition competition);


    @Select({"select ", SELECT_FIELDS, "from ", TABLE_NAME, " where competitionName=#{competitionName}"})
    Competition selectByName(String competitionName);

}
