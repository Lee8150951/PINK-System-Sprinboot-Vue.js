package com.work.pinkweb.Company.Mapper;

import com.work.pinkweb.Entity.CompanyHr;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoCompanyHrMapper {
     Integer deleteOneHr(Integer ehr_id);
     Integer insertOneHr(CompanyHr companyHr);
}
