package com.work.pinkweb.Entity;

import java.util.Date;
import java.util.List;

public class Company {
    private Integer e_id;
    private String e_name;
    private String e_introduction;
    private String e_legalrepresentative;
    private String e_registeredcapital;
    private Date e_establishtime;
    private String e_type;
    private String e_size;
    private String e_operationstatus;
    private String e_registeredaddress;
    private String e_unifiedcreditcode;
    private String e_businessscope;
    private String e_licenseimg;
    private String e_address;
    private Integer is_delete;
    private Integer upload_userid;
    private Date created_time;
    private String e_logo;
    private String e_executive;
    private String e_position;
    private String e_executive_intro;
    private String e_phone;
    //在招岗位数量
    private Integer positionNum;
    //该公司的HR在总数
    private Integer hrNum;
    private List<Recruitment> RecruitmentList;
    private List<CompanyHr> companyHrList;
    public Company() {
    }


    public Integer getE_id() {
        return e_id;
    }

    public void setE_id(Integer e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_introduction() {
        return e_introduction;
    }

    public void setE_introduction(String e_introduction) {
        this.e_introduction = e_introduction;
    }

    public String getE_legalrepresentative() {
        return e_legalrepresentative;
    }

    public void setE_legalrepresentative(String e_legalrepresentative) {
        this.e_legalrepresentative = e_legalrepresentative;
    }

    public String getE_registeredcapital() {
        return e_registeredcapital;
    }

    public void setE_registeredcapital(String e_registeredcapital) {
        this.e_registeredcapital = e_registeredcapital;
    }

    public Date getE_establishtime() {
        return e_establishtime;
    }

    public void setE_establishtime(Date e_establishtime) {
        this.e_establishtime = e_establishtime;
    }

    public String getE_type() {
        return e_type;
    }

    public void setE_type(String e_type) {
        this.e_type = e_type;
    }

    public String getE_size() {
        return e_size;
    }

    public void setE_size(String e_size) {
        this.e_size = e_size;
    }

    public String getE_operationstatus() {
        return e_operationstatus;
    }

    public void setE_operationstatus(String e_operationstatus) {
        this.e_operationstatus = e_operationstatus;
    }

    public String getE_registeredaddress() {
        return e_registeredaddress;
    }

    public void setE_registeredaddress(String e_registeredaddress) {
        this.e_registeredaddress = e_registeredaddress;
    }

    public String getE_unifiedcreditcode() {
        return e_unifiedcreditcode;
    }

    public void setE_unifiedcreditcode(String e_unifiedcreditcode) {
        this.e_unifiedcreditcode = e_unifiedcreditcode;
    }

    public String getE_businessscope() {
        return e_businessscope;
    }

    public void setE_businessscope(String e_businessscope) {
        this.e_businessscope = e_businessscope;
    }

    public String getE_licenseimg() {
        return e_licenseimg;
    }

    public void setE_licenseimg(String e_licenseimg) {
        this.e_licenseimg = e_licenseimg;
    }

    public String getE_address() {
        return e_address;
    }

    public void setE_address(String e_address) {
        this.e_address = e_address;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Integer getUpload_userid() {
        return upload_userid;
    }

    public void setUpload_userid(Integer upload_userid) {
        this.upload_userid = upload_userid;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public String getE_logo() {
        return e_logo;
    }

    public void setE_logo(String e_logo) {
        this.e_logo = e_logo;
    }

    public String getE_executive() {
        return e_executive;
    }

    public void setE_executive(String e_executive) {
        this.e_executive = e_executive;
    }

    public String getE_position() {
        return e_position;
    }

    public void setE_position(String e_position) {
        this.e_position = e_position;
    }

    public String getE_executive_intro() {
        return e_executive_intro;
    }

    public void setE_executive_intro(String e_executive_intro) {
        this.e_executive_intro = e_executive_intro;
    }

    public String getE_phone() {
        return e_phone;
    }

    public void setE_phone(String e_phone) {
        this.e_phone = e_phone;
    }

    public Integer getPositionNum() {
        return positionNum;
    }

    public void setPositionNum(Integer positionNum) {
        this.positionNum = positionNum;
    }

    public Integer getHrNum() {
        return hrNum;
    }

    public void setHrNum(Integer hrNum) {
        this.hrNum = hrNum;
    }

    public List<CompanyHr> getCompanyHrList() {
        return companyHrList;
    }

    public void setCompanyHrList(List<CompanyHr> companyHrList) {
        this.companyHrList = companyHrList;
    }

    public List<Recruitment> getRecruitmentList() {
        return RecruitmentList;
    }

    public void setRecruitmentList(List<Recruitment> recruitmentList) {
        RecruitmentList = recruitmentList;
    }

    public Company(Integer e_id, String e_name, String e_introduction, String e_legalrepresentative, String e_registeredcapital, Date e_establishtime, String e_type, String e_size, String e_operationstatus, String e_registeredaddress, String e_unifiedcreditcode, String e_businessscope, String e_licenseimg, String e_address, Integer is_delete, Integer upload_userid, Date created_time, String e_logo, String e_executive, String e_position, String e_executive_intro, String e_phone, Integer positionNum, Integer hrNum, List<Recruitment> recruitmentList, List<CompanyHr> companyHrList) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.e_introduction = e_introduction;
        this.e_legalrepresentative = e_legalrepresentative;
        this.e_registeredcapital = e_registeredcapital;
        this.e_establishtime = e_establishtime;
        this.e_type = e_type;
        this.e_size = e_size;
        this.e_operationstatus = e_operationstatus;
        this.e_registeredaddress = e_registeredaddress;
        this.e_unifiedcreditcode = e_unifiedcreditcode;
        this.e_businessscope = e_businessscope;
        this.e_licenseimg = e_licenseimg;
        this.e_address = e_address;
        this.is_delete = is_delete;
        this.upload_userid = upload_userid;
        this.created_time = created_time;
        this.e_logo = e_logo;
        this.e_executive = e_executive;
        this.e_position = e_position;
        this.e_executive_intro = e_executive_intro;
        this.e_phone = e_phone;
        this.positionNum = positionNum;
        this.hrNum = hrNum;
        RecruitmentList = recruitmentList;
        this.companyHrList = companyHrList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "e_id=" + e_id +
                ", e_name='" + e_name + '\'' +
                ", e_introduction='" + e_introduction + '\'' +
                ", e_legalrepresentative='" + e_legalrepresentative + '\'' +
                ", e_registeredcapital='" + e_registeredcapital + '\'' +
                ", e_establishtime=" + e_establishtime +
                ", e_type='" + e_type + '\'' +
                ", e_size=" + e_size +
                ", e_operationstatus='" + e_operationstatus + '\'' +
                ", e_registeredaddress='" + e_registeredaddress + '\'' +
                ", e_unifiedcreditcode='" + e_unifiedcreditcode + '\'' +
                ", e_businessscope='" + e_businessscope + '\'' +
                ", e_licenseimg='" + e_licenseimg + '\'' +
                ", e_address='" + e_address + '\'' +
                ", is_delete=" + is_delete +
                ", upload_userid=" + upload_userid +
                ", created_time=" + created_time +
                ", e_logo='" + e_logo + '\'' +
                ", e_executive='" + e_executive + '\'' +
                ", e_position='" + e_position + '\'' +
                ", e_executive_intro='" + e_executive_intro + '\'' +
                ", e_phone='" + e_phone + '\'' +
                ", positionNum=" + positionNum +
                ", hrNum=" + hrNum +
                ", RecruitmentList=" + RecruitmentList +
                ", companyHrList=" + companyHrList +
                '}';
    }
}

