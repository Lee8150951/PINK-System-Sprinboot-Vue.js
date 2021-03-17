package com.work.pinkweb.Candidate.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Entity.Company;
import com.work.pinkweb.Entity.CompanyHr;
import com.work.pinkweb.Candidate.Mapper.CaCompanyMapper;
import com.work.pinkweb.Candidate.Service.CaCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaCompanyServiceImlp implements CaCompanyService {
    @Autowired
    private CaCompanyMapper caCompanyMapper;

    @Override
    public List<Company> getHotCompany() {
        return caCompanyMapper.getHotCompany();
    }

    @Override
    public Company getCompanyDetailbById(Integer e_id) {
        return caCompanyMapper.getCompanyDetailbById(e_id);
    }

    @Override
    public Company getCompanyHr(Integer e_id) {return caCompanyMapper.getCompanyHr(e_id);}

    @Override
    public List<CompanyHr> getHrRecruitment(Integer e_id) {return caCompanyMapper.getHrRecruitment(e_id);}

    @Override
    public PageInfo<Company> getCompanyByCondition(Integer pageIndex, Integer pageSize,String key,String flag) {
        List<Company> clist = null;
        PageHelper.startPage(pageIndex,pageSize);//一个设置
        if(flag.equals("undefined")||flag.equals("不限")){
            clist= caCompanyMapper.getAllCompany();
        }else{
            switch (key){
                case "公司地点":
                    clist= caCompanyMapper.getCompanyByCity(flag);
                    break;
                case "行业类型":
                    clist= caCompanyMapper.getCompanyByType(flag);
                    break;
                case "公司规模":
                    clist= caCompanyMapper.getCompanyBySize(flag);
                    break;
                case "搜索":
                    clist= caCompanyMapper.searchCompany(flag);
                    break;
                default:
                    break;
            }
        }
        PageInfo<Company> info=new PageInfo<>(clist);
        return info;
    }


}
