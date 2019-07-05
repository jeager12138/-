package com.sumflower.demo.Controller;

import com.sumflower.demo.model.Judge;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class WinjaceController {

    @RequestMapping(path = {"/api2"}, method = {RequestMethod.GET} )
    @ResponseBody
    public String api2(){
        return "test";
    }
}
