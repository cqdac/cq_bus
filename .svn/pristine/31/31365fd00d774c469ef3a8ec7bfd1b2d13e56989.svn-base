package com.egean.cq_bus.BusInOut.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.*;


/**
 * @author shiyinzhi
 */
@Component
public class DataService {
    // 共用线程池，是为了从全局角度，叫多线程可控
    private Executor executor = newCachedThreadPool();

    @Autowired
    private BusInoutStationDao busInoutStationDao;

    /**
     * 多线程查公交出入站信息
     * @param carNum
     * @param SiteNums
     * @return
     * @throws Exception
     */
    public int getSiteNum(final String carNum,final int[] SiteNums) throws Exception {
        // 创建线程
        // 多线程任务管理
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
        for (int i = 0; i < SiteNums.length; i++) {
            final int index = i;
            // 根据总记录数count和线程数Server.threadCount进行分页任务分发
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Integer data = 0;
                    try {
                        data = busInoutStationDao.getSiteNumTotalByCarNumAndSiteNum(carNum,SiteNums[index]);
                    } catch (Throwable t) {
                        t.printStackTrace();
                    } finally {

                    }
                    return data;
                }
            });
        }

        int SiteNum = 0;
        for (int i = 0; i < SiteNums.length; i++) {
            // 取得结果，如果没有返回，则阻塞
            SiteNum+= completionService.take().get();
        }
        return SiteNum;
    }

    /**
     * 多线程查公交出入站信息
     * @param hoursList 储存需要查询的时间阶段 比如 7/8/9/10点的结果
     * @param carNum
     * @param SiteNums
     * @param BeginRangeTime
     * @param EndRangeTime
     * @return
     * @throws Exception
     */
    public int getSiteNum(final List<String> hoursList,final String carNum,final int[] SiteNums,String BeginRangeTime,String EndRangeTime) throws Exception {
        // 创建线程
        // 多线程任务管理
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
        for (String hour : hoursList) {
            final String[] hours_spl = hour.split("_");
            for (int i = 0; i < SiteNums.length; i++) {
                final int index = i;
                // 根据总记录数count和线程数Server.threadCount进行分页任务分发
                completionService.submit(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        Integer data = 0;
                        try {
                            data = busInoutStationDao.getSiteNumTotalByCarNumAndSiteNumCondition(carNum,SiteNums[index],hours_spl[0],hours_spl[1],BeginRangeTime,EndRangeTime);
                        } catch (Throwable t) {
                            t.printStackTrace();
                        } finally {

                        }
                        return data;
                    }
                });
            }
        }

        int SiteNum = 0;
        for (int i = 0; i < SiteNums.length; i++) {
            // 取得结果，如果没有返回，则阻塞
            SiteNum+= completionService.take().get();
        }
        return SiteNum;
    }
}
