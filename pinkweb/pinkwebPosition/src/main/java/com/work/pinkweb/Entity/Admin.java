package com.work.pinkweb.Entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Admin {
    private Integer admin_id;
    private String admin_account;
    private Integer admin_pwd;
    private String admin_name;
    private Integer admin_power;
    private String apartment;
    private String position;
    private Date admin_time;


    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_account() {
        return admin_account;
    }

    public void setAdmin_account(String admin_account) {
        this.admin_account = admin_account;
    }

    public Integer getAdmin_pwd() {
        return admin_pwd;
    }

    public void setAdmin_pwd(Integer admin_pwd) {
        this.admin_pwd = admin_pwd;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public Integer getAdmin_power() {
        return admin_power;
    }

    public void setAdmin_power(Integer admin_power) {
        this.admin_power = admin_power;
    }

    public String getAdmin_time() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String signTime = sdf.format(admin_time);
        return signTime;
    }

    public void setAdmin_time(Date admin_time) {
        this.admin_time = admin_time;
    }

}
