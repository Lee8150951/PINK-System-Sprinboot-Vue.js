package com.work.pinkweb.HR.Controller;

import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Entity.Apply;
import com.work.pinkweb.HR.Service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    //根据hr的id查询所有申请，参数：hr的id
    @RequestMapping("/hire/hire_attract/{ehrid}")
    public PageInfo<Apply> getByEhr(@RequestParam(value = "pageNow")Integer pageIndex,
                                    @RequestParam(value = "pageSize")Integer pageSize,@PathVariable("ehrid") Integer ehrid){
        return applyService.getAllApply(pageIndex, pageSize, ehrid);
    }
    //获取每日岗位投递信息
    @RequestMapping("/hire/hire_attract/delete")
    public Integer refuseApply(@RequestParam("apply_id") Integer apply_id){

        return applyService.deleteApplyById(apply_id);
    }

    //根据岗位id和时间在申请表中查询每日投递人数
    @RequestMapping("/hire/release_detail/dailyApply")
    public List<Integer> getDailyApplyLines(@RequestParam("r_id")Integer r_id,@RequestParam("dates") String dates){

        String[] sarray=dates.split("&");
        List<Integer> nums=new LinkedList<>();

        for (int i = 0; i < sarray.length; i++) {
            sarray[i]="2021-"+sarray[i].substring(2,7);
            System.err.println(sarray[i]);
            applyService.getLineByDate(sarray[i],r_id);
            System.err.println(applyService.getLineByDate(sarray[i],r_id).size());
            nums.add(applyService.getLineByDate(sarray[i],r_id).size());

        }

        return nums;
    }
    //在查看申请的详情页，根据申请id
    @RequestMapping("/hire/attract_detail/{apply_id}")
    public Apply getCandiateByApply(@PathVariable("apply_id") Integer apply_id){

        return applyService.getCandidateByApply(apply_id);
    }


    @RequestMapping("/hire/attract_detail/applyChange/{r_id}")
    public  List  applyChange(@PathVariable("r_id") Integer r_id) {
        return applyService.applyChange(r_id);
    }

    @RequestMapping("/hire/attract_detail/applyCompanyChange/{r_id}")
    public  List  applyCompanyChange(@PathVariable("r_id") Integer r_id) {
        return applyService.applyCompanyChange(r_id);
    }

}
