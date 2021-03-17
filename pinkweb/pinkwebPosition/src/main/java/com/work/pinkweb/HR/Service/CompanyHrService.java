package com.work.pinkweb.HR.Service;

import com.work.pinkweb.Entity.CompanyHr;

import java.util.List;

public interface CompanyHrService {
     List<CompanyHr> getCompanyHrAllInfo(Integer ehrid);
     Integer updateHrInfo(Integer ehr_id,String ehr_vx,String ehr_position);
     Integer updateHrPhone(Integer ehr_id,String phone);

}
