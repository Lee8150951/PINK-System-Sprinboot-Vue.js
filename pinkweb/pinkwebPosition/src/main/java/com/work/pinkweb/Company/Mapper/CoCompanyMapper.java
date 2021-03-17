package com.work.pinkweb.Company.Mapper;
import com.work.pinkweb.Entity.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoCompanyMapper {
    Integer updateCompanyAvatar(Integer e_id,String e_logo);
    Integer updateCompanyInfo(Company company);
    Integer uploadLicenseImg(Integer e_id,String e_licenseImg);
}
