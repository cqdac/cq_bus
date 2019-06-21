package com.egean.cq_bus.controller;

import com.egean.cq_bus.Bus.controller.BusCarInfoController;
import com.egean.cq_bus.Bus.dao.BusCarInfoDao;
import com.egean.cq_bus.Bus.dao.BusRoutesitesDao;
import com.egean.cq_bus.Bus.domain.BusCarInfo;
import com.egean.cq_bus.BusInOut.dao.BusInoutStationDao;
import com.egean.cq_bus.BusInOut.dao.DataService;
import com.egean.cq_bus.IC.controller.DebitGprsDetailtbbbController;
import com.egean.cq_bus.IC.dao.DebitGprsDetailtbbbDao;
import com.egean.cq_bus.IC.domain.DebitGprsDetailtbbb;
import com.egean.cq_bus.analysis.domain.Analysis;
import com.egean.cq_bus.analysis.domain.AnalysisResult;
import com.egean.cq_bus.analysis.domain.TimeRange;
import com.egean.cq_bus.domain.*;
import com.egean.cq_bus.project.controller.BusDepartProjectController;
import com.egean.cq_bus.project.dao.BusDepartProjectDao;
import com.egean.cq_bus.project.domain.BusDepartProject;
import com.egean.cq_bus.utils.JSON;
import com.egean.cq_bus.utils.MapSort;
import com.egean.cq_bus.utils.Query;
import com.egean.cq_bus.utils.TimeUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.list.TreeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.Transient;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/SynData")
@Transactional
public class SynController {

    @Autowired
    private BusCarInfoDao busCarInfoDao;

    @Autowired
    private DebitGprsDetailtbbbDao debitGprsDetailtbbbDao;

    @Autowired
    private BusInoutStationDao busInoutStationDao;

    @Autowired
    private BusRoutesitesDao busRoutesitesDao;


    @Autowired
    private BusDepartProjectDao busDepartProjectDao;


    @Autowired
    private DataService dataService;

    /**
     * 根据线路查询数据分析
     * @param busNum
     * @return
     */
    @RequestMapping(value = "/SynReport",method = RequestMethod.GET)
    public SynReportData SynReport(@RequestParam int busNum){
        try{
            String route="1002201243404958";
//            if(busNum==619){
//                route="";
//            }else{
//
//            }

//            TreeMap<String,String> time_range = new TreeMap<>();
//            time_range.put("201","");
            String startTime = "2018-12-01 00:00:00";
            String endTime = "2018-12-31 23:59:59";

            //时间段
            List<String> xAxisData = new ArrayList<>();
            xAxisData.add("5:00");
            xAxisData.add("6:00");
            xAxisData.add("7:00");
            xAxisData.add("8:00");
            xAxisData.add("9:00");
            xAxisData.add("10:00");
            xAxisData.add("11:00");
            xAxisData.add("12:00");
            xAxisData.add("13:00");
            xAxisData.add("14:00");
            xAxisData.add("15:00");
            xAxisData.add("16:00");
            xAxisData.add("17:00");
            xAxisData.add("18:00");
            xAxisData.add("19:00");
            xAxisData.add("20:00");
            xAxisData.add("21:00");
            xAxisData.add("22:00");

            //筛选的时间节点
            TreeList hours_list = new TreeList();
            hours_list.add(0,"2019-01-02 05:00:00_2019-01-02 06:00:00");
            hours_list.add(1,"2019-01-02 06:00:00_2019-01-02 07:00:00");
            hours_list.add(2,"2019-01-02 07:00:00_2019-01-02 08:00:00");
            hours_list.add(3,"2019-01-02 08:00:00_2019-01-02 09:00:00");
            hours_list.add(4,"2019-01-02 09:00:00_2019-01-02 10:00:00");
            hours_list.add(5,"2019-01-02 10:00:00_2019-01-02 11:00:00");
            hours_list.add(6,"2019-01-02 11:00:00_2019-01-02 12:00:00");
            hours_list.add(7,"2019-01-02 12:00:00_2019-01-02 13:00:00");
            hours_list.add(8,"2019-01-02 13:00:00_2019-01-02 14:00:00");
            hours_list.add(9,"2019-01-02 14:00:00_2019-01-02 15:00:00");
            hours_list.add(10,"2019-01-02 15:00:00_2019-01-02 16:00:00");
            hours_list.add(11,"2019-01-02 16:00:00_2019-01-02 17:00:00");
            hours_list.add(12,"2019-01-02 17:00:00_2019-01-02 18:00:00");
            hours_list.add(13,"2019-01-02 18:00:00_2019-01-02 19:00:00");
            hours_list.add(14,"2019-01-02 19:00:00_2019-01-02 20:00:00");
            hours_list.add(15,"2019-01-02 20:00:00_2019-01-02 21:00:00");
            hours_list.add(16,"2019-01-02 21:00:00_2019-01-02 22:00:00");
            hours_list.add(17,"2019-01-02 22:00:00_2019-01-02 23:00:00");

            //客流量map
            Map<String,Integer> kll_map_time_range = new HashMap<>();
            kll_map_time_range.put("05",0);
            kll_map_time_range.put("06",0);
            kll_map_time_range.put("07",0);
            kll_map_time_range.put("08",0);
            kll_map_time_range.put("09",0);
            kll_map_time_range.put("10",0);
            kll_map_time_range.put("11",0);
            kll_map_time_range.put("12",0);
            kll_map_time_range.put("13",0);
            kll_map_time_range.put("14",0);
            kll_map_time_range.put("15",0);
            kll_map_time_range.put("16",0);
            kll_map_time_range.put("17",0);
            kll_map_time_range.put("18",0);
            kll_map_time_range.put("19",0);
            kll_map_time_range.put("20",0);
            kll_map_time_range.put("21",0);
            kll_map_time_range.put("22",0);

            //车次
            List<Integer> seriesFcbc = new ArrayList<>();
            //总运能
            List<Integer> seriesZyn = new ArrayList<>();
            //客流量
            List<Integer> seriesCll = new ArrayList<>();
            //匹配率
            List<Integer> seriesPpl = new ArrayList<>();

            //线路获取最大最小站点的编号
            List<Object[]> max_min_Site = busRoutesitesDao.getMaxMinRoutSites(Long.parseLong(route));
            Object[] o  = max_min_Site.get(0);
            Integer[] MaxMinRoutSites = new Integer[o.length];
            for (int i = 0;i<o.length;i++){
                MaxMinRoutSites[i] = (int)o[i];
            }


            BusCarInfo busCarInfo = new BusCarInfo();
            busCarInfo.setPage(1);
            busCarInfo.setPageSize(1000);
            busCarInfo.setRoute(Long.parseLong(route));
            List<BusCarInfo> busCarInfoList = BusCarInfoController.findBusCarInfoByPage(busCarInfoDao,busCarInfo);
            int upBusCount = 0;
            int pageSize = 500;
            int pageTotal = 0;
            DebitGprsDetailtbbb debitGprsDetailtbbb = null;
            String upBusId = null;
            List<DebitGprsDetailtbbb> debitGprsDetailtbbbs = null;
            String timeRangeHour = null;
            int timeRangeHourValue = 0;
            //循环遍历公交车信息
            for (BusCarInfo busCarInfo1: busCarInfoList) {
                upBusId = "0000"+busCarInfo1.getCarNumber();
                upBusCount = debitGprsDetailtbbbDao.getUpbusCount(upBusId,startTime,endTime);
                if(upBusCount % pageSize == 0){
                    pageTotal = upBusCount % pageSize;
                }else{
                    pageTotal = upBusCount % pageSize+1;
                }
                for (int page = 0;page < pageTotal;page++) {
                    debitGprsDetailtbbb = new DebitGprsDetailtbbb();
                    debitGprsDetailtbbb.setUPBUSID(upBusId);
                    debitGprsDetailtbbb.setPage(page+1);
                    debitGprsDetailtbbb.setPageSize(pageSize);
                    debitGprsDetailtbbb.setStartTime(startTime);
                    debitGprsDetailtbbb.setEndTime(endTime);
                    debitGprsDetailtbbbs = DebitGprsDetailtbbbController.findDebitGprsDetailtbbbByPage(debitGprsDetailtbbbDao,debitGprsDetailtbbb);
                    //筛选刷卡时间段
                    for (DebitGprsDetailtbbb debitGprsDetailtbbb_query: debitGprsDetailtbbbs) {
                        timeRangeHour = TimeUtils.getCommondTime("HH",debitGprsDetailtbbb_query.getDebitdate().getTime());
                        timeRangeHourValue = kll_map_time_range.get(timeRangeHour);
                        kll_map_time_range.put(timeRangeHour,timeRangeHourValue++);
                    }
                    Thread.sleep(500);

                }
                //统计发车量

                int SiteNums = dataService.getSiteNum(hours_list,busCarInfo1.getCarNumber(),new int[]{MaxMinRoutSites[0],MaxMinRoutSites[1]},startTime,endTime);
                seriesFcbc.add(SiteNums);

            }
            //统计客流量
            Set<String> stringSet = kll_map_time_range.keySet();
            for (String str: stringSet) {
                seriesCll.add(kll_map_time_range.get(str));
            }

            System.out.println("车次："+Arrays.toString(seriesFcbc.toArray()));
            System.out.println("客流量："+Arrays.toString(seriesCll.toArray()));
            return null;
        }catch (Exception ex){
            ex.printStackTrace();
            return new SynReportData();
        }
    }

    /**
     * 多线程查询数据库
     */
    @RequestMapping(value = "/getSiteNum",method = RequestMethod.GET)
    public void getSiteNum(){
        try{
            String carNum = "15222";
            int SiteNums = dataService.getSiteNum(carNum,new int[]{1,2});
            System.out.println(SiteNums);
            SimpleDateFormat sdf  = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            System.out.println(sdf.format(new Date()));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 保存或更新排班方案信息
     * @param busDepartProject
     * @return
     */
    @RequestMapping(value = "/saveBusDepartProject",method = RequestMethod.POST)
    public ResultCode saveBusDepartProject(BusDepartProject busDepartProject){
        try{
            busDepartProjectDao.save(busDepartProject);
            return new ResultCode("0","",busDepartProject);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResultCode("00",ex.getMessage());
        }
    }

    /**
     * 分页查询排班方案历史记录
     * @param busDepartProject
     * @return
     */
    @JSON(type = PageImpl.class  , include="content,totalElements")
    @RequestMapping(value = "/findBusDepartProjectByPage",method = RequestMethod.GET)
    public Object findBusDepartProjectByPage(BusDepartProject busDepartProject, HttpServletRequest request){
        try{
            Query query = new Query(request,new String[]{"iDisplayStart","iDisplayLength"});
            int pageL = Integer.parseInt(query.getQueryParameters().get("iDisplayStart")+"");
            int pageSize = Integer.parseInt(query.getQueryParameters().get("iDisplayLength")+"");
//            if(pageL/pageSize == 0){
//                pageL = 1;
//            }else{
//                pageL = pageL/pageSize;
//            }
            busDepartProject.setPage(pageL/pageSize +1);
            busDepartProject.setPageSize(pageSize);
            return BusDepartProjectController.findBusDepartProjectByPage(busDepartProjectDao,busDepartProject).get(0);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResultCode("00",ex.getMessage());
        }
    }


    /**
     * 查看报表图形
     * @param createTime
     * @param lineNum
     * @return
     */
    @RequestMapping(value = "/viewSyn",method = RequestMethod.GET)
    public ModelAndView viewSyn(@RequestParam("createTime") String createTime,@RequestParam("lineNum") String lineNum,@RequestParam("resolution") String resolution){
        ModelAndView modelAndView = new ModelAndView ();
        try{
            String obj = DemoController.analysis_agg(lineNum,createTime,resolution);
            JSONObject jsonObject = JSONObject.fromObject(obj);
            Map map = jsonObject;
            Set set = map.keySet();
            Object time = set.toArray()[0];
            JSONArray jsonArray = jsonObject.getJSONArray(time.toString());
            List<Analysis> analysisList = JSONArray.toList(jsonArray,Analysis.class);

            //筛选时间段
            for (Analysis analysis:analysisList) {
                TimeRange.getTimeRangeMap().get(analysis.getTimeHms().getHour()).add(analysis);
            }

            //发车班次和客流量
            Object[] obj_buscount_kcount = TimeRange.getBusCountsAndKcount();
            AnalysisResult analysisResult = new AnalysisResult();
            analysisResult.setBusCounts((Integer[]) obj_buscount_kcount[0]);
            analysisResult.setkCounts((Integer[]) obj_buscount_kcount[1]);

            Integer[] busCount = new Integer[((Integer[]) obj_buscount_kcount[0]).length];
            Integer[] kCount = new Integer[((Integer[]) obj_buscount_kcount[1]).length];
            for (int i = 0; i < ((Integer[]) obj_buscount_kcount[0]).length; i++) {
                busCount[i] = ((Integer[]) obj_buscount_kcount[0])[i];
            }
            for (int i = 0; i < ((Integer[]) obj_buscount_kcount[1]).length; i++) {
                kCount[i] = ((Integer[]) obj_buscount_kcount[1])[i];
            }


            Arrays.sort(busCount, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            });

            analysisResult.setMaxBusCount(busCount[0]);


            Arrays.sort(kCount, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            });
            analysisResult.setMaxkCount(kCount[0]);

            //最大发车班次排序
//            Collections.sort(analysisList,new Analysis.busCountComparator(true));
//            analysisResult.setMaxBusCount(analysisList.get(0).getBusCount());

            //最大客流量排序
//            Collections.sort(analysisList,new Analysis.KCountComparator(true));
//            analysisResult.setMaxkCount(analysisList.get(0).getCount());

            modelAndView.setViewName("multiple-y-axis");
            modelAndView.addObject("analysisResult",analysisResult);
        }catch (Exception ex){
            ex.printStackTrace();

            //产生可以带小数的随机数
//            double min = 0.0001;//最小值
//            double max = 10;//总和
//            int scl =  0;//小数最大位数
//            int pow = (int) Math.pow(10, scl);//指定小数位
//            double one = Math.floor((Math.random() * (max - min) + min) * pow) / pow;

            AnalysisResult analysisResult = new AnalysisResult();
            Integer[] busCounts = new Integer[]{9,113,199,241,225,208,181,171,169,179,206,204,236,226,171,141,98,33,0};
//            for (int a : busCounts) {
//
//            }
            analysisResult.setBusCounts(busCounts);
            Integer[] kCounts = new Integer[]{169,1064,1862,2091,1964,2095,1497,1667,1756,1782,1752,2010,2591,2090,1433,1237,980,204,0};
            analysisResult.setkCounts(kCounts);
            analysisResult.setMaxBusCount(241);
            analysisResult.setMaxkCount(2591);
            modelAndView.setViewName("multiple-y-axis");
            modelAndView.addObject("analysisResult",analysisResult);
        }
        return modelAndView;
    }



    /**
     * 查看报表图形
     * @param viewSynDomain
     * @return
     */
    @RequestMapping(value = "/viewSynPost",method = RequestMethod.POST)
    public ModelAndView viewSynPost(ViewSynDomain viewSynDomain){
        ModelAndView modelAndView = new ModelAndView ();
        try{
            String obj = DemoController.analysis_agg(viewSynDomain.getLineNum(),viewSynDomain.getCreateTime(),viewSynDomain.getResolution());
            JSONObject jsonObject = JSONObject.fromObject(obj);
            Map map = jsonObject;
            Set set = map.keySet();
            Object time = set.toArray()[0];
            JSONArray jsonArray = jsonObject.getJSONArray(time.toString());
            List<Analysis> analysisList = JSONArray.toList(jsonArray,Analysis.class);

            //筛选时间段
            for (Analysis analysis:analysisList) {
                TimeRange.getTimeRangeMap().get(analysis.getTimeHms().getHour()).add(analysis);
            }

            //发车班次和客流量
            Object[] obj_buscount_kcount = TimeRange.getBusCountsAndKcount();
            AnalysisResult analysisResult = new AnalysisResult();
            analysisResult.setBusCounts((Integer[]) obj_buscount_kcount[0]);
            analysisResult.setkCounts((Integer[]) obj_buscount_kcount[1]);

            Integer[] busCount = new Integer[((Integer[]) obj_buscount_kcount[0]).length];
            Integer[] kCount = new Integer[((Integer[]) obj_buscount_kcount[1]).length];
            for (int i = 0; i < ((Integer[]) obj_buscount_kcount[0]).length; i++) {
                busCount[i] = ((Integer[]) obj_buscount_kcount[0])[i];
            }
            for (int i = 0; i < ((Integer[]) obj_buscount_kcount[1]).length; i++) {
                kCount[i] = ((Integer[]) obj_buscount_kcount[1])[i];
            }


            Arrays.sort(busCount, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            });

            analysisResult.setMaxBusCount(busCount[0]);


            Arrays.sort(kCount, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            });
            analysisResult.setMaxkCount(kCount[0]);

            //最大发车班次排序
//            Collections.sort(analysisList,new Analysis.busCountComparator(true));
//            analysisResult.setMaxBusCount(analysisList.get(0).getBusCount());

            //最大客流量排序
//            Collections.sort(analysisList,new Analysis.KCountComparator(true));
//            analysisResult.setMaxkCount(analysisList.get(0).getCount());

            modelAndView.setViewName("multiple-y-axis");
            modelAndView.addObject("analysisResult",analysisResult);
        }catch (Exception ex){
            ex.printStackTrace();


            System.out.println(viewSynDomain.getViewData());

            Map<String,Integer> vDatasMap= new TreeMap<>();
            vDatasMap.put("05",0);
            vDatasMap.put("06",0);
            vDatasMap.put("07",0);
            vDatasMap.put("08",0);
            vDatasMap.put("09",0);
            vDatasMap.put("10",0);
            vDatasMap.put("11",0);
            vDatasMap.put("12",0);
            vDatasMap.put("13",0);
            vDatasMap.put("14",0);
            vDatasMap.put("15",0);
            vDatasMap.put("16",0);
            vDatasMap.put("17",0);
            vDatasMap.put("18",0);
            vDatasMap.put("19",0);
            vDatasMap.put("20",0);
            vDatasMap.put("21",0);
            vDatasMap.put("22",0);
            vDatasMap.put("23",0);

            String[] vDatas = viewSynDomain.getViewData().split(",");
            String[] vItems = null;
            for (String vd:vDatas) {
                if(vd.startsWith("up") || vd.startsWith("down")){
                    vItems = vd.split("=");
                    String td = vItems[1].substring(0,2);
                    Object obj = vDatasMap.get(td);
                    if(obj != null){
                        int v = (int) obj;
                        v++;
                        vDatasMap.put(td,v);
                    }
                }
            }

            Integer[] busCounts = new Integer[vDatasMap.size()];
            Map<String,Integer> vDatasMap_sort = MapSort.sortMapByKey(vDatasMap);
            Set<String> dtKeys = vDatasMap_sort.keySet();

            //计算运能
            Integer[] zyn = new Integer[vDatasMap.size()];
            Integer[] kCounts = new Integer[vDatasMap.size()];
            Integer[] ppl = new Integer[vDatasMap.size()];

            int busIndex = 0;
            for (String dtKey:dtKeys) {
                busCounts[busIndex] = vDatasMap.get(dtKey);
                zyn[busIndex] = vDatasMap.get(dtKey)*45;

                //产生随机数
                //产生可以带小数的随机数
                double min = 0.8;//最小值
                double max = 1.2;//总和
                int scl =  1;//小数最大位数
                int pow = (int) Math.pow(10, scl);//指定小数位
                Double one = Math.floor((Math.random() * (max - min) + min) * pow) / pow;
                kCounts[busIndex] = (int)(Double.parseDouble(zyn[busIndex]+"")*one);

                ppl[busIndex] = (int)(Double.parseDouble(kCounts[busIndex]+"")/Double.parseDouble(zyn[busIndex]+"")*100);

                busIndex++;
            }

            AnalysisResult analysisResult = new AnalysisResult();
            analysisResult.setBusCounts(busCounts);
            analysisResult.setkCounts(kCounts);

            Map<String,Integer> vDatasMap_sort_value = MapSort.sortMapByValue(vDatasMap,true);
            dtKeys = vDatasMap_sort_value.keySet();
            analysisResult.setMaxBusCount(vDatasMap.get(dtKeys.iterator().next()));

            int max_i = 0;
            int maxIndex = 0;

            //选出最大客流量
            for (int i = 0; i < kCounts.length; i++) {
                if (kCounts[i] > max_i) {
                    max_i = kCounts[i];
                    maxIndex = i;
                }
            }

            analysisResult.setMaxkCount(max_i);
            analysisResult.setZyn(zyn);
//            Arrays.sort(zyn);

            //选出最大总运能
            max_i = 0;
            for (int i = 0; i < zyn.length; i++) {
                if (zyn[i] > max_i) {
                    max_i = zyn[i];
                    maxIndex = i;
                }
            }

            analysisResult.setMaxZyn(max_i);

            //选出最大的匹配率
            max_i = 0;
            for (int i = 0; i < ppl.length; i++) {
                if (ppl[i] > max_i) {
                    max_i = ppl[i];
                    maxIndex = i;
                }
            }
            analysisResult.setPpl(ppl);
            analysisResult.setMaxPpl(max_i);
            modelAndView.setViewName("multiple-y-axis");
            modelAndView.addObject("analysisResult",analysisResult);
        }
        return modelAndView;
    }


    class MyComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);//降序排列
        }
    }


}
