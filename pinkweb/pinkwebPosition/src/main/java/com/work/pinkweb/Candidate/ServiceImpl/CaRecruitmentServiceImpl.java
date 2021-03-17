package com.work.pinkweb.Candidate.ServiceImpl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Candidate.Mapper.CaRecruitmentMapper;
import com.work.pinkweb.Entity.Recruitment;
import com.work.pinkweb.Candidate.Service.CaRecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CaRecruitmentServiceImpl implements CaRecruitmentService {
    @Autowired
    private CaRecruitmentMapper caRecruitmentMapper;

    @Override
    public List<Recruitment> getHotRecruitment() {
        return caRecruitmentMapper.getHotRecruitment();
    }

    @Override
    public Recruitment getRecruitmentDetailsById(Integer r_id) {
        return caRecruitmentMapper.getRecruitmentDetailsById(r_id);
    }

    @Override
    public PageInfo<Recruitment> getRecruitmentByCondition(Integer pageIndex, Integer pageSize, String key, String flag) {
        List<Recruitment> rlist = null;
        PageHelper.startPage(pageIndex,pageSize);//一个设置

        double time_tmp=0;
        if(flag.equals("undefined")||flag.equals("不限")||flag.equals("全部城市")){
            rlist= caRecruitmentMapper.getAllRecruitment();
        }else {
                if(key.matches(".*time.*")){
                    String[] timeArr = key.split("\\_");
                    time_tmp = Float.valueOf(timeArr[1]);
                    key = "time";
                }
            switch (key){
                case "salary":
                    rlist= caRecruitmentMapper.getBySalary(flag);
                    break;
                case "time":
                    rlist= caRecruitmentMapper.getByTime((float) time_tmp);
                    break;
                case "exp":
                    rlist= caRecruitmentMapper.getByExperience(flag);
                    break;
                case "degree":
                    rlist= caRecruitmentMapper.getByDegree(flag);
                    break;
                case "城市":
                    rlist= caRecruitmentMapper.getByCity(flag);
                    break;
                case "搜索":
                    rlist= caRecruitmentMapper.getByName(flag);
                    break;
                default:
                    break;
            }
        }
        PageInfo<Recruitment> info=new PageInfo<>(rlist);
        return info;
    }

    @Override
    public String applyResume(Integer u_id, Integer p_id, Integer r_id, Date created_time) {
        caRecruitmentMapper.applyResume(u_id, p_id, r_id, created_time);
        return "applyResume-success";
    }

    @Override
    public String judgeApplyResume(Integer u_id, Integer p_id) {
        if (caRecruitmentMapper.judgeApplyResume(u_id, p_id) == null) {
            return "没有投递该岗位";
        } else {
            return "已经投递该岗位";
        }
    }
}
