package com.work.pinkweb.HR.Controller;

import com.work.pinkweb.Entity.Company;
import com.work.pinkweb.Entity.CompanyHr;
import com.work.pinkweb.HR.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/hire/hire_profile/company/{ea_id}")
    public Company getCompanyInfo(@PathVariable("ea_id") Integer ea_id){
        System.out.println("ea_id = "+ea_id);
        List<Company> list1=companyService.getRCompanyById(ea_id);
        List<Company> list2= companyService.getHrCompanyById(ea_id);
        Company a=list1.get(0);
        a.setHrNum(list1.get(0).getCompanyHrList().size());
        a.setPositionNum(list1.get(0).getRecruitmentList().size());
        return a;
    }


}
