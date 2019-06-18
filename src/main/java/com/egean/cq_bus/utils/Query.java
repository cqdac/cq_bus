package com.egean.cq_bus.utils;

import com.egean.cq_bus.domain.BaseEntity;
import javafx.scene.control.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Query<T extends BaseEntity> {

    private Pagination pagination;

    private String sex;

    private String marrageStatus;

    private String custName;

    public Pagination getPagination() {
        return pagination;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMarrageStatus() {
        return marrageStatus;
    }

    public void setMarrageStatus(String marrageStatus) {
        this.marrageStatus = marrageStatus;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    private Map queryParameters = new HashMap();

    public Map getQueryParameters() {
        return queryParameters;
    }

    public Query(HttpServletRequest request, T domain) {
        pagination = new Pagination();
        domain.setPage(Integer.parseInt(request.getParameter("pagination[page]")));
        domain.setPageSize(Integer.parseInt(request.getParameter("pagination[perpage]")));
        this.setSex(request.getParameter("query[sex]"));
        this.setMarrageStatus(request.getParameter("query[marrageStatus]"));
        this.setCustName(request.getParameter("query[generalSearch]"));
    }

    public Query(HttpServletRequest request, T domain, String regxStr) {
        pagination = new Pagination();
        domain.setPage(Integer.parseInt(request.getParameter("pagination[page]")));
        domain.setPageSize(Integer.parseInt(request.getParameter("pagination[perpage]")));
        this.setSex(request.getParameter("query[sex]"));
        this.setMarrageStatus(request.getParameter("query[marrageStatus]"));
        this.setCustName(request.getParameter(regxStr));
    }

    public Query(HttpServletRequest request, String[] regxStr) {
        for (String queryParameter : regxStr) {
            queryParameters.put(queryParameter,request.getParameter(queryParameter));
        }
    }
}
