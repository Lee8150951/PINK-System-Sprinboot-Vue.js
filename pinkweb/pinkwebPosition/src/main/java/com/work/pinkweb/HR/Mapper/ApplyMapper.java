package com.work.pinkweb.HR.Mapper;

import com.work.pinkweb.Entity.Apply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApplyMapper {
    //根据岗位id搜索信息

    List<Apply> getAllApply(Integer r_id);
//    Apply uoloadApply(Integer uid,Integer r_id);
//    //根据apply_id查询，删除
    Apply getCandidateByApply(Integer apply_id);
    Integer deleteApplyById(Integer apply_id);
    List<Apply> getLineByDate(String time,Integer r_id);

    @Select("SELECT count(*) FROM apply WHERE TO_DAYS( NOW( ) ) - TO_DAYS( created_time) < #{i} && TO_DAYS( NOW( ) ) - TO_DAYS( created_time) >= (#{i}-1) and p_id = #{r_id}")
    Integer applyChange(Integer i,Integer r_id);

    @Select("SELECT count(*) FROM apply " +
            "left join " +
            "recruitment_info on apply.p_id =recruitment_info.r_id " +
            "left join enterprise_hr on enterprise_hr.ehr_id  = recruitment_info.ehrid " +
            "WHERE TO_DAYS( NOW( ) ) - TO_DAYS( apply.created_time) < #{i} && TO_DAYS( NOW( ) ) - TO_DAYS( apply.created_time) >= (#{i}-1) and enterprise_hr.ehr_id = #{r_id}")
    Integer applyCompanyChange(Integer i,Integer r_id);
}
