package com.work.pinkweb.HR.Service;

import com.work.pinkweb.Entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getRCompanyById(Integer id);
    List<Company> getHrCompanyById(Integer id);
}
