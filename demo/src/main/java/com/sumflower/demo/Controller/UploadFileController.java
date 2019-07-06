package com.sumflower.demo.Controller;
import java.io.*;
import java.util.Map;

import com.sumflower.demo.dao.UploadFileDao;
import com.sumflower.demo.dao.WorkFillDAO;
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

    @Autowired
    WorkFillDAO workFillDAO;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("id") int id , @RequestParam("file") MultipartFile file) throws IOException {// 文件上传

        System.out.println(id);

        LoginTicket loginTicket = hostHolder.getLoginTicket();
        String filename = "";
        System.out.println(loginTicket.getUserType());
        String originfilename = file.getOriginalFilename();
        if(loginTicket.getUserType() == 0)//student
        {

            filename = "student_" + loginTicket.getUserId() + "_" + file.getOriginalFilename();
        }else if(loginTicket.getUserType() == 2)//committee
        {
            filename = "committee" + "_" + file.getOriginalFilename();
        }else
        {
            filename = "expert_" + file.getOriginalFilename();
        }
        /*本地测试
        BufferedOutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(new File(filename)));
        File dir = new File(""+loginTicket.getUserId());
        */

        File dir = new File("/var/www/html/uploadfile/"
                +loginTicket.getUserId());

        if(!dir.exists()){
            dir.mkdir();
        }

        //用于实时生成新的zip文件
        PrintStream w = new PrintStream("/var/www/html/uploadfile/updateFolder");
        w.print(loginTicket.getUserId());

        BufferedOutputStream outputStream =
                new BufferedOutputStream(new FileOutputStream
                        (new File("/var/www/html/uploadfile/"
                                +loginTicket.getUserId()+"/"+filename)));
        String FileUrl = "http://180.76.233.101/uploadfile/"+loginTicket.getUserId()+"/"+filename; //下载url 文档：pdf，图片：jpg，视频：mp4
        String docUrl = "" , picUrl = "" , videoUrl = "";
        if (FileUrl.endsWith("pdf") | FileUrl.endsWith("PDF")) {
            docUrl = FileUrl + ";";
        }else if(FileUrl.endsWith("png") | FileUrl.endsWith("PNG"))
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
        uploadFileDao.updateFileUrl(project);

        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
        return originfilename;
    }

    @RequestMapping(path = "/deleteFile", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestBody Map m)
    {
        int id = Integer.parseInt(m.get("id").toString());
        String filename = m.get("filename").toString();
        System.out.println(id);

        LoginTicket loginTicket = hostHolder.getLoginTicket();
        String filetodelete;
        System.out.println(loginTicket.getUserType());
        if(loginTicket.getUserType() == 0)//student
        {

            filetodelete = "http://180.76.233.101/uploadfile/" + "student_" + loginTicket.getUserId() + "_" + filename + ";";
        }else if(loginTicket.getUserType() == 2)//committee
        {

            filetodelete = "http://180.76.233.101/uploadfile/" + "committee_" + loginTicket.getUserId() + "_" + filename + ";";
        }else
        {

            filetodelete = "http://180.76.233.101/uploadfile/" + "expert_" + loginTicket.getUserId() + "_" + filename + ";";
        }
        System.out.println(filetodelete);
        Project project = workFillDAO.getInfo(id);
        String docUrltodelete = project.getDocUrl();
        String picUrltodelete = project.getPicUrl();
        String videoUrltodelete = project.getVideoUrl();
        System.out.println(picUrltodelete);
        System.out.println(docUrltodelete);
        System.out.println(videoUrltodelete);

        if (filetodelete.endsWith("pdf;") | filetodelete.endsWith("PDF;")) {
            String newdocUrl = deleteSubString(docUrltodelete, filetodelete);
            System.out.println("newdocUrl:"+newdocUrl);
            Project newproject = new Project();
            newproject.setId(id);
            newproject.setDocUrl(newdocUrl);
            newproject.setPicUrl(picUrltodelete);
            newproject.setVideoUrl(videoUrltodelete);
            uploadFileDao.changeFileUrl(newproject);
        }else if(filetodelete.endsWith("png;") | filetodelete.endsWith("PNG;"))
        {
            String newpicUrl = deleteSubString(picUrltodelete, filetodelete);
            System.out.println("newpicUrl:" + newpicUrl);
            Project newproject = new Project();
            newproject.setId(id);
            newproject.setPicUrl(newpicUrl);
            newproject.setDocUrl(docUrltodelete);
            newproject.setVideoUrl(videoUrltodelete);
            uploadFileDao.changeFileUrl(newproject);
        }else if(filetodelete.endsWith("mp4;") | filetodelete.endsWith("MP4;"))
        {
            String newvideoUrl = deleteSubString(videoUrltodelete, filetodelete);
            System.out.println("newvideoUrl:" + newvideoUrl);
            Project newproject = new Project();
            newproject.setId(id);
            newproject.setVideoUrl(newvideoUrl);
            newproject.setDocUrl(docUrltodelete);
            newproject.setPicUrl(picUrltodelete);
            uploadFileDao.changeFileUrl(newproject);
        }
        return "File deleted";
    }

    public String deleteSubString(String str1,String str2) {
        StringBuffer sb = new StringBuffer(str1);
        int delCount = 0;
        Object[] obj = new Object[2];

        while (true) {
            int index = sb.indexOf(str2);
            if(index == -1) {
                break;
            }
            sb.delete(index, index+str2.length());
            delCount++;

        }
        if(delCount!=0) {
            obj[0] = sb.toString();
            obj[1] = delCount;
        }else {
            //不存在返回-1
            obj[0] = -1;
            obj[1] = -1;
        }
        String newstr = obj[0].toString();
        return newstr;
    }
}
