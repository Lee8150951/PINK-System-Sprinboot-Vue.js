package com.work.pinkweb.Entity;

import java.util.Date;

public class Apply {
    private Integer apply_id;
    private Integer u_id;
    private Integer p_id;
    private Date created_time;
    private Integer is_delete;
    private Integer r_id;
    //根据Candidate得到申请人的相关信息
    private Candidate candidate;
    //根据recruitment得到该岗位的id等信息；
    private Recruitment recruitment;
    private Resume resume;

    public Apply() {
    }



    public Integer getApply_id() {
        return apply_id;
    }

    public void setApply_id(Integer apply_id) {
        this.apply_id = apply_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }


    public Integer getR_id() {
        return r_id;
    }

    public void setR_id(Integer r_id) {
        this.r_id = r_id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Recruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }



    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "apply_id=" + apply_id +
                ", u_id=" + u_id +
                ", p_id=" + p_id +
                ", created_time=" + created_time +
                ", is_delete=" + is_delete +
                ", r_id=" + r_id +
                ", candidate=" + candidate +
                ", recruitment=" + recruitment +
                ", resume=" + resume +
                '}';
    }
}
