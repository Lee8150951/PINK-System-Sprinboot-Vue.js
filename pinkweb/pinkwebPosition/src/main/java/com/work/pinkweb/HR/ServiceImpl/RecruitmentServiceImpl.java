package com.work.pinkweb.HR.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Entity.Recruitment;
import com.work.pinkweb.HR.Mapper.RecruitmentMapper;
import com.work.pinkweb.HR.Service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    @Autowired
    RecruitmentMapper recruitmentMapper;

    @Override
    public PageInfo<Recruitment> getRecruitmentByEhrid(Integer pageIndex,Integer pageSize,Integer ehrid) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Recruitment> list=recruitmentMapper.getRecruitmentByEhrid(ehrid);
        PageInfo<Recruitment> plist=new PageInfo<>(list);
        return plist;
    }

    @Override
    public Integer deleteOneRecruitment(Integer r_id) {

        return recruitmentMapper.deleteOneRecruitment(r_id);
    }

    @Override
    public Integer insertOneRecruitment(Recruitment re) {
        return recruitmentMapper.insertOneRecruitment(re);
    }

    @Override
    public Recruitment getRecruitmentAppByRid(Integer r_id) {
        Recruitment recruitment=recruitmentMapper.getRecruitmentAppByRid(r_id);

        recruitment.setApplyNum(recruitment.getApplyList().size());

        return recruitment;
    }

    @Override
    public Integer updateRecruitment(Recruitment recruitment) {
        return recruitmentMapper.updateRecruitment(recruitment);
    }
}
