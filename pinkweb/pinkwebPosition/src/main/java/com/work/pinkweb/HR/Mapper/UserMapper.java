package com.work.pinkweb.HR.Mapper;
import com.work.pinkweb.Entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    Integer updateAvatarById(Integer uid,String avatar);
    User getUserById(Integer uid);
}
