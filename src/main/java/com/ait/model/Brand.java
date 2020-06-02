package com.ait.model;

import java.util.Date;

public class Brand {

    private Long id;
    private String name;
    private Integer isDelete;
    private Date create_date;

    public Brand(Long id, String name, Integer isDelete, Date create_date) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
        this.create_date = create_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}

