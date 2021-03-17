package com.work.pinkweb.Entity;

import java.util.List;

public class CompanyHr {
    private Integer ehr_id;
    private Integer enterprise_id;
    private Integer ehr_applyaccount;
    private Integer ehr_currentaccount;
    private Integer ehr_collect;
    private Integer ehr_vipauthority;
    private String ehr_jobnum;
    private String ehr_vx;
    private Integer is_delete;
    private String ehr_position;
    private Company company;
    private User user;
    private  List<Recruitment> recruitmentList;
    private Integer hr_account;
    public CompanyHr() {
    }

    public Integer getHr_account() {
        return hr_account;
    }

    public void setHr_account(Integer hr_account) {
        this.hr_account = hr_account;
    }

    public Integer getEhr_id() {
        return ehr_id;
    }

    public void setEhr_id(Integer ehr_id) {
        this.ehr_id = ehr_id;
    }

    public Integer getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(Integer enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public Integer getEhr_applyaccount() {
        return ehr_applyaccount;
    }

    public void setEhr_applyaccount(Integer ehr_applyaccount) {
        this.ehr_applyaccount = ehr_applyaccount;
    }

    public Integer getEhr_currentaccount() {
        return ehr_currentaccount;
    }

    public void setEhr_currentaccount(Integer ehr_currentaccount) {
        this.ehr_currentaccount = ehr_currentaccount;
    }

    public Integer getEhr_collect() {
        return ehr_collect;
    }

    public void setEhr_collect(Integer ehr_collect) {
        this.ehr_collect = ehr_collect;
    }

    public Integer getEhr_vipauthority() {
        return ehr_vipauthority;
    }

    public void setEhr_vipauthority(Integer ehr_vipauthority) {
        this.ehr_vipauthority = ehr_vipauthority;
    }

    public String getEhr_jobnum() {
        return ehr_jobnum;
    }

    public void setEhr_jobnum(String ehr_jobnum) {
        this.ehr_jobnum = ehr_jobnum;
    }

    public String getEhr_vx() {
        return ehr_vx;
    }

    public void setEhr_vx(String ehr_vx) {
        this.ehr_vx = ehr_vx;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public String getEhr_position() {
        return ehr_position;
    }

    public void setEhr_position(String ehr_position) {
        this.ehr_position = ehr_position;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Recruitment> getRecruitmentList() {
        return recruitmentList;
    }

    public void setRecruitmentList(List<Recruitment> recruitmentList) {
        this.recruitmentList = recruitmentList;
    }

    @Override
    public String toString() {
        return "CompanyHr{" +
                "ehr_id=" + ehr_id +
                ", enterprise_id=" + enterprise_id +
                ", ehr_applyaccount=" + ehr_applyaccount +
                ", ehr_currentaccount=" + ehr_currentaccount +
                ", ehr_collect=" + ehr_collect +
                ", ehr_vipauthority=" + ehr_vipauthority +
                ", ehr_jobnum='" + ehr_jobnum + '\'' +
                ", ehr_vx='" + ehr_vx + '\'' +
                ", is_delete=" + is_delete +
                ", ehr_position='" + ehr_position + '\'' +
                ", company=" + company +
                ", user=" + user +
                ", recruitmentList=" + recruitmentList +
                '}';
    }
}
