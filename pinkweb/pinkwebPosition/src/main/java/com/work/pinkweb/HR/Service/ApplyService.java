package com.work.pinkweb.HR.Service;
import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Entity.Apply;

import java.util.List;

public interface ApplyService {

    PageInfo<Apply> getAllApply(Integer pageIndex,Integer pageSize,Integer r_id);
   Apply getCandidateByApply(Integer apply_id);
    Integer deleteApplyById(Integer apply_id);
    List<Apply> getLineByDate(String time,Integer r_id);
    List applyChange(Integer r_id);
    List applyCompanyChange(Integer r_id);


}
