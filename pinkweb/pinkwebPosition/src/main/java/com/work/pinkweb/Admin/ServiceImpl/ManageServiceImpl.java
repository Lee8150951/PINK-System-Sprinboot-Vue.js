package com.work.pinkweb.Admin.ServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Admin.Mapper.ManageMapper;
import com.work.pinkweb.Admin.Service.ManageService;
import com.work.pinkweb.Entity.Admin;
import com.work.pinkweb.Entity.User;
import com.work.pinkweb.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private ManageMapper manageMapper;

    @Override
    public String adminLogin(String admin_account, String admin_pwd) throws JsonProcessingException {
        Admin admin = manageMapper.adminLogin(admin_account);

        if(admin!=null){
            if(Integer.valueOf(admin_pwd)==Integer.valueOf(admin.getAdmin_pwd())){
                System.out.println("yew");
                String token= TokenUtil.sign(admin);
                HashMap<String,Object> hs=new HashMap<>();
                hs.put("token",token);
                ObjectMapper objectMapper=new ObjectMapper();
                return objectMapper.writeValueAsString(hs);
            }else{
                System.out.println("no");
                return "账号或密码错误";
            }
        }else{
            return "用户不存在";
        }
    }

    @Override
    public String manageDeleteCompany(Integer e_id) {
        manageMapper.manageDeleteCompany(e_id);
        return "manageDeleteCompany-success";
    }

    @Override
    public PageInfo<User> getUserByCondition(Integer pageIndex, Integer pageSize,String key,String flag) {

        PageHelper.startPage(pageIndex,pageSize);//一个设置
        List<User> ulist = null;
        if(flag.equals("undefined")){
            ulist= manageMapper.manageGetAllUser();
        }else{
            ulist= manageMapper.getUserByName(flag);

        }
        PageInfo<User> info=new PageInfo<>(ulist);
        return info;
    }

    @Override
    public String manageDeleteUser(Integer id) {
        manageMapper.manageDeleteUser(id);
        return "manageDeleteUser-success";
    }

    @Override
    public PageInfo<Admin> getAdminByCondition(Integer pageIndex, Integer pageSize, String key, String flag) {
        PageHelper.startPage(pageIndex,pageSize);//一个设置
        List<Admin> alist = null;
        if(flag.equals("undefined")){
            alist= manageMapper.manageGetAllAdmin();
        }else{
            switch (key){
                case "getAdminByID":
                    alist= manageMapper.getAdminById(Integer.valueOf(flag));
                    break;
                case "getAdminByAccount":
                    alist= manageMapper.getAdminByAccount(flag);
                    break;
                default:
                    alist= manageMapper.getAdminByName(flag);
                    break;
            }


        }
        PageInfo<Admin> info=new PageInfo<>(alist);
        return info;
    }

    @Override
    public String manageAddUser(String admin_account, String admin_pwd, String admin_name, Integer admin_power, String apartment, String position, Date admin_time) {
        manageMapper.manageAddUser(admin_account,admin_pwd,admin_name,admin_power,apartment,position,admin_time);
        return "manageAddUser-success";
    }

    @Override
    public String manageEditUser(Integer admin_id,String admin_name ,String admin_pwd,Integer admin_power) {
        manageMapper.manageEditUser(admin_id,admin_name,admin_pwd,admin_power);
        return "manageEditUser-success";
    }

    @Override
    public List userChange() {
        List li = new ArrayList();
        for(int i=10;i>0;i--){
            li.add(manageMapper.userChange(i));
        }
        return li;
    }

    @Override
    public List companyChange() {
        List li = new ArrayList();
        for(int i=10;i>0;i--){
            li.add(manageMapper.companyChange(i));
        }
        System.out.println(li);
        return li;
    }
}
