package com.work.pinkweb.Company.Service;

import com.work.pinkweb.Entity.CompanyHr;

public interface CoCompanyHrService {
    Integer deleteOneHr(Integer ehr_id);
    Integer insertOneHr(CompanyHr companyHr);
}
