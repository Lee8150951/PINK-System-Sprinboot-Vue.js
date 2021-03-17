package com.work.pinkweb.Admin.Mapper;

import com.work.pinkweb.Entity.Admin;
import com.work.pinkweb.Entity.Resume;
import com.work.pinkweb.Entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface ManageMapper {

    @Select("select * from admin where admin_account = #{admin_account}")
    Admin adminLogin(String admin_account);

    @Delete("delete from enterprise_info where e_id = #{e_id}")
    Integer manageDeleteCompany(Integer e_id);

    @Select("select * from user where type = 0")
    List<User> manageGetAllUser();

    @Select("select * from user where name like CONCAT(CONCAT('%',#{name}),'%')")
    List<User> getUserByName(String name);


    @Delete("delete from user where id = #{id}")
    Integer manageDeleteUser(Integer id);

    @Select("select * from admin ")
    List<Admin> manageGetAllAdmin();

    @Select("select * from admin where admin_name like CONCAT(CONCAT('%',#{admin_name}),'%')")
    List<Admin> getAdminByName(String admin_name);

    @Insert("insert into admin (admin_account,admin_pwd,admin_name,admin_power,apartment,position,admin_time) values (#{admin_account}, #{admin_pwd}, #{admin_name}, #{admin_power}, #{apartment}, #{position}, #{admin_time})")
    Integer manageAddUser(String admin_account, String admin_pwd, String admin_name, Integer admin_power, String apartment, String position, Date admin_time);


    @Update("update admin set admin_name=#{admin_name},admin_pwd=#{admin_pwd},admin_power=#{admin_power} where admin_id=#{admin_id}")
    Integer manageEditUser(Integer admin_id,String admin_name ,String admin_pwd,Integer admin_power);

    @Select("select * from admin where admin_id = #{admin_id}")
    List<Admin> getAdminById(Integer admin_id);

    @Select("select * from admin where admin_account = #{admin_account}")
    List<Admin> getAdminByAccount(String admin_account);

    @Select("SELECT count(*) FROM user WHERE TO_DAYS( NOW( ) ) - TO_DAYS( created_time) < #{i} && TO_DAYS( NOW( ) ) - TO_DAYS( created_time) >= (#{i}-1)")
    Integer userChange(Integer i);


    @Select("SELECT count(*) FROM enterprise_info WHERE TO_DAYS( NOW( ) ) - TO_DAYS( created_time) < #{i} && TO_DAYS( NOW( ) ) - TO_DAYS( created_time) >= (#{i}-1)")
    Integer companyChange(Integer i);
}
