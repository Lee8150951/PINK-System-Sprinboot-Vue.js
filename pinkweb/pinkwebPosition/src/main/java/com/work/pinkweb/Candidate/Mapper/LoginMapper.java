package com.work.pinkweb.Candidate.Mapper;

import com.work.pinkweb.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper {

    //查找是否有该求职者账户
    @Select("select * from user where phone = #{phone}")
    User userLogin(String phone);
}
