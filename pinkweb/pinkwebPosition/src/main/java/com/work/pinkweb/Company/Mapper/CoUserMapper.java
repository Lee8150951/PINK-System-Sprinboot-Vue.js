package com.work.pinkweb.Company.Mapper;

import com.work.pinkweb.Entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoUserMapper {
    Integer insertOneUser(User user);
    User getByPhone(String phone);
}
