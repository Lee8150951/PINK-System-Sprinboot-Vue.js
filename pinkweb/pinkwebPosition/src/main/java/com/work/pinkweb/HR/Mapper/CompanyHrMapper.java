package com.work.pinkweb.HR.Mapper;

import com.work.pinkweb.Entity.CompanyHr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyHrMapper {
   List<CompanyHr> getCompanyHrAllInfo(Integer ehrid);
   Integer updateHrInfo(Integer ehr_id,String ehr_vx,String ehr_position);
   Integer updateHrPhone(Integer ehr_id,String phone);
}
