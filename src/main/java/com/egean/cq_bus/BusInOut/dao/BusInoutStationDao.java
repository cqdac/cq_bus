package com.egean.cq_bus.BusInOut.dao;

import com.egean.cq_bus.BusInOut.domain.BusInoutStation;
import com.egean.cq_bus.IC.domain.DebitGprsDetailtbbb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BusInoutStationDao extends JpaRepository<BusInoutStation, Long> {



    @Query(value = "select count(carId) from BusInoutStation where carNum=:carNum and siteNum=:siteNum")
    int getSiteNumTotalByCarNumAndSiteNum(@Param("carNum") String carNum,@Param("siteNum")int siteNum);

    /**
     * 条件查询公交出入站信息
     * @param carNum 公交编码
     * @param siteNum 站点序列号
     * @param departBegin 开始统计的小时的开始字符串 比如统计7点的数据 则传入yyyy-MM-dd 07:00:00
     * @param departEnd   开始统计的小时的结束字符串 比如统计7点的数据 则传入yyyy-MM-dd 08:00:00
     * @param BeginRangeTime 时间范围开始时间 yyyy-MM-dd HH:mm:ss
     * @param EndRangeTime 时间范围结束时间 yyyy-MM-dd HH:mm:ss
     * @return
     */
    @Query(value = "select count(CarId) from Bus_Inout_Station_201812 where CarNum=?1 and SiteNum=?2 and DATEPART(HH,SysTime) >= DATEPART(HH,?3) and DATEPART(HH,SysTime) < DATEPART(HH,?4) and SysTime >=?5 and SysTime <=?5",nativeQuery = true)
    int getSiteNumTotalByCarNumAndSiteNumCondition(String carNum,int siteNum,String departBegin,String departEnd,String BeginRangeTime,String EndRangeTime);
}
