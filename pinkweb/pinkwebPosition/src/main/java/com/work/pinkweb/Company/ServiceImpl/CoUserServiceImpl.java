package com.work.pinkweb.Company.ServiceImpl;

import com.work.pinkweb.Entity.User;
import com.work.pinkweb.Company.Mapper.CoUserMapper;
import com.work.pinkweb.Company.Service.CoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CoUserServiceImpl implements CoUserService {

    @Autowired
    private CoUserMapper coUserMapper;


    @Override
    public Integer insertOneUser(User user) {
        user.setCreated_time(new Date());

        return coUserMapper.insertOneUser(user);
    }

    @Override
    public User getByPhone(String phone) {
        return coUserMapper.getByPhone(phone);
    }
}
