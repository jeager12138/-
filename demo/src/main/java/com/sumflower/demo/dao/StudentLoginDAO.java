package com.sumflower.demo.dao;


import com.sumflower.demo.model.StudentLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentLoginDAO {
    String TABLE_NAME = " StudentLogin ";
    String INSERT_FIELDS = " studentNumber, studentName, college, major, entryYear, phone, email, passwords, salt ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where studentNumber=#{studentNumber}"})
    StudentLogin selectByStudentNumber(String studentNumber);


}
