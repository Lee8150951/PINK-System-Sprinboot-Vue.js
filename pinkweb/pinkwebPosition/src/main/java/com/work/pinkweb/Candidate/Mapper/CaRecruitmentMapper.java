package com.work.pinkweb.Candidate.Mapper;

import com.work.pinkweb.Entity.Recruitment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
@Mapper
public interface CaRecruitmentMapper {
    List<Recruitment> getAllRecruitment();
    Recruitment getRecruitmentDetailsById(Integer r_id);
    List<Recruitment> getByName(String r_name);
    List<Recruitment> getByDegree(String degree);
    List<Recruitment> getBySalary(String salary);
    List<Recruitment> getByTime(Float timetag);
    List<Recruitment>  getByExperience(String ex);
    List<Recruitment>  getByCity(String city);
    List<Recruitment> getHotRecruitment();
    @Insert("insert into apply (u_id, p_id,r_id,created_time,is_delete) values (#{u_id}, #{p_id}, #{r_id}, #{created_time}, 0)")
    Integer applyResume(Integer u_id, Integer p_id, Integer r_id,Date created_time);
    @Select("select * from apply where u_id = #{u_id} and p_id = #{p_id}")
    Integer judgeApplyResume(Integer u_id,Integer p_id);
}
