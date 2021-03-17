package com.work.pinkweb.Candidate.Mapper;

import com.work.pinkweb.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface RegisterMapper {
    @Insert("insert into user (phone, password) values (#{phone}, #{password})")
    Integer userRegister(String phone,String password);

    @Select("select * from user where phone = #{phone}")
    User getNewUser(String phone);

    @Insert("insert into candidate (c_id) values (#{c_id})")
    Integer candidateRegister(Integer c_id);

    @Update("update user set name=#{name},sex=#{sex},birthday=#{birthday}, type=0 where phone=#{phone}")
    Integer perfectCandidateInfo(String phone, String name, Integer sex, Date birthday);
}
