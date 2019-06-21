package com.egean.cq_bus.Bus.controller;

import com.egean.cq_bus.Bus.dao.BusCarInfoDao;
import com.egean.cq_bus.Bus.domain.BusCarInfo;
import com.egean.cq_bus.IC.dao.DebitGprsDetailtbbbDao;
import com.egean.cq_bus.IC.domain.DebitGprsDetailtbbb;
import org.springframework.data.domain.*;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class BusCarInfoController {


    /**
     * 条件分页查询公交信息
     * @param queryEntity
     * @return
     */
    public static List<BusCarInfo> findBusCarInfoByPage(BusCarInfoDao busCarInfoDao, BusCarInfo queryEntity) throws Exception{
        try {

            List<BusCarInfo> busCarInfos = new ArrayList<>();

            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("Route", ExampleMatcher.GenericPropertyMatchers.contains())
                    .withIgnorePaths("CarId");
            Example<BusCarInfo> example = Example.of(queryEntity ,matcher);
            Sort sort = new Sort(Sort.Direction.DESC, "CarNumber");
            Pageable pageable = new PageRequest(queryEntity.getPage()-1, queryEntity.getPageSize(), sort);

            Page<BusCarInfo> pages = busCarInfoDao.findAll(example,pageable);
            busCarInfos.addAll(pages.getContent());
            return busCarInfos;
        }catch (Exception ex){
            throw ex;
        }
    }



}
