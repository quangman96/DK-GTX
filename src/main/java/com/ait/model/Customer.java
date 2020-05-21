package com.ait.model;

import java.util.Date;

public class Customer {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String identity;
    private Date current_day;
    private Integer isDelete;

    private Long province_id;
    private String province_name;

    public Customer(Long id, String name, String address, String phone, String identity,
                    Long province_id,String province_name, Integer isDelete ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.identity = identity;
//        this.current_day = current_day;
        this.isDelete = isDelete;
        this.province_id = province_id;
        this.province_name = province_name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCurrent_day() {
        return current_day;
    }

    public void setCurrent_day(Date current_day) {
        this.current_day = current_day;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Long province_id) {
        this.province_id = province_id;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
