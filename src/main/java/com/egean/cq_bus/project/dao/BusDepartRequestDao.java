package com.egean.cq_bus.project.dao;

import com.egean.cq_bus.project.domain.BusDepartProject;
import com.egean.cq_bus.project.domain.BusDepartRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shiyinzhi
 */
@Transactional
public interface BusDepartRequestDao extends JpaRepository<BusDepartRequest, Long>, JpaSpecificationExecutor<BusDepartRequest> {

    /**
     * 根据请求参数查询最新的requestId
     * @param requestParamet
     * @return
     */
    @Query(value = "select * from bus_depart_request where request_paramet=?1 order by createTime desc limit 0,1  ",nativeQuery = true)
    BusDepartRequest getRequestByRequestParamet(String requestParamet);

}
