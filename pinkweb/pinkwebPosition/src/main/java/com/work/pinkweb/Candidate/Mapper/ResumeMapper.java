package com.work.pinkweb.Candidate.Mapper;

import com.work.pinkweb.Entity.Resume;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface ResumeMapper {

    @Select("select count(*) from resume where uid = #{c_id};")
    Integer countResumePDF(Integer c_id);

    @Insert("insert into resume (uid, name,url,created_time) values (#{uid}, #{name}, #{url}, #{created_time})")
    Integer uploadResumePDF(Integer uid, String name, String url, Date created_time);

    @Select("select * from resume where uid = #{c_id}")
    List<Resume> findAllResumePDF(Integer c_id);

    @Delete("delete from resume where resume_id = #{r_id}")
    Integer deleteResumePDF(Integer r_id);

    @Select("select * from resume where resume_id = #{resume_id}")
    Resume findResumePDF(Integer r_id);
}
