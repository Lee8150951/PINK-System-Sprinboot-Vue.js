package com.work.pinkweb.Company.Service;

import com.work.pinkweb.Entity.Company;

public interface CoCompanyService {
    Integer updateCompanyAvatar(Integer e_id,String fileName);
    Integer updateCompanyInfo(Company company);
    Integer uploadLicenseImg(Integer e_id,String fileName);
}
