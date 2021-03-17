package com.work.pinkweb.Candidate.Service;


import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Entity.Company;
import com.work.pinkweb.Entity.Recruitment;

import java.util.Date;
import java.util.List;

public interface CaRecruitmentService {
    List<Recruitment> getHotRecruitment();
    Recruitment getRecruitmentDetailsById(Integer r_id);
    PageInfo<Recruitment> getRecruitmentByCondition(Integer pageIndex,Integer pageSize,String key,String flag);
    String applyResume(Integer u_id, Integer p_id, Integer r_id, Date created_time);
    String judgeApplyResume(Integer u_id,Integer p_id);
}
