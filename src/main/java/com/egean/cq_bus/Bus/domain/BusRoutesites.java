package com.egean.cq_bus.Bus.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author shiyinzhi
 */
@Entity
@Table(name = "bus_routesites")
public class BusRoutesites implements Serializable {

    /**
     * 公交线路编码
     */
    @Column(name = "Route")
    private long Route;

    /**
     * 上行、下行
     */
    @Column(name = "UpDown")
    private int UpDown;

    /**
     * 站点序号
     */
    @Id
    @Column(name = "SiteSerialumber")
    private int SiteSerialumber;

    /**
     * 站点属性
     */
    @Column(name = "SiteAttribute")
    private int SiteAttribute;

    /**
     *
     */
    @Column(name = "extIntervals", length = 24,scale = 9)
    private BigDecimal extIntervals;

    /**
     * 站点名称
     */
    @Column(name = "Stationame", length = 255)
    private String Stationame;

    /**
     *经度
     */
    @Column(name = "Longitude", length = 24,scale = 9)
    private BigDecimal Longitude;

    /**
     *维度
     */
    @Column(name = "Latitude", length = 24,scale = 9)
    private BigDecimal Latitude;

    @Transient
    private int max;

    @Transient
    private int min;

    public long getRoute() {
        return Route;
    }

    public void setRoute(long route) {
        Route = route;
    }

    public int getUpDown() {
        return UpDown;
    }

    public void setUpDown(int upDown) {
        UpDown = upDown;
    }

    public int getSiteSerialumber() {
        return SiteSerialumber;
    }

    public void setSiteSerialumber(int siteSerialumber) {
        SiteSerialumber = siteSerialumber;
    }

    public int getSiteAttribute() {
        return SiteAttribute;
    }

    public void setSiteAttribute(int siteAttribute) {
        SiteAttribute = siteAttribute;
    }

    public BigDecimal getExtIntervals() {
        return extIntervals;
    }

    public void setExtIntervals(BigDecimal extIntervals) {
        this.extIntervals = extIntervals;
    }

    public String getStationame() {
        return Stationame;
    }

    public void setStationame(String stationame) {
        Stationame = stationame;
    }

    public BigDecimal getLongitude() {
        return Longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        Longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return Latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        Latitude = latitude;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
