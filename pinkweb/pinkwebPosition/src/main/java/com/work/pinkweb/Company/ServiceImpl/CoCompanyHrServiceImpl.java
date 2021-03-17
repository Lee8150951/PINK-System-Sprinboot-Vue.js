package com.work.pinkweb.Company.ServiceImpl;

import com.work.pinkweb.Company.Service.CoCompanyHrService;
import com.work.pinkweb.Entity.CompanyHr;
import com.work.pinkweb.Company.Mapper.CoCompanyHrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoCompanyHrServiceImpl implements CoCompanyHrService {
    @Autowired
    private CoCompanyHrMapper coCompanyHrMapper;
    @Override
    public Integer deleteOneHr(Integer ehr_id) {
        return coCompanyHrMapper.deleteOneHr(ehr_id);
    }

    @Override
    public Integer insertOneHr(CompanyHr companyHr) {

        return coCompanyHrMapper.insertOneHr(companyHr);
    }
}
