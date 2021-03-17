package com.work.pinkweb.HR.ServiceImpl;

import com.work.pinkweb.Entity.User;
import com.work.pinkweb.HR.Mapper.UserMapper;
import com.work.pinkweb.HR.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer updateAvatarById(Integer uid, String avatar) {

        return userMapper.updateAvatarById(uid,avatar);
    }

    @Override
    public User getUserById(Integer uid) {
        return userMapper.getUserById(uid);
    }
}
