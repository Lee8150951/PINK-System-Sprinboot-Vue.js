package com.work.pinkweb.Admin.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.work.pinkweb.Entity.Admin;
import com.work.pinkweb.Entity.Company;
import com.work.pinkweb.Entity.User;

import java.util.Date;
import java.util.List;

public interface ManageService {
    String adminLogin(String admin_account,String admin_pwd) throws JsonProcessingException;


    String manageDeleteCompany(Integer e_id);
    PageInfo<User> getUserByCondition(Integer pageIndex, Integer pageSize,String key,String flag);
    String manageDeleteUser(Integer id);

    PageInfo<Admin> getAdminByCondition(Integer pageIndex, Integer pageSize, String key, String flag);
    String manageAddUser(String admin_account, String admin_pwd, String admin_name, Integer admin_power, String apartment, String position, Date admin_time);
    String manageEditUser(Integer admin_id,String admin_name ,String admin_pwd,Integer admin_power);

    List userChange();
    List companyChange();
}
