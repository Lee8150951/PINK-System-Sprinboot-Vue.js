package com.work.pinkweb.HR.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.work.pinkweb.Entity.User;

public interface HrLoginService {
    //求职者登录
    String hrLogin(String phone,String password) throws JsonProcessingException;
    //获取用户信息
    User getHrInfo(String phone);
}
