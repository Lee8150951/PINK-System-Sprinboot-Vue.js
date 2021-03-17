package com.work.pinkweb.HR.Controller;

import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Entity.Recruitment;
import com.work.pinkweb.HR.Service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class RecruitmentController {
    @Autowired
    private RecruitmentService recruitmentService;

    @RequestMapping("/hire/hire_release/{ehrid}")
    public PageInfo<Recruitment> getAllRecruitments(@RequestParam(value = "pageNow")Integer pageIndex,
                                                    @RequestParam(value = "pageSize")Integer pageSize, @PathVariable Integer ehrid){
        return recruitmentService.getRecruitmentByEhrid(pageIndex, pageSize, ehrid);
    }
    @RequestMapping("/hire/hire_release/delete/{r_id}")
    public Integer deleteRecruitment(@PathVariable("r_id") Integer r_id){
        return  recruitmentService.deleteOneRecruitment(r_id);
    }
    @RequestMapping("/hire/hire_release/insert")
    public Integer deleteRecruitment(@RequestBody Recruitment recruitment){
        recruitment.setCreated_time(new Date());
        System.err.println(recruitment);
        return recruitmentService.insertOneRecruitment(recruitment);
    }

    //根据岗位id回显岗位信息
    @RequestMapping("/hire/release_detail/{r_id}")
    public Recruitment getRecruitmentAppByRid(@PathVariable("r_id")Integer r_id){
        System.out.println("r_id = "+r_id);
        return recruitmentService.getRecruitmentAppByRid(r_id);

    }
    //修改招聘信息
    @PostMapping("/hire/release_detail/update")
    public Object updateRecruitment(@RequestBody Recruitment recruitment){
        return recruitmentService.updateRecruitment(recruitment);



    }


}
