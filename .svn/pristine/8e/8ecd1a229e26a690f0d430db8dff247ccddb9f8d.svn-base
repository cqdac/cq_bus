package com.egean.cq_bus.Bus.domain;

import com.egean.cq_bus.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bus_carinfo")
public class BusCarInfo extends BaseEntity {

    /**
     * 公交线路编码
     */
    @Id
    @Column(name = "Route", length = 20)
    private long Route;

    /**
     *
     */
    @Column(name = "CarId", length = 20)
    private long CarId;

    /**
     * 公交编码
     */
    @Column(name = "CarNumber", length = 255)
    private String carNumber;

    /**
     * 公交车牌
     */
    @Column(name = "CarName", length = 255)
    private String CarName;

    /**
     * 联网地址
     */
    @Column(name = "IpAddress", length = 255)
    private String IpAddress;

    public long getRoute() {
        return Route;
    }

    public void setRoute(long route) {
        Route = route;
    }

    public long getCarId() {
        return CarId;
    }

    public void setCarId(long carId) {
        CarId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarName() {
        return CarName;
    }

    public void setCarName(String carName) {
        CarName = carName;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }
}
