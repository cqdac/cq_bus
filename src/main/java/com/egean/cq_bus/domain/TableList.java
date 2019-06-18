package com.egean.cq_bus.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TableList {

    private int iTotalRecords;

    private int iTotalDisplayRecords;

    private String lineNum;

    private int code;

    private String error;

    private int perfectTotal;

//    @JsonProperty("data")
    private List<TableDomain> aaData;

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<TableDomain> getAaData() {
        return aaData;
    }

    public void setAaData(List<TableDomain> aaData) {
        this.aaData = aaData;
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getPerfectTotal() {
        return perfectTotal;
    }

    public void setPerfectTotal(int perfectTotal) {
        this.perfectTotal = perfectTotal;
    }
}
