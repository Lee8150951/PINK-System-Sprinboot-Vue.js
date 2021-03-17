package com.work.pinkweb.Candidate.Controller;

import com.work.pinkweb.Entity.Recruitment;
import com.work.pinkweb.Candidate.Service.CaRecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController

public class CaRecruitmentController {
    @Autowired
    private CaRecruitmentService caRecruitmentService;

    @RequestMapping("/index/apply_home/hotRecruitment")
    public List<Recruitment> getHotRecruitment() {return caRecruitmentService.getHotRecruitment();}

    @RequestMapping("/index/position_detail/{r_id}")
    public Recruitment getRecruitmentDetailsById(@PathVariable("r_id") Integer id){return  caRecruitmentService.getRecruitmentDetailsById(id);}

    @RequestMapping("/index/apply_position/query")
    @ResponseBody
    public PageInfo<Recruitment> getRecruitmentByCondition(@RequestBody Map<String, Map> para,
                                         @RequestParam("key")String key,
                                         @RequestParam("flag")String flag) {

        Integer pageIndex=Integer.valueOf(para.get("params").get("pageNow").toString());
        Integer pageSize=Integer.valueOf(para.get("params").get("pageSize").toString());
        return caRecruitmentService.getRecruitmentByCondition(pageIndex,pageSize,key,flag);
    }

    //携带简历申请岗位
    @RequestMapping(value = "/index/apply_resume/apply", method = RequestMethod.POST)
    @ResponseBody
    public String applyResume(@RequestBody Map<String, Map> para) {
        Integer u_id = Integer.valueOf(para.get("data").get("u_id").toString());
        Integer p_id = Integer.valueOf(para.get("data").get("p_id").toString());
        Integer r_id = Integer.valueOf(para.get("data").get("r_id").toString());
        return caRecruitmentService.applyResume(u_id, p_id, r_id, new Date());
    }

    //重复申请判断
    @RequestMapping(value = "/index/apply_resume/judge", method = RequestMethod.POST)
    @ResponseBody
    public String judgeApplyResume(@RequestBody Map<String, Map> para) {
        Integer u_id = Integer.valueOf(para.get("data").get("u_id").toString());
        Integer p_id = Integer.valueOf(para.get("data").get("p_id").toString());
        return caRecruitmentService.judgeApplyResume(u_id, p_id);
    }
}
