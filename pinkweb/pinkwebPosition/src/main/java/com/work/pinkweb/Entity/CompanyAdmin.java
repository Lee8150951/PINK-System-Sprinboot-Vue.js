package com.work.pinkweb.Entity;

import com.work.pinkweb.Entity.Company;
import com.work.pinkweb.Entity.User;

public class CompanyAdmin {
    private Integer ea_id;
    private Integer e_id;
    private Integer em_applyaccount;
    private Integer em_currentaccount;
    private Integer em_vipauthority;
    private Integer is_delete;
    private User user;
    private Company company;

    public CompanyAdmin() {
    }

    public CompanyAdmin(Integer ea_id, Integer e_id, Integer em_applyaccount, Integer em_currentaccount, Integer em_vipauthority, Integer is_delete, User user, Company company) {
        this.ea_id = ea_id;
        this.e_id = e_id;
        this.em_applyaccount = em_applyaccount;
        this.em_currentaccount = em_currentaccount;
        this.em_vipauthority = em_vipauthority;
        this.is_delete = is_delete;
        this.user = user;
        this.company = company;
    }

    public Integer getEa_id() {
        return ea_id;
    }

    public void setEa_id(Integer ea_id) {
        this.ea_id = ea_id;
    }

    public Integer getE_id() {
        return e_id;
    }

    public void setE_id(Integer e_id) {
        this.e_id = e_id;
    }

    public Integer getEm_applyaccount() {
        return em_applyaccount;
    }

    public void setEm_applyaccount(Integer em_applyaccount) {
        this.em_applyaccount = em_applyaccount;
    }

    public Integer getEm_currentaccount() {
        return em_currentaccount;
    }

    public void setEm_currentaccount(Integer em_currentaccount) {
        this.em_currentaccount = em_currentaccount;
    }

    public Integer getEm_vipauthority() {
        return em_vipauthority;
    }

    public void setEm_vipauthority(Integer em_vipauthority) {
        this.em_vipauthority = em_vipauthority;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "CompanyAdmin{" +
                "ea_id=" + ea_id +
                ", e_id=" + e_id +
                ", em_applyaccount=" + em_applyaccount +
                ", em_currentaccount=" + em_currentaccount +
                ", em_vipauthority=" + em_vipauthority +
                ", is_delete=" + is_delete +
                ", user=" + user +
                ", company=" + company +
                '}';
    }
}
