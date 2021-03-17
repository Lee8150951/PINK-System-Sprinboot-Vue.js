package com.work.pinkweb.HR.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Entity.Apply;
import com.work.pinkweb.HR.Mapper.ApplyMapper;
import com.work.pinkweb.HR.Service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyMapper applyMapper;


    //根据hrid得到所有申请
    @Override
    public PageInfo<Apply> getAllApply(Integer pageIndex,Integer pageSize,Integer ehr_id) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Apply> applylist=applyMapper.getAllApply(ehr_id);
        PageInfo<Apply> info=new PageInfo<>(applylist);
        //做判断，如果有第二学历，就采纳第二学历
        for (int i = 0; i < applylist.size(); i++) {
            Apply apply=applylist.get(i);
            if(apply.getCandidate().getC_sdegree()==null){
                apply.getCandidate().setHighDegree(apply.getCandidate().getC_fdegree());
                apply.getCandidate().setHighSchool(apply.getCandidate().getC_fschool());
            }
            else{
                apply.getCandidate().setHighDegree(apply.getCandidate().getC_sdegree());
                apply.getCandidate().setHighSchool(apply.getCandidate().getC_sschool());
            }
            System.err.println(apply);

        }

        return info;
    }

    @Override
    public Apply getCandidateByApply(Integer apply_id) {
        Apply apply=applyMapper.getCandidateByApply(apply_id);
        if(apply.getCandidate().getC_sdegree()==null){
            apply.getCandidate().setHighDegree(apply.getCandidate().getC_fdegree());
            apply.getCandidate().setHighSchool(apply.getCandidate().getC_fschool());
        }
        else{
            apply.getCandidate().setHighDegree(apply.getCandidate().getC_sdegree());
            apply.getCandidate().setHighSchool(apply.getCandidate().getC_sschool());
        }
        return apply;
    }


    @Override
    public Integer deleteApplyById(Integer apply_id) {

            System.err.println("删除成功");
            return applyMapper.deleteApplyById(apply_id);



    }

    @Override
    public List<Apply> getLineByDate(String time,Integer r_id) {
        return applyMapper.getLineByDate(time,r_id);
    }

    @Override
    public List applyChange(Integer r_id) {

        List li = new ArrayList();
        for(int i=10;i>0;i--){
            li.add(applyMapper.applyChange(i,r_id));
        }
        System.out.println(li);
        return li;
    }

    @Override
    public List applyCompanyChange(Integer r_id) {

        List li = new ArrayList();
        for(int i=10;i>0;i--){
            li.add(applyMapper.applyCompanyChange(i,r_id));
        }
        System.out.println(li);
        return li;
    }

}
