package com.ait.model;

public class Color {
    private Long id;
    private String name;
    private Integer isDelete;

    public Color(Long id, String name, Integer isDelete) {
        this.id = id;
        this.name = name;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
