package com.work.pinkweb.HR.ServiceImpl;

import com.work.pinkweb.Entity.CompanyHr;
import com.work.pinkweb.HR.Mapper.CompanyHrMapper;
import com.work.pinkweb.HR.Service.CompanyHrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyHrServiceImpl implements CompanyHrService {
    @Autowired
    private CompanyHrMapper companyHrMapper;
    @Override
    public List<CompanyHr> getCompanyHrAllInfo(Integer ehrid) {
        List<CompanyHr> list=companyHrMapper.getCompanyHrAllInfo(ehrid);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setEhr_currentaccount(list.get(i).getRecruitmentList().size());
        }
        return list;
    }

    @Override
    public Integer updateHrInfo(Integer ehr_id,String ehr_vx, String ehr_position) {
        return companyHrMapper.updateHrInfo(ehr_id, ehr_vx, ehr_position);
    }

    @Override
    public Integer updateHrPhone(Integer ehr_id, String phone) {
        return companyHrMapper.updateHrPhone(ehr_id, phone);
    }


}
