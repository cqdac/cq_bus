package com.egean.cq_bus.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class ResultCode<T extends BaseEntityRmote> {
    private String rcode;
    private String desc;
    private Page<T> page;

    public ResultCode(String rcode, String desc) {
        this.rcode = rcode;
        this.desc = desc;
    }

    public ResultCode(String rcode, String desc, Page<T> page) {
        this.rcode = rcode;
        this.desc = desc;
        this.page = page;
    }

    public ResultCode(String rcode, String desc, T custBaseInfo) {
        this.rcode = rcode;
        this.desc = desc;
        List<T> custBaseInfos = new ArrayList<>();
        if(custBaseInfo != null){
            custBaseInfos.add(custBaseInfo);
        }
        this.page = new PageImpl(custBaseInfos);
    }

    public ResultCode(String rcode, String desc, List<T> custBaseInfo) {
        this.rcode = rcode;
        this.desc = desc;
        this.page = new PageImpl(custBaseInfo);
    }

    public String getRcode() {
        return rcode;
    }

    public void setRcode(String rcode) {
        this.rcode = rcode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }
}
