package com.VMInventory.entity;

public class InventoryFindCriteria {
    private Integer page = 1 ;
    private Integer size = 20 ;
    private String name ;
    private String code ;

    Boolean isNotExpired = false;

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Boolean isNotExpired() {
        return isNotExpired;
    }

    public void setPage(Integer page) {
        this.page = page!=null?page : this.page;
    }

    public void setSize(Integer size) {
        this.size = size!=null? size: this.size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNotExpired(Boolean notExpired) {
        isNotExpired = notExpired;
    }

    public InventoryFindCriteria(Integer page, Integer size, String name, String code , Boolean isNotExpired) {
        this.page = page!=null?page : this.page;
        this.size = size!=null?size: this.size;
        this.name = name;
        this.code = code;
        this.isNotExpired = isNotExpired;
    }

    public InventoryFindCriteria() {
        super();
    }

    @Override
    public String toString() {
        return "(" +
                "page=" + page +
                ", size=" + size +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", isNotExpired=" + isNotExpired +
                ')';
    }
}
