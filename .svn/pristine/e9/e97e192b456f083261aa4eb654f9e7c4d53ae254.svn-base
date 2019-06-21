package com.egean.cq_bus.Bus.dao;

import com.egean.cq_bus.Bus.domain.BusRoutesites;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusRoutesitesDao extends JpaRepository<BusRoutesites, Long> {

    /**
     * 通过线路编号查询最大做小的两个站点编码
     * @param Route
     * @return
     */
//    @Query(value = "select max(siteSerialumber) from BusRoutesites where route=:Route")
    @Query(value = "select max(SiteSerialumber),min(SiteSerialumber) from bus_routesites where route=?1",nativeQuery = true)
    List<Object[]> getMaxMinRoutSites(long Route);
}
