package com.egean.cq_bus.domain;

import javax.persistence.Transient;
import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
    @Transient
    private int page = 1;

    @Transient
    private int pageSize = 10;

    @Transient
    private String sort = "asc";

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
