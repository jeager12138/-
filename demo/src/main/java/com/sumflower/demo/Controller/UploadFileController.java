package com.sumflower.demo.Controller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Controller
public class UploadFileController {

    @RequestMapping(path = "/upload", method = RequestMethod.GET)
    public String file() {
        return "file";
    }

    @ResponseBody
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {// 文件上传

        BufferedOutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(new File("/var/www/html/"+file.getOriginalFilename())));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
        return "Finished";
    }
}