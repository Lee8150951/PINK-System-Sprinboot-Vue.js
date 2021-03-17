package com.work.pinkweb.HR.Mapper;

import com.work.pinkweb.Entity.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    List<Company> getRCompanyById(Integer id);
    List<Company> getHrCompanyById(Integer id);
}
