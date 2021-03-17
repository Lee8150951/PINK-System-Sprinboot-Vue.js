package com.work.pinkweb.Company.Service;


import com.work.pinkweb.Entity.CompanyAdmin;

import java.util.List;

public interface CompanyAdminService {
    CompanyAdmin getCompanyInfo(Integer ea_id);
    List<CompanyAdmin> getCompanyHrList(Integer ea_id);
}
