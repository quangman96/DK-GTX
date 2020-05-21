package com.ait.model;

public class Province {
    private Long id;
    private String name;
    private String province_code;
    private String telephone_code;
    private Integer quantity;
    private Integer isDelete;

    public Province(Long id, String name, String province_code, String telephone_code, Integer isDelete) {
        this.id = id;
        this.name = name;
        this.province_code = province_code;
        this.telephone_code = telephone_code;
        this.isDelete = isDelete;
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

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getTelephone_code() {
        return telephone_code;
    }

    public void setTelephone_code(String telephone_code) {
        this.telephone_code = telephone_code;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
