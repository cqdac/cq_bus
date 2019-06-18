package com.egean.cq_bus.Bus.dao;

import com.egean.cq_bus.Bus.domain.BusCarInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusCarInfoDao extends JpaRepository<BusCarInfo, Long> {
}
