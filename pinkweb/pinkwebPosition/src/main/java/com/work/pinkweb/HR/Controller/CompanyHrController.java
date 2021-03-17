package com.work.pinkweb.HR.Controller;

import com.work.pinkweb.Entity.Company;
import com.work.pinkweb.Entity.CompanyHr;
import com.work.pinkweb.HR.Service.CompanyHrService;
import com.work.pinkweb.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class CompanyHrController {

    @Autowired
    private CompanyHrService companyHrService;

    @RequestMapping("/hire/hire_profile/{id}")
    public CompanyHr getComoanyHrInfo(@PathVariable("id") Integer ehr_id){
        List<CompanyHr> list=companyHrService.getCompanyHrAllInfo(ehr_id);
        CompanyHr a=list.get(0);
        return a;
    }

    @RequestMapping(value = "/hire/hire_profile/update/{id}",method = RequestMethod.POST)
    @ResponseBody
//    @RequestParam("phone")String phone,@RequestParam("ehr_vx")String ehr_vx,@RequestParam("ehr_position")String ehr_position
    public String updateHrProfile (@PathVariable("id") Integer ehr_id,@RequestBody Map<String, Map> para)
    {
        String phone=para.get("params").get("phone").toString();
        String ehr_vx=para.get("params").get("ehr_vx").toString();
        String ehr_position=para.get("params").get("ehr_position").toString();
        System.out.println("phone = "+phone);
        System.out.println("ehr_vx = "+ehr_vx);
        System.out.println("ehr_position = "+ehr_position);
        companyHrService.updateHrInfo(ehr_id,ehr_vx,ehr_position);
        companyHrService.updateHrPhone(ehr_id,phone);
        return "success";


    }



}
