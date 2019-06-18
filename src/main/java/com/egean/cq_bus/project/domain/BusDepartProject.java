package com.egean.cq_bus.project.domain;

import com.egean.cq_bus.IC.domain.DebitGprsDetailtbbb;
import com.egean.cq_bus.domain.BaseEntityRmote;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author shiyinzhi
 */
@Entity
@Table(name = "bus_depart_project")
public class BusDepartProject extends BaseEntityRmote {

    /**
     *方案名称
     */
    @Column(name = "project_name", length = 128)
    private String projectName;

    /**
     *方案数据保存为字JSON 字符串
     */
    @Column(name = "project_value")
    private String projectValue;

    /**
     * 线路编号
     */
    @Column(name = "line_num")
    private String lineNum;

    /**
     * 方案日期
     */
    @Column(name = "project_date")
    private String projectDate;

    /**
     * 线路车辆数
     */
    @Column(name = "number_of_buses")
    private int numberOfBuses;

    /**
     * 最小发车
     */
    @Column(name = "min_dispatch_gap")
    private int minDispatchGap;

    /**
     * 最大发车
     */
    @Column(name = "max_dispatch_gap")
    private int maxDispatchGap;

    /**
     * 等待时间系数
     */
    @Column(name = "wating_time",length = 7,scale = 2)
    private BigDecimal watingTime;

    /**
     * 负荷系数
     */
    @Column(name = "load_factory",length = 7,scale = 2)
    private BigDecimal loadFactory;

    /**
     * 运营成本系数
     */
    @Column(name = "operation_cost",length = 7,scale = 2)
    private BigDecimal operationCost;

    @Column(name = "is_holly_day")
    private int isHollyDay = 0;

    @Transient
    private String searchTime;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectValue() {
        return projectValue;
    }

    public void setProjectValue(String projectValue) {
        this.projectValue = projectValue;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public int getNumberOfBuses() {
        return numberOfBuses;
    }

    public void setNumberOfBuses(int numberOfBuses) {
        this.numberOfBuses = numberOfBuses;
    }

    public int getMinDispatchGap() {
        return minDispatchGap;
    }

    public void setMinDispatchGap(int minDispatchGap) {
        this.minDispatchGap = minDispatchGap;
    }

    public int getMaxDispatchGap() {
        return maxDispatchGap;
    }

    public void setMaxDispatchGap(int maxDispatchGap) {
        this.maxDispatchGap = maxDispatchGap;
    }

    public BigDecimal getWatingTime() {
        return watingTime;
    }

    public void setWatingTime(BigDecimal watingTime) {
        this.watingTime = watingTime;
    }

    public BigDecimal getLoadFactory() {
        return loadFactory;
    }

    public void setLoadFactory(BigDecimal loadFactory) {
        this.loadFactory = loadFactory;
    }

    public BigDecimal getOperationCost() {
        return operationCost;
    }

    public void setOperationCost(BigDecimal operationCost) {
        this.operationCost = operationCost;
    }

    public int getIsHollyDay() {
        return isHollyDay;
    }

    public void setIsHollyDay(int isHollyDay) {
        this.isHollyDay = isHollyDay;
    }

    public String getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(String projectDate) {
        this.projectDate = projectDate;
    }
}
