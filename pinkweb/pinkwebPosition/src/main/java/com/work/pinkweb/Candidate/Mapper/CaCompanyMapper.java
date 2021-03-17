package com.work.pinkweb.Candidate.Mapper;

import com.work.pinkweb.Entity.Company;
import com.work.pinkweb.Entity.CompanyHr;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface CaCompanyMapper {
    //根据ID查找公司
    Company getCompanyDetailbById(Integer e_id);
    //根据ID查找公司Hr
    Company getCompanyHr(Integer e_id);
    //根据ID查找公司Hr发布的岗位信息
    List<CompanyHr> getHrRecruitment(Integer e_id);
    //查询所有公司
    List<Company> getAllCompany();
    //查询热门公司
    List<Company> getHotCompany();
    //根据城市查找公司
    List<Company> getCompanyByCity(String city);
    //根据类型查找公司
    List<Company> getCompanyByType(String type);
    //根据规模查找公司
    List<Company> getCompanyBySize(String size);
    //根据模糊查询查找公司
    List<Company>  searchCompany(String key);


}
