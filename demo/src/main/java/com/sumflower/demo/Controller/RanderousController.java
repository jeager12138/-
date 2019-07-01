package com.sumflower.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@RestController
@Controller
public class RanderousController {

    @RequestMapping(path = "/setCookies")
    @ResponseBody
    public String setCookies(HttpServletResponse res)
    {
        Cookie cookie = new Cookie("ticket", "0b350893ed50452980915f0dd1ee44a0");
        res.addCookie(cookie);
        return "success";
    }

    @RequestMapping(path = "/getCookies")
    @ResponseBody
    public  String getCookies(HttpServletRequest res)
    {
        Cookie[] cookies = res.getCookies();
        if (cookies != null)
        {
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("sessionId"))
                {
                    return cookie.getValue();
                }
            }
            return "error";
        }
        else return "error";

    }

    /*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping("springUpload")
    public String  springUpload(HttpServletRequest request) throws IllegalStateException, IOException
    {
        long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String path="E:/springUpload"+file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }

            }

        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "/success";
    }

    @RequestMapping(path = "/testHttpServletRequest")
    @ResponseBody
    public String suc(HttpServletRequest a)
    {
        return  a.getSession().toString();
    }

}
