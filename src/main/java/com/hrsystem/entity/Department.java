package com.hrsystem.entity;

import java.io.Serializable;
import java.util.Date;

public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String code;
    private Date createTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
