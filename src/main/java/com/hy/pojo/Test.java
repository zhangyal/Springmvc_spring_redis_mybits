package com.hy.pojo;

import java.io.Serializable;

/**
 * 序列化Serializable接口
 */
public class Test implements Serializable {
    private Integer id;

    private String name;

    private String pawd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPawd() {
        return pawd;
    }

    public void setPawd(String pawd) {
        this.pawd = pawd == null ? null : pawd.trim();
    }
}