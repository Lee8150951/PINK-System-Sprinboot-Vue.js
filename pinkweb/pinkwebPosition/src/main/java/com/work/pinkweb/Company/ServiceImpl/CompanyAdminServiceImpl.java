package com.work.pinkweb.Company.ServiceImpl;

import com.work.pinkweb.Entity.CompanyAdmin;
import com.work.pinkweb.Company.Mapper.CompanyAdminMapper;
import com.work.pinkweb.Company.Service.CompanyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyAdminServiceImpl implements CompanyAdminService {
    @Autowired
    private CompanyAdminMapper companyAdminMapper;

    @Override
    public CompanyAdmin getCompanyInfo(Integer ea_id) {
        return companyAdminMapper.getCompanyInfo(ea_id);
    }

    @Override
    public List<CompanyAdmin> getCompanyHrList(Integer ea_id) {
        return companyAdminMapper.getCompanyHrList(ea_id);
    }
}
