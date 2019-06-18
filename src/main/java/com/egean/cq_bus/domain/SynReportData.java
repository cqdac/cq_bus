package com.egean.cq_bus.domain;

import java.util.List;

public class SynReportData {

    //时间段
    private List<String> xAxisData;
    //发车班次
    private List<Integer> seriesFcbc;
    //总运能
    private List<Integer> seriesZyn;
    //客流量
    private List<Integer> seriesCll;
    //匹配率
    private List<Integer> seriesPpl;

    public List<String> getxAxisData() {
        return xAxisData;
    }

    public void setxAxisData(List<String> xAxisData) {
        this.xAxisData = xAxisData;
    }

    public List<Integer> getSeriesFcbc() {
        return seriesFcbc;
    }

    public void setSeriesFcbc(List<Integer> seriesFcbc) {
        this.seriesFcbc = seriesFcbc;
    }

    public List<Integer> getSeriesZyn() {
        return seriesZyn;
    }

    public void setSeriesZyn(List<Integer> seriesZyn) {
        this.seriesZyn = seriesZyn;
    }

    public List<Integer> getSeriesCll() {
        return seriesCll;
    }

    public void setSeriesCll(List<Integer> seriesCll) {
        this.seriesCll = seriesCll;
    }

    public List<Integer> getSeriesPpl() {
        return seriesPpl;
    }

    public void setSeriesPpl(List<Integer> seriesPpl) {
        this.seriesPpl = seriesPpl;
    }
}
