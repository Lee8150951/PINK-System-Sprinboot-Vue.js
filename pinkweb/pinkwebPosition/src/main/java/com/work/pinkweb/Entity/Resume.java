package com.work.pinkweb.Entity;

import java.util.Date;

public class Resume {
    private Integer resume_id;
    private Integer uid;
    private String name;
    private String url;
    private Date created_time;
    public Integer getResume_id() {
        return resume_id;
    }

    public void setResume_id(Integer resume_id) {
        this.resume_id = resume_id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "resume_id=" + resume_id +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
