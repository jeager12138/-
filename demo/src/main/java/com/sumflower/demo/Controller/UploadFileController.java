package com.sumflower.demo.Controller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import com.sumflower.demo.dao.UploadFileDao;
import com.sumflower.demo.model.HostHolder;
import com.sumflower.demo.model.LoginTicket;
import com.sumflower.demo.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Controller
public class UploadFileController {
    @Autowired
    HostHolder hostHolder;

    @Autowired
    UploadFileDao uploadFileDao;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("id") int id , @RequestParam("file") MultipartFile file) throws IOException {// 文件上传

        System.out.println(id);
        LoginTicket loginTicket = hostHolder.getLoginTicket();
        String filename;
        System.out.println(loginTicket.getUserType());
        if(loginTicket.getUserType() == 0)//student
        {

            filename = "student_"+loginTicket.getUserId() + "_" + file.getOriginalFilename();
        }else if(loginTicket.getUserType() == 2)//committee
        {
            filename = "committee" + "_" +file.getOriginalFilename();
        }else
        {
            filename = "expert_"+file.getOriginalFilename();
        }
        /*本地测试
        BufferedOutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(new File(filename)));
        */

        BufferedOutputStream outputStream =
                new BufferedOutputStream(new FileOutputStream
                        (new File("/var/www/html/"+filename)));


        String FileUrl = "http://liuterry.cn/"+filename; //下载url 文档：pdf，图片：jpg，视频：mp4
        String docUrl = "" , picUrl = "" , videoUrl = "";
        if (FileUrl.endsWith("pdf") | FileUrl.endsWith("PDF")) {
            docUrl = FileUrl + ";";
        }else if(FileUrl.endsWith("jpg") | FileUrl.endsWith("JPG"))
        {
            picUrl = FileUrl + ";";
        }else if(FileUrl.endsWith("mp4") | FileUrl.endsWith("MP4"))
        {
            videoUrl = FileUrl + ";";
        }

        Project project = new Project();
        project.setDocUrl(docUrl);
        project.setPicUrl(picUrl);
        project.setVideoUrl(videoUrl);
        project.setId(id);
        //project.setId(Integer.parseInt(id.toString()));
        uploadFileDao.updateFileUrl(project);

        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
        return "Finished";
    }
}