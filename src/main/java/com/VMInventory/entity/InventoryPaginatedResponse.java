package com.VMInventory.entity;

import java.util.List;

public class InventoryPaginatedResponse {
    long totalCount ;
    int currentCount;
    int pageSize;
    List<Inventory> collection;
    InventoryFindCriteria criteria;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Inventory> getCollection() {
        return collection;
    }

    public void setCollection(List<Inventory> collection) {
        this.collection = collection;
    }

    public InventoryFindCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(InventoryFindCriteria criteria) {
        this.criteria = criteria;
    }

    public InventoryPaginatedResponse(long totalCount, int currentCount, int pageSize, List<Inventory> collection, InventoryFindCriteria criteria) {
        this.totalCount = totalCount;
        this.currentCount = currentCount;
        this.pageSize = pageSize;
        this.collection = collection;
        this.criteria = criteria;
    }
}
