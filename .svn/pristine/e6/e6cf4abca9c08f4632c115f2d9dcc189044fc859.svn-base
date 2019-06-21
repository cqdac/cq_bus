package com.egean.cq_bus.BusInOut.domain;

import com.egean.cq_bus.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author shiyinzhi
 */
@Entity
@Table(name = "Bus_Inout_Station_201812")
@IdClass(BusInoutStation.class)
public class BusInoutStation extends BaseEntity implements Serializable {

    /**
     *
     */
    @Id
    @Column(name = "CarId", length = 50)
    private long CarId;

    /**
     *
     */
    @Column(name = "CarNum", length = 20)
    private String CarNum;

    /**
     *
     */
    @Column(name = "Vip",length = 20)
    private String Vip;

    /**
     *
     */
    @Column(name = "POSID", length = 50)
    private String POSID;

    /**
     *
     */
    @Column(name = "GpsTime")
    private Date GpsTime;

    /**
     *
     */
    @Column(name = "SysTime")
    private Date SysTime;

    /**
     *
     */
    @Column(name = "Located")
    private int Located;

    /**
     *
     */
    @Column(name = "AccFlag")
    private int AccFlag;

    /**
     *
     */
    @Column(name = "Ew")
    private int Ew;

    /**
     *
     */
    @Column(name = "Longitude", length = 10,scale = 6)
    private BigDecimal Longitude;

    /**
     *
     */
    @Column(name = "Latitude", length = 10,scale = 6)
    private BigDecimal Latitude;

    /**
     *
     */
    @Column(name = "Ns")
    private int Ns;

    @Column(name = "GpsSpeed")
    private int GpsSpeed;

    @Column(name = "RealSpeed")
    private int RealSpeed;

    @Column(name = "LimitedSpeed")
    private int LimitedSpeed;

    @Column(name = "Height")
    private int Height;

    @Column(name = "Azimuth")
    private int Azimuth;

    @Column(name = "Mileage",length = 18,scale = 3)
    private BigDecimal Mileage;

    @Column(name = "UpDown")
    private int UpDown;

    @Column(name = "RouteCode",length = 20)
    private String RouteCode;

    @Column(name = "StationId")
    private int StationId;

    @Column(name = "SiteNum")
    private int SiteNum;

    @Column(name = "InOut")
    private int InOut;

    @Column(name = "ResendFlag")
    private int ResendFlag;

    @Column(name = "AutoSend")
    private int AutoSend;

    @Column(name = "IsRunning")
    private int IsRunning;

    @Column(name = "RunState")
    private int RunState;

    @Column(name = "InPassengers")
    private int InPassengers;

    @Column(name = "OutPassengers")
    private int OutPassengers;

    @Column(name = "CardAmount",length = 10,scale = 2)
    private BigDecimal CardAmount;

    @Column(name = "RouteId")
    private int RouteId;


    public long getCarId() {
        return CarId;
    }

    public void setCarId(long carId) {
        CarId = carId;
    }

    public String getCarNum() {
        return CarNum;
    }

    public void setCarNum(String carNum) {
        CarNum = carNum;
    }

    public String getVip() {
        return Vip;
    }

    public void setVip(String vip) {
        Vip = vip;
    }

    public String getPOSID() {
        return POSID;
    }

    public void setPOSID(String POSID) {
        this.POSID = POSID;
    }

    public Date getGpsTime() {
        return GpsTime;
    }

    public void setGpsTime(Date gpsTime) {
        GpsTime = gpsTime;
    }

    public Date getSysTime() {
        return SysTime;
    }

    public void setSysTime(Date sysTime) {
        SysTime = sysTime;
    }

    public int getLocated() {
        return Located;
    }

    public void setLocated(int located) {
        Located = located;
    }

    public int getAccFlag() {
        return AccFlag;
    }

    public void setAccFlag(int accFlag) {
        AccFlag = accFlag;
    }

    public int getEw() {
        return Ew;
    }

    public void setEw(int ew) {
        Ew = ew;
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

    public int getNs() {
        return Ns;
    }

    public void setNs(int ns) {
        Ns = ns;
    }

    public int getGpsSpeed() {
        return GpsSpeed;
    }

    public void setGpsSpeed(int gpsSpeed) {
        GpsSpeed = gpsSpeed;
    }

    public int getRealSpeed() {
        return RealSpeed;
    }

    public void setRealSpeed(int realSpeed) {
        RealSpeed = realSpeed;
    }

    public int getLimitedSpeed() {
        return LimitedSpeed;
    }

    public void setLimitedSpeed(int limitedSpeed) {
        LimitedSpeed = limitedSpeed;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getAzimuth() {
        return Azimuth;
    }

    public void setAzimuth(int azimuth) {
        Azimuth = azimuth;
    }

    public BigDecimal getMileage() {
        return Mileage;
    }

    public void setMileage(BigDecimal mileage) {
        Mileage = mileage;
    }

    public int getUpDown() {
        return UpDown;
    }

    public void setUpDown(int upDown) {
        UpDown = upDown;
    }

    public String getRouteCode() {
        return RouteCode;
    }

    public void setRouteCode(String routeCode) {
        RouteCode = routeCode;
    }

    public int getStationId() {
        return StationId;
    }

    public void setStationId(int stationId) {
        StationId = stationId;
    }

    public int getSiteNum() {
        return SiteNum;
    }

    public void setSiteNum(int siteNum) {
        SiteNum = siteNum;
    }

    public int getInOut() {
        return InOut;
    }

    public void setInOut(int inOut) {
        InOut = inOut;
    }

    public int getResendFlag() {
        return ResendFlag;
    }

    public void setResendFlag(int resendFlag) {
        ResendFlag = resendFlag;
    }

    public int getAutoSend() {
        return AutoSend;
    }

    public void setAutoSend(int autoSend) {
        AutoSend = autoSend;
    }

    public int getIsRunning() {
        return IsRunning;
    }

    public void setIsRunning(int isRunning) {
        IsRunning = isRunning;
    }

    public int getRunState() {
        return RunState;
    }

    public void setRunState(int runState) {
        RunState = runState;
    }

    public int getInPassengers() {
        return InPassengers;
    }

    public void setInPassengers(int inPassengers) {
        InPassengers = inPassengers;
    }

    public int getOutPassengers() {
        return OutPassengers;
    }

    public void setOutPassengers(int outPassengers) {
        OutPassengers = outPassengers;
    }

    public BigDecimal getCardAmount() {
        return CardAmount;
    }

    public void setCardAmount(BigDecimal cardAmount) {
        CardAmount = cardAmount;
    }

    public int getRouteId() {
        return RouteId;
    }

    public void setRouteId(int routeId) {
        RouteId = routeId;
    }
}
