package com.work.pinkweb.Company.Mapper;

import com.work.pinkweb.Entity.CompanyAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyAdminMapper {
    //根据hrAdmin的id得到公司相关信息
    CompanyAdmin getCompanyInfo(Integer ea_id);
    List<CompanyAdmin> getCompanyHrList(Integer ea_id);

}
