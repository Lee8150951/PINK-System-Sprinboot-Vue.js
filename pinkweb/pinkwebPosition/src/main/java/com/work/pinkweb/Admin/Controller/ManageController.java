package com.work.pinkweb.Admin.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Admin.Service.ManageService;
import com.work.pinkweb.Entity.Admin;
import com.work.pinkweb.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ManageController {
    @Autowired
    private ManageService manageService;

    //求职者登录
    @RequestMapping(value = "/premise/admin_login",method = RequestMethod.POST)
    @ResponseBody
    public String adminLogin(@RequestBody Map<String, Map> para) throws JsonProcessingException {
        String admin_account=para.get("data").get("admin_account").toString();
        String admin_pwd=para.get("data").get("admin_pwd").toString();
        String state = manageService.adminLogin(admin_account,admin_pwd);
        return state;
    }

    //公司删除
    @RequestMapping("/admin/manage_company/delete/{e_id}")
    public  String  manageDeleteCompany(@PathVariable String e_id ) {
        return manageService.manageDeleteCompany(Integer.valueOf(e_id));
    }

    //用户管理
    @PostMapping("/admin/manage_user/getAllUser")
    public PageInfo<User> getUserByCondition(@RequestBody Map<String, Map> para,@RequestParam(value = "key",required = false) String key ,
                                           @RequestParam(value = "flag",required = false) String flag) {
        Integer pageIndex=Integer.valueOf(para.get("params").get("pageNow").toString());
        Integer pageSize=Integer.valueOf(para.get("params").get("pageSize").toString());
        return manageService.getUserByCondition(pageIndex,pageSize,key,flag);
    }

    @RequestMapping("/admin/manage_user/delete/{e_id}")
    public  String  manageDeleteUser(@PathVariable String e_id ) {
        return manageService.manageDeleteUser(Integer.valueOf(e_id));
    }


    //管理员管理
    @PostMapping("/admin/manage_admin/getAllAdmin")
    public PageInfo<Admin> getAdminByCondition(@RequestBody Map<String, Map> para, @RequestParam(value = "key",required = false) String key ,
                                               @RequestParam(value = "flag",required = false) String flag) {
        Integer pageIndex=Integer.valueOf(para.get("params").get("pageNow").toString());
        Integer pageSize=Integer.valueOf(para.get("params").get("pageSize").toString());
        System.out.println("key= "+key);
        System.out.println("flag= "+flag);
        return manageService.getAdminByCondition(pageIndex,pageSize,key,flag);
    }

    @RequestMapping("/admin/manage_admin/add")
    public  String  manageAddUser(@RequestBody Map<String, Map> para) throws ParseException {
        String admin_account=para.get("params").get("admin_account").toString();
        String admin_pwd=para.get("params").get("admin_pwd").toString();
        String admin_name=para.get("params").get("admin_name").toString();
        Integer admin_power=Integer.valueOf(para.get("params").get("admin_power").toString());
        String apartment=para.get("params").get("apartment").toString();
        String position=para.get("params").get("position").toString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date admin_time=simpleDateFormat.parse(para.get("params").get("admin_time").toString());
        return manageService.manageAddUser(admin_account,admin_pwd,admin_name,admin_power,apartment,position,admin_time);
    }


    @RequestMapping(value = "/admin/manage_admin/edit",method = RequestMethod.POST)
    @ResponseBody
    public  String  manageEditUser(@RequestBody Map<String, Map> para)  throws JsonProcessingException{
        Integer admin_id=Integer.valueOf(para.get("params").get("admin_id").toString());

        String admin_name=para.get("params").get("admin_name").toString();
        String admin_pwd=para.get("params").get("admin_pwd").toString();
        Integer admin_power=Integer.valueOf(para.get("params").get("admin_power").toString());
        System.out.println("admin_id = "+admin_id);
        return manageService.manageEditUser(admin_id,admin_name,admin_pwd,admin_power);
    }

    @RequestMapping("/admin/admin_home/userChange")
    public  List  userChange() {
        return manageService.userChange();
    }

    @RequestMapping("/admin/admin_home/companyChange")
    public  List  companyChange() {
        return manageService.companyChange();
    }
}
