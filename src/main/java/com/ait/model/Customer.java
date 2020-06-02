package com.ait.model;

import java.util.Date;

public class Customer {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String identity;
    private Date create_date;
    private Integer isDelete;

    private Long province_id;
    private String province_name;

    private Long amount;

    public Customer(Long id, String name, String address, String phone, String identity,
                    Long province_id, String province_name, Integer isDelete, Date create_date) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.identity = identity;
        this.isDelete = isDelete;
        this.province_id = province_id;
        this.province_name = province_name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
