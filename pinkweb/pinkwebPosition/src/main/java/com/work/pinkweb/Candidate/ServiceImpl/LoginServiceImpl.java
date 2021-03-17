package com.work.pinkweb.Candidate.ServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.pinkweb.Candidate.Mapper.LoginMapper;
import com.work.pinkweb.Candidate.Service.LoginService;
import com.work.pinkweb.Entity.User;
import com.work.pinkweb.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public String candidateLogin(String phone, String password) throws JsonProcessingException {
        User user = loginMapper.userLogin(phone);
        if(user!=null){
            if(password.equals(user.getPassword())){
                String token= TokenUtil.sign(user);
                HashMap<String,Object> hs=new HashMap<>();
                hs.put("token",token);
                ObjectMapper objectMapper=new ObjectMapper();
                return objectMapper.writeValueAsString(hs);
            }else{
                return "账号或密码错误";
            }
        }else{
            return "用户不存在";
        }
    }

    @Override
    public User getUserInfo(String phone) {
        return loginMapper.userLogin(phone);
    }
}
