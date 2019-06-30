package com.sumflower.demo.Controller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sumflower.demo.model.HostHolder;
import com.sumflower.demo.model.LoginTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Controller
public class UploadFileController {
    @Autowired
    HostHolder hostHolder;

    public String upload(String id,MultipartFile file) throws IOException {// 文件上传

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
        BufferedOutputStream outputStream =
                new BufferedOutputStream(new FileOutputStream
                        (new File("/var/www/html/"+filename)));
        String FileUrl = "http://liuterry.cn/"+filename; //下载url 文档：pdf，图片：jpg，视频：mp4
        /*本地测试
        BufferedOutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(new File(filename)));
        */
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
        return FileUrl;
    }
}