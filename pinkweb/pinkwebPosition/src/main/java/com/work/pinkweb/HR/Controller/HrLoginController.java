package com.work.pinkweb.HR.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.work.pinkweb.Candidate.Service.LoginService;
import com.work.pinkweb.Entity.User;
import com.work.pinkweb.HR.Service.HrLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HrLoginController {
    @Autowired
    private HrLoginService hrLoginService;

    //求职者登录
    @RequestMapping(value = "/premise/hr_login",method = RequestMethod.POST)
    @ResponseBody
    public String applyLogin(@RequestBody Map<String, Map> para) throws JsonProcessingException {
        String phone=para.get("data").get("phone").toString();
        String password=para.get("data").get("password").toString();
        String state = hrLoginService.hrLogin(phone,password);
        return state;
    }

    @RequestMapping(value = "/premise/hr_login/{phone}",method = RequestMethod.GET)
    @ResponseBody
    public User getUserInfo(@PathVariable("phone") String phone) throws JsonProcessingException {
        return hrLoginService.getHrInfo(phone);
    }
}
