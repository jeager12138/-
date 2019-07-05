package com.sumflower.demo.dao;

import com.sumflower.demo.model.LoginTicket;
import com.sumflower.demo.model.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UploadFileDao {
    @Update({"update Project set picUrl = concat(picUrl, #{picUrl}) , docUrl = concat(docUrl, #{docUrl}) , " +
            "videoUrl = concat(videoUrl, #{videoUrl}) where id = #{id}"})
    int updateFileUrl(Project project);

    @Update({"update Project set picUrl = #{picUrl} , docUrl = #{docUrl}), " +
            "videoUrl = #{videoUrl}) where id = #{id}"})
    int changeFileUrl(Project project);
}
