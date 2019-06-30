package com.sumflower.demo.Controller;

import com.sumflower.demo.dao.WorkFillDAO;
import com.sumflower.demo.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@CrossOrigin
@Controller
public class WorkDetailController {
    @Autowired
    WorkFillDAO workFillDAO;

    @RequestMapping(path = "/getWorkDetail")
    @ResponseBody
    public Project project(@RequestBody Map map)
    {
        int id = Integer.parseInt(map.get("id").toString());
        return  workFillDAO.getInfo(id);
    }
}
