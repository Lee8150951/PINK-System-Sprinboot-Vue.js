package com.work.pinkweb.Entity;

import java.util.Date;
import java.util.List;

public class Recruitment {
    private Integer ehrid;
    private Integer r_id;
    private String r_name;
    private String r_salary;
    private String r_address;
    private String r_experience;
    private String r_education;
    private String r_introduction;
    private Float r_timetag;
    private String r_tag;
    private Date created_time;
    private List<Apply> applyList;
    private Integer applyNum;
    private Integer collectNum;
    private CompanyHr companyHr;
    private Integer total;
    public Recruitment() {
    }

    public Recruitment(Integer ehrid, Integer r_id, String r_name, String r_salary, String r_address, String r_experience, String r_education, String r_introduction, Float r_timetag, String r_tag, Date created_time, List<Apply> applyList, Integer applyNum, Integer collectNum) {
        this.ehrid = ehrid;
        this.r_id = r_id;
        this.r_name = r_name;
        this.r_salary = r_salary;
        this.r_address = r_address;
        this.r_experience = r_experience;
        this.r_education = r_education;
        this.r_introduction = r_introduction;
        this.r_timetag = r_timetag;
        this.r_tag = r_tag;
        this.created_time = created_time;
        this.applyList = applyList;
        this.applyNum = applyNum;
        this.collectNum = collectNum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public CompanyHr getCompanyHr() {
        return companyHr;
    }

    public void setCompanyHr(CompanyHr companyHr) {
        this.companyHr = companyHr;
    }

    public Integer getEhrid() {
        return ehrid;
    }

    public void setEhrid(Integer ehrid) {
        this.ehrid = ehrid;
    }

    public Integer getR_id() {
        return r_id;
    }

    public void setR_id(Integer r_id) {
        this.r_id = r_id;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_salary() {
        return r_salary;
    }

    public void setR_salary(String r_salary) {
        this.r_salary = r_salary;
    }

    public String getR_address() {
        return r_address;
    }

    public void setR_address(String r_address) {
        this.r_address = r_address;
    }

    public String getR_experience() {
        return r_experience;
    }

    public void setR_experience(String r_experience) {
        this.r_experience = r_experience;
    }

    public String getR_education() {
        return r_education;
    }

    public void setR_education(String r_education) {
        this.r_education = r_education;
    }

    public String getR_introduction() {
        return r_introduction;
    }

    public void setR_introduction(String r_introduction) {
        this.r_introduction = r_introduction;
    }

    public Float getR_timetag() {
        return r_timetag;
    }

    public void setR_timetag(Float r_timetag) {
        this.r_timetag = r_timetag;
    }

    public String getR_tag() {
        return r_tag;
    }

    public void setR_tag(String r_tag) {
        this.r_tag = r_tag;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public List<Apply> getApplyList() {
        return applyList;
    }

    public void setApplyList(List<Apply> applyList) {
        this.applyList = applyList;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    @Override
    public String toString() {
        return "Recruitment{" +
                "ehrid=" + ehrid +
                ", r_id=" + r_id +
                ", r_name='" + r_name + '\'' +
                ", r_salary=" + r_salary +
                ", r_address='" + r_address + '\'' +
                ", r_experience='" + r_experience + '\'' +
                ", r_education='" + r_education + '\'' +
                ", r_introduction='" + r_introduction + '\'' +
                ", r_timetag=" + r_timetag +
                ", r_tag='" + r_tag + '\'' +
                ", created_time=" + created_time +
                ", applyList=" + applyList +
                ", applyNum=" + applyNum +
                ", collectNum=" + collectNum +
                '}';
    }
}
