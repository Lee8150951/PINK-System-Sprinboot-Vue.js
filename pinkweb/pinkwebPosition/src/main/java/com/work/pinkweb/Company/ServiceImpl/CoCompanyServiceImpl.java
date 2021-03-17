package com.work.pinkweb.Company.ServiceImpl;

import com.work.pinkweb.Company.Service.CoCompanyService;
import com.work.pinkweb.Entity.Company;
import com.work.pinkweb.Company.Mapper.CoCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoCompanyServiceImpl implements CoCompanyService {
    @Autowired
    private CoCompanyMapper coCompanyMapper;
    @Override
    public Integer updateCompanyAvatar(Integer e_id,String fileName) {
        return coCompanyMapper.updateCompanyAvatar(e_id, fileName);
    }

    @Override
    public Integer updateCompanyInfo(Company company) {
        return coCompanyMapper.updateCompanyInfo(company);
    }

    @Override
    public Integer uploadLicenseImg(Integer e_id, String fileName) {
        return coCompanyMapper.uploadLicenseImg(e_id, fileName);
    }
}
