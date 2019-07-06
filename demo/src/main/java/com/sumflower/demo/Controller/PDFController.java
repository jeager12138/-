package com.sumflower.demo.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.Font;
import com.sumflower.demo.dao.WorkFillDAO;
import com.sumflower.demo.model.Project;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@CrossOrigin
@Controller
public class PDFController{
    @Autowired
    WorkFillDAO workFillDAO;

    @RequestMapping(path = "/api/DownloadPDF")
    @ResponseBody
    public void pdfexport(HttpServletResponse response, @RequestParam("id") int id) {
        // 指定解析器
        System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
                "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
        String filename = "科技竞赛作品提交表.pdf";
        //int id = Integer.parseInt(m.get("id").toString());
        //int id = 12;
        Project p = workFillDAO.getInfo(id);
        if(p == null) return;
        try {
            //设置文件头：最后一个参数是设置下载文件名(这里我们叫：个人简历.pdf)
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(p.getProjectFullName()+ ".pdf", "UTF-8"));
            //response.setContentType("/application/pdf;charset=UTF-8");
            response.setContentType("application/pdf");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        OutputStream os = null;
        //FileOutputStream os = new FileOutputStream(filename);
        PdfStamper ps = null;
        PdfReader reader = null;
        try {
            os = response.getOutputStream(); // 生成的新文件路径 ，这里指页面
            /**
             * class.getResource("/") --> 返回class文件所在的顶级目录，一般为包名的顶级目录
             * 在这个目录中src/main/java和src/main/resources和src/test/java都是属于顶级目录
             * 这里pdf/就属于顶级目录下的子目录了
             *
             */
            // 2 读入pdf表单
            reader = new PdfReader(PDFController.class.getResource("/PDF/") + "科技作品申报表.pdf");

            // 3 根据表单生成一个新的pdf
            ps = new PdfStamper(reader, os);

            // 4 获取pdf表单中所有字段
            AcroFields form = ps.getAcroFields();

            // 5给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
            //BaseFont bf = BaseFont.createFont("/Fonts/simsunb.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            //Font font = new Font(bfChinese, 10,Font.NORMAL);
            //form.addSubstitutionFont(font);
            //BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

            // 6查询数据
            //int id = Integer.parseInt(m.get("id").toString());

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("id", p.getId());
            data.put("projectName", p.getProjectName());
            data.put("college",p.getCollege());
            if(p.getCompetitionType() == 0){
                data.put("competitionType0","√");
            }
            else{
                data.put("competitionType1","√");
            }
            data.put("studentName",p.getStudentName());
            data.put("studentNumber",p.getStudentNumber());
            data.put("birthDay",p.getBirthDay());
            String education = p.getEducation();
            if(education.equals("大专")){
                data.put("education","A");
            }else if(education.equals("大学本科")){
                data.put("education","B");
            }else if(education.equals("硕士研究生")){
                data.put("education","C");
            }else if(education.equals("博士研究生")){
                data.put("education","D");
            }
            data.put("major",p.getMajor());
            data.put("entryYear",p.getEntryYear());
            data.put("projectFullName",p.getProjectFullName());
            data.put("address",p.getAddress());
            data.put("phone",p.getPhone());
            data.put("email",p.getEmail());
            if(p.getProjectType() == 0){
                data.put("projectType","A");
            }else if(p.getProjectType() == 1){
                data.put("projectType","B");
            }else if(p.getProjectType() == 2){
                data.put("projectType","C");
            }else if(p.getProjectType() == 3){
                data.put("projectType","D");
            }else if(p.getProjectType() == 4){
                data.put("projectType","E");
            }else if(p.getProjectType() == 5){
                data.put("projectType","F");
            }
            data.put("details",p.getDetails());
            data.put("invention",p.getInvention());
            data.put("keywords",p.getKeywords());

            if(p.getAdditionalMessage()==null) {
                p.setAdditionalMessage("");
            }
            StringBuffer newAdditionMessage = new StringBuffer();
            StringBuffer oldAdditionMessage = new StringBuffer(p.getAdditionalMessage());
            oldAdditionMessage.insert(oldAdditionMessage.length()-1, ", ");
            oldAdditionMessage.insert(1, ", ");
            String str = oldAdditionMessage.toString();
            if(p.getCompetitionType()==0){
                if(str.contains(", 0,")) {
                    data.put("showFormat1","√");
                }
                if(str.contains(", 1,")) {
                    data.put("showFormat2","√");
                }
                if(str.contains(", 2,")) {
                    data.put("showFormat3","√");
                }
                if(str.contains(", 3,")) {
                    data.put("showFormat4","√");
                }
                if(str.contains(", 4,")) {
                    data.put("showFormat5","√");
                }
                if(str.contains(", 5,")) {
                    data.put("showFormat6","√");
                }
                if(str.contains(", 6,")) {
                    data.put("showFormat7","√");
                }
                if(str.contains(", 7,")) {
                    data.put("showFormat8","√");
                }
            }else{
                if(str.contains(", 0,")) {
                    data.put("researchFormat1","√");
                }
                if(str.contains(", 1,")) {
                    data.put("researchFormat2","√");
                }
                if(str.contains(", 2,")) {
                    data.put("researchFormat3","√");
                }
                if(str.contains(", 3,")) {
                    data.put("researchFormat4","√");
                }
                if(str.contains(", 4,")) {
                    data.put("researchFormat5","√");
                }
                if(str.contains(", 5,")) {
                    data.put("researchFormat6","√");
                }
                if(str.contains(", 6,")) {
                    data.put("researchFormat7","√");
                }
                if(str.contains(", 7,")) {
                    data.put("researchFormat8","√");
                }
                if(str.contains(", 8,")) {
                    data.put("researchFormat9","√");
                }
                if(str.contains(", 9,")) {
                    data.put("researchFormat10","√");
                }
                if(str.contains(", 10,")) {
                    data.put("researchFormat11","√");
                }
                if(str.contains(", 11,")) {
                    data.put("researchFormat12","√");
                }
                if(str.contains(", 12,")) {
                    data.put("researchFormat13","√");
                }
                if(str.contains(", 13,")) {
                    data.put("researchFormat14","√");
                }
                if(str.contains(", 14,")) {
                    data.put("researchFormat15","√");
                }
            }

            String friends = p.getFriends();
            JSONArray friendlist = JSONArray.parseArray(friends);
            int length = friendlist.size();
            System.out.println(length);
            if(length > 0){
                JSONObject friend0 = JSON.parseObject(friendlist.get(0).toString());
                String fname0 = friend0.get("name").toString();
                String fNumber0 = friend0.get("studentId").toString();
                String fEducation0 = friend0.get("education").toString();
                String fTel0 = friend0.get("phone").toString();
                String fEmail0 = friend0.get("email").toString();
                data.put("fNumber0",fNumber0);
                data.put("fName0",fname0);
                data.put("fEducation0",fEducation0);
                data.put("fTel0",fTel0);
                data.put("fEmail0",fEmail0);
            }
            if(length > 1){
                JSONObject friend1 = JSON.parseObject(friendlist.get(1).toString());
                String fname1 = friend1.get("name").toString();
                String fNumber1 = friend1.get("studentId").toString();
                String fEducation1 = friend1.get("education").toString();
                String fTel1 = friend1.get("phone").toString();
                String fEmail1 = friend1.get("email").toString();
                data.put("fNumber1",fNumber1);
                data.put("fName1",fname1);
                data.put("fEducation1",fEducation1);
                data.put("fEmail1",fEmail1);
                data.put("fTel1",fTel1);
            }
            if(length > 2){
                JSONObject friend1 = JSON.parseObject(friendlist.get(1).toString());
                String fname1 = friend1.get("name").toString();
                String fNumber1 = friend1.get("studentId").toString();
                String fEducation1 = friend1.get("education").toString();
                String fTel1 = friend1.get("phone").toString();
                String fEmail1 = friend1.get("email").toString();
                data.put("fNumber2",fNumber1);
                data.put("fName2",fname1);
                data.put("fEducation2",fEducation1);
                data.put("fEmail2",fEmail1);
                data.put("fTel2",fTel1);
            }
            if(length > 3){
                JSONObject friend1 = JSON.parseObject(friendlist.get(1).toString());
                String fname1 = friend1.get("name").toString();
                String fNumber1 = friend1.get("studentId").toString();
                String fEducation1 = friend1.get("education").toString();
                String fTel1 = friend1.get("phone").toString();
                String fEmail1 = friend1.get("email").toString();
                data.put("fNumber3",fNumber1);
                data.put("fName3",fname1);
                data.put("fEducation3",fEducation1);
                data.put("fEmail3",fEmail1);
                data.put("fTel3",fTel1);
            }
                //Font font = new Font(bf, 12, Font.NORMAL);
            // 7遍历data 给pdf表单表格赋值
            for (String key : data.keySet()) {

                form.setFieldProperty(key, "textfont",bfChinese, null);// 设置字体
                form.setField(key, data.get(key).toString());
            }

            // 如果为false那么生成的PDF文件还能编辑，一定要设为true
            ps.setFormFlattening(true);

            System.out.println("===============PDF导出成功=============");
        } catch (Exception e) {
            System.out.println("===============PDF导出失败=============");
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                reader.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}