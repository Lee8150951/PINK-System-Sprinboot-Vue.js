package com.work.pinkweb.Candidate.Mapper;

import com.work.pinkweb.Entity.Candidate;
import com.work.pinkweb.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EditMapper {
     Candidate getCandidateInfo(Integer id);

     @Update("update user set name=#{name},phone=#{phone},sex=#{sex},email=#{email},birthday=#{birthday} where id=#{id}")
     Integer modifyUserInfo(User user);

     @Update("update candidate set c_vx=#{c_vx},c_type=#{c_type},c_intro=#{c_intro},c_finternship_enterprise=#{c_finternship_enterprise}," +
             " c_finternship_job=#{c_finternship_job},c_finternship_stime=#{c_finternship_stime}," +
             "c_finternship_etime=#{c_finternship_etime},c_sinternship_enterprise=#{c_sinternship_enterprise} ," +
             "c_sinternship_job=#{c_sinternship_job},c_sinternship_stime=#{c_sinternship_stime},c_sinternship_etime=#{c_sinternship_etime}," +
             "c_fdegree=#{c_fdegree},c_fschool=#{c_fschool},c_fmajor=#{c_fmajor},c_ftime=#{c_ftime}," +
             "c_sdegree=#{c_sdegree},c_sschool=#{c_sschool},c_smajor=#{c_smajor},c_stime=#{c_stime}," +
             "c_exindustry=#{c_exindustry},c_exposition=#{c_exposition},c_excity=#{c_excity},c_exsalary=#{c_exsalary} where c_id=#{c_id}")
     Integer modifyCandidateInfo(Candidate candidate);

     @Update("update user set avatar=#{avatar} where id=#{uid}")
     Integer avatarUpdate(Integer uid,String avatar);
}
