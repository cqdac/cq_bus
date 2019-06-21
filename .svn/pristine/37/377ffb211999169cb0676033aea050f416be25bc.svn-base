package com.egean.cq_bus.IC.dao;

import com.egean.cq_bus.IC.domain.DebitGprsDetailtbbb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shiyinzhi
 */
@Transactional
public interface DebitGprsDetailtbbbDao extends JpaRepository<DebitGprsDetailtbbb, Long>, JpaSpecificationExecutor<DebitGprsDetailtbbb> {

    /**
     * 根据公交编码获取刷卡总数
     * @param UPBUSID
     * @return
     */
//    @Query(value = "select count(sn) from DebitGprsDetailtbbb where UPBUSID=:UPBUSID")
    @Query(value = "select count(cardid) from debitgprsdetailtbbb where UPBUSID=?1 and debitdate>=?2 and debitdate <= ?3",nativeQuery = true)
    int getUpbusCount(String UPBUSID,String startTime,String endTime);

    @Query(value = "select count(cardid) from debitgprsdetailtbbb where UPBUSID=?1 and debitdate>=?2 and debitdate <= ?3",nativeQuery = true)
    int getSkCount(String UPBUSID,String startTime,String endTime);
}
