package com.sumflower.demo.dao;

import com.sumflower.demo.model.CommitteeLogin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommitteeLoginDAO {
    String TABLE_NAME = " Committee ";
    String INSERT_FIELDS = " userName, passwords, salt ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
    ") values (#{userName}, #{passwords}, #{salt})"})
    int addCommittee(CommitteeLogin committee);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, "where userName=#{userName}"})
    CommitteeLogin getCommittee(String userName);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, "where id=#{id}"})
    CommitteeLogin selectById(int id);
}
