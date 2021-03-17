package com.work.pinkweb.Candidate.Service;

import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Entity.Company;
import com.work.pinkweb.Entity.CompanyHr;

import java.util.List;

public interface CaCompanyService {
    Company getCompanyDetailbById(Integer e_id);
    Company getCompanyHr(Integer e_id);
    List<CompanyHr> getHrRecruitment(Integer e_id);
    PageInfo<Company> getCompanyByCondition(Integer pageIndex, Integer pageSize,String key,String flag);
    List<Company> getHotCompany();


}
