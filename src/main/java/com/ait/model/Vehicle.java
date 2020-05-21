package com.ait.model;

public class Vehicle {
    private Long id;
    private String engine_num;
    private String chassis_num;
    private Integer isDelete;

    private Long customer_id;
    private String customer_name;

    private Long brand_id;
    private String brand_name;

    private Long color_id;
    private String color_name;

    public Vehicle(Long id, String engine_num, String chassis_num, Integer isDelete, Long customer_id, String customer_name, Long brand_id, String brand_name, Long color_id, String color_name) {
        this.id = id;
        this.engine_num = engine_num;
        this.chassis_num = chassis_num;
        this.isDelete = isDelete;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.color_id = color_id;
        this.color_name = color_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEngine_num() {
        return engine_num;
    }

    public void setEngine_num(String engine_num) {
        this.engine_num = engine_num;
    }

    public String getChassis_num() {
        return chassis_num;
    }

    public void setChassis_num(String chassis_num) {
        this.chassis_num = chassis_num;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public Long getColor_id() {
        return color_id;
    }

    public void setColor_id(Long color_id) {
        this.color_id = color_id;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }
}
