package com.work.pinkweb.Company.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Entity.Company;
import com.work.pinkweb.Entity.CompanyAdmin;
import com.work.pinkweb.Entity.CompanyHr;
import com.work.pinkweb.Company.Service.CompanyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyAdminController {
    @Autowired
    private CompanyAdminService companyAdminService;

    @RequestMapping("/company/profile/{ea_id}")
    public CompanyAdmin getCompanyInfo(@PathVariable("ea_id")Integer ea_id){
        System.err.println(ea_id);
        return companyAdminService.getCompanyInfo(ea_id);
    }

    @RequestMapping("/company/list/{ea_id}")
    @ResponseBody
    public PageInfo<CompanyHr> getCompanyHrList(@RequestBody Map<String, Map> para,@PathVariable("ea_id")Integer ea_id){
        Integer pageIndex=Integer.valueOf(para.get("params").get("pageNow").toString());
        Integer pageSize=Integer.valueOf(para.get("params").get("pageSize").toString());

        PageHelper.startPage(pageIndex,pageSize);//一个设置

        List<CompanyAdmin> list1=companyAdminService.getCompanyHrList(ea_id);
        List<CompanyHr> list2=new LinkedList<>();
        for (int i = 0; i < list1.size(); i++) {
            System.err.println(list1.get(i).getCompany().getCompanyHrList().get(0));
            list2.add(list1.get(i).getCompany().getCompanyHrList().get(0));
        }
        PageInfo<CompanyHr> info=new PageInfo<>(list2);
        return info;
    }

}
