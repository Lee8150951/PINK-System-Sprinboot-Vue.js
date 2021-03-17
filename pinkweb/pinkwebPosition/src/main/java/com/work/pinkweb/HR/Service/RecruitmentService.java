package com.work.pinkweb.HR.Service;

import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Entity.Recruitment;

import java.util.List;

public interface RecruitmentService {
    PageInfo<Recruitment> getRecruitmentByEhrid(Integer pageIndex,Integer pageSize,Integer ehrid);
    Integer deleteOneRecruitment(Integer r_id);
    Integer insertOneRecruitment(Recruitment re);
    Recruitment getRecruitmentAppByRid(Integer r_id);
    Integer updateRecruitment(Recruitment recruitment);
}
