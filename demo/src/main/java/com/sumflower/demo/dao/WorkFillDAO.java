package com.sumflower.demo.dao;

import com.sumflower.demo.model.LoginTicket;
import com.sumflower.demo.model.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorkFillDAO {
    String TABLE_NAME = "Project";
    String INSERT_FILEDS = " projectName,college,competitionType,studentName,studentNumber,birthDay,education,major," +
            "entryYear,projectFullName,address,phone,email,friends,projectType,details,invention,keywords,picUrl," +
            "docUrl,videoUrl,averageScore,submitStatus " ;

    @Insert({"insert into Project (studentId,competitionId,submitStatus) values (#{studentId},#{competitionId}, 1)"})
    int createProject(Project project);

    @Update({"update Project set projectName = #{projectName},college = #{college},competitionType = 1," +
            "studentName = #{studentName},studentNumber = #{studentNumber},birthDay = #{birthDay},education = #{education}," +
            "major = #{major},entryYear = #{entryYear},projectFullName = #{projectFullName},address = #{address}," +
            "phone = #{phone},email = #{email},friends = #{friends},projectType = #{projectType},details = #{details}," +
            "invention = #{invention},keywords = #{keywords}," +
            "submitStatus = #{submitStatus},studentId = #{studentId},competitionId=#{competitionId} where id = #{id}"})
    int updateProject(Project project);

    @Update({"update Project set submitStatus = 0 where id = #{id}"})
    int updateProjectStatus(int id);

    @Update({"update Project set submitStatus = 2 where id = #{id}"})
    int passProject(int id);

    @Update({"update Project set submitStatus = 3 where id = #{id}"})
    int rejectProject(int id);

    @Update({"update Project set submitStatus = 1 where id = #{id}"})
    int giveBackProject(int id);
    /*
    @Insert({" insert into ", TABLE_NAME, "(", INSERT_FILEDS,
    ") values ( #{projectName}, #{college}, #{competitionType}, #{studentName}, #{studentNumber}, #{birthDay}, " +
            "#{education}, #{major}, #{entryYear}, #{projectFullName}, #{address}, #{phone}, #{email}," +
            " #{friends}, #{projectType}, #{details}, #{invention}, #{keywords}, " +
            "#{picUrl}, #{docUrl}, #{videoUrl}, #{averageScore}, #{submitStatus} )"})
    int addProject(Project project);
    */
    @Delete({"delete from " , TABLE_NAME , " where id = #{id}"})
    int deleteProject(int id);

    @Select({"Select * From " , TABLE_NAME , " where id = #{id}"})
    Project getInfo(int id);

    @Select({"Select * From " , TABLE_NAME , " where studentId = #{studentId} and competitionId=#{competitionId}"})
    List<Project> getWorkList(@Param("studentId") int studentId, @Param("competitionId") int competitionId);

    @Select({"Select * from ", TABLE_NAME, " where competitionId=#{competitionId} and submitStatus!=1"})
    List<Project> getAlreadyList(int competitionId);

}
