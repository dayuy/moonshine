package com.geekbang.exercise.char10.dao_.domain;

import java.time.LocalDateTime;

// 一个Actor对象对应一条数据库表actor取出的一条数据
public class Actor {
    private Integer id;
    private String user_name;
    private String sex;
    private LocalDateTime borndate;
    private String phone;

    public Actor() {} // 映射需要

    public Actor(String user_name, String sex, LocalDateTime borndate, String phone) {
        this.user_name = user_name;
        this.sex = sex;
        this.borndate = borndate;
        this.phone = phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDateTime getBorndate() {
        return borndate;
    }

    public void setBorndate(LocalDateTime borndate) {
        this.borndate = borndate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "\nActor{" +
                "user_name='" + user_name + '\'' +
                ", sex=" + sex +
                ", borndate=" + borndate +
                ", phone='" + phone + '\'' +
                '}';
    }
}
