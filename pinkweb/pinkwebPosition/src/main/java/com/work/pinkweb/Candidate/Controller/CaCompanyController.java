package com.work.pinkweb.Candidate.Controller;

import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Entity.Company;
import com.work.pinkweb.Entity.CompanyHr;
import com.work.pinkweb.Candidate.Service.CaCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CaCompanyController {
    @Autowired
    private CaCompanyService caCompanyService;

    @RequestMapping("/index/apply_home/hotCompany")
    public  List<Company>  getHotCompany() {
        return caCompanyService.getHotCompany();
    }

    @RequestMapping(value="/index/company_detail/{e_id}",method= RequestMethod.GET)
    public Company getCompanyDetailById(@PathVariable Integer e_id ) {return caCompanyService.getCompanyDetailbById(e_id);}

    @RequestMapping(value="/index/company_detail/joblist/{e_id}",method= RequestMethod.GET)
    public Company getCompanyHr(@PathVariable Integer e_id ) {return caCompanyService.getCompanyHr(e_id);}

    @RequestMapping(value="/index/company_detail/hrlist/{e_id}",method= RequestMethod.GET)
    public List<CompanyHr> getHrRecruitment(@PathVariable Integer e_id ) {return caCompanyService.getHrRecruitment(e_id);}

    @RequestMapping("/index/apply_company/query")
    @ResponseBody
    public PageInfo<Company> getCompanyByCondition(@RequestBody Map<String, Map> para, @RequestParam(value = "key",required = false) String key ,
                                           @RequestParam(value = "flag",required = false) String flag){
        Integer pageIndex=Integer.valueOf(para.get("params").get("pageNow").toString());
        Integer pageSize=Integer.valueOf(para.get("params").get("pageSize").toString());
        return caCompanyService.getCompanyByCondition(pageIndex,pageSize,key,flag);
    }


}
