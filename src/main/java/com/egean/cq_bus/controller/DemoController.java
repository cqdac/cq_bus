package com.egean.cq_bus.controller;

import com.egean.cq_bus.domain.*;
import com.egean.cq_bus.project.dao.BusDepartRequestDao;
import com.egean.cq_bus.project.domain.BusDepartRequest;
import com.egean.cq_bus.utils.DateUtil;
import com.egean.cq_bus.utils.MapSort;
import com.egean.cq_bus.utils.TimeUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/")
public class DemoController {

    private static final int TIMEOUT_IN_MILLIONS = 300000;

    @RequestMapping("/view")
    public ModelAndView login2() {
        return new ModelAndView ("components_noui_sliders");
    }

    @RequestMapping("/viewSyn")
    public ModelAndView viewSyn() {
        return new ModelAndView ("multiple-y-axis");
    }

    private static final String LineAll = "877297621653287";
    private static final String Line_619 = "2i23tehsbsygt2";
    private static final String Line_877 = "kdieijduwy87687";
    private static final int Line_877_count = 35;
    private static final int Line_619_count = 45;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat sdf_ymd = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private BusDepartRequestDao busDepartRequestDao;

    @Autowired
    private HttpServletRequest request;


    /**
     * 根据公交线路查询优化结果
     * @param routeId
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/schedules/{routeId}",method = RequestMethod.POST,consumes = "application/json")
    public Result OptimizeScheduleByRouteIdAsync(@PathVariable String routeId,@RequestBody List<ObjectivesDto> jsonParam){
        try{
            System.out.println(jsonParam);
            String requestId = "";
            Result result = new Result(LineAll,"");;
            if(routeId.equals("619")){
                result = new Result(Line_619,"");
            }else if(routeId.equals("877")){
                result = new Result(Line_877,"");
            }
            return result;
        }catch (Exception ex){
            ex.printStackTrace();
            return new Result("",ex.getMessage());
        }
    }


    /**
     * 根据公交线路查询优化结果
     * @param routeId
     * @param requestBusLine
     * @return
     */
    @RequestMapping(value = "/schedulesremote/{routeId}",method = RequestMethod.POST,consumes = "application/json")
    public Result OptimizeScheduleByRouteIdAsyncRemote(@PathVariable String routeId, @RequestBody RequestBusLine requestBusLine, HttpServletRequest request){
        try{
            System.out.println(requestBusLine);
            requestBusLine.setLineNum(routeId);
//            requestBusLine.setStartTime("2019-01-18T06:20:00");
//            requestBusLine.setEndTime("2019-01-18T22:00:00");
//            requestBusLine.setStartTime("2018-12-28");
//            requestBusLine.setEndTime("2018-12-28");
//            this.requestBusLine = requestBusLine;
            String requestId = null;
            if(requestBusLine != null && requestBusLine.getObjectivesDtos() != null){
//                List<ObjectivesDto> objectivesDtos = requestBusLine.getObjectivesDtos();
//                if(requestBusLine.getLineNum().equals("619") &&
//                        objectivesDtos.get(0).getValue().equals("0.32") &&
//                        objectivesDtos.get(1).getValue().equals("0.34") &&
//                        objectivesDtos.get(2).getValue().equals("0.34")){
//                    requestId = "0";
//                } else if(requestBusLine.getLineNum().equals("619") &&
//                        objectivesDtos.get(0).getValue().equals("0.10") &&
//                        objectivesDtos.get(1).getValue().equals("0.10") &&
//                        objectivesDtos.get(2).getValue().equals("0.80")){
//                    requestId = "0";
//                } else if(requestBusLine.getLineNum().equals("619") &&
//                        objectivesDtos.get(0).getValue().equals("0.70") &&
//                        objectivesDtos.get(1).getValue().equals("0.20") &&
//                        objectivesDtos.get(2).getValue().equals("0.10")){
//                    requestId = "0";
//                } else if(requestBusLine.getLineNum().equals("619") &&
//                        objectivesDtos.get(0).getValue().equals("0.25") &&
//                        objectivesDtos.get(1).getValue().equals("0.50") &&
//                        objectivesDtos.get(2).getValue().equals("0.25")){
//                    requestId = "0";
//                } else if(requestBusLine.getLineNum().equals("877") &&
//                        objectivesDtos.get(0).getValue().equals("0.32") &&
//                        objectivesDtos.get(1).getValue().equals("0.34") &&
//                        objectivesDtos.get(2).getValue().equals("0.34")){
//                    requestId = "0";
//                } else if(requestBusLine.getLineNum().equals("877") &&
//                        objectivesDtos.get(0).getValue().equals("0.10") &&
//                        objectivesDtos.get(1).getValue().equals("0.10") &&
//                        objectivesDtos.get(2).getValue().equals("0.80")){
//                    requestId = "0";
//                } else if(requestBusLine.getLineNum().equals("877") &&
//                        objectivesDtos.get(0).getValue().equals("0.70") &&
//                        objectivesDtos.get(1).getValue().equals("0.20") &&
//                        objectivesDtos.get(2).getValue().equals("0.10")){
//                    requestId = "0";
//                } else if(requestBusLine.getLineNum().equals("877") &&
//                        objectivesDtos.get(0).getValue().equals("0.25") &&
//                        objectivesDtos.get(1).getValue().equals("0.50") &&
//                        objectivesDtos.get(2).getValue().equals("0.25")){
//                    requestId = "0";
//                }else{
                    requestId = schedules(requestBusLine);
//                }
            }else{
                requestId = schedules(requestBusLine);
            }

            Result result = null;
            //{"code":202,"message":"Request accepted and is in process!  Please check back later","data":196}
            if(requestId.equals("0")){
                requestId = "{\"code\":202,\"message\":\"Request accepted and is in process!  Please check back later\",\"data\":0}";
            }
            System.out.println("requestId:"+requestId);
            JSONObject jsonObject = JSONObject.fromObject(requestId);
            if(!jsonObject.get("code").equals(200) && !jsonObject.get("code").equals(202)){
                result = new Result(null,jsonObject.getString("message"));
            }else{
                result = new Result(jsonObject.getString("data"),"");
            }
            request.getSession().setAttribute("requestBusLine",requestBusLine);
            return result;
        }catch (Exception ex){
            ex.printStackTrace();
            return new Result("",ex.getMessage());
        }
    }


    /**
     *查询优化结果
     * @param requestBusLine
     * @return
     */
    @RequestMapping(value = "/schedules",method = RequestMethod.POST,consumes = "application/json")
    public Result optimizeSchedulesAsync(@RequestBody RequestBusLine requestBusLine){
        try{
            System.out.println(requestBusLine);
            requestBusLine.setStartTime("2018-12-28T06:00:00");
            requestBusLine.setEndTime("2018-12-28T21:00:00");
            String requestId = schedules(requestBusLine);
            Result result = new Result(requestId,"");
            return result;
        }catch (Exception ex){
            ex.printStackTrace();
            return new Result("",ex.getMessage());
        }
    }


    /**
     *根据优化结果返回具体优化事项
     * @param requestId
     */
    @RequestMapping(value = "/schedule-requests/{requestId}",method = RequestMethod.GET)
    public List<TableList> scheduleRequests(@PathVariable String requestId,@RequestParam int busNum){
        try{

            String lineRange_619 = "";
            String lineRange_877 = "";
            String lineRange = "";

            List<TableList> tableListList = new ArrayList<>();
            if(busNum > 0){
                TableList tableList = new TableList();
                List<TableDomain> tableDomainList = new ArrayList<>();
                if(requestId.equals(Line_619)){
                    lineRange = "在水一方--轨道嘉州恒大中渝广场";
                    for (int i = 0;i<busNum;i++){
                        TableDomain tableDomain = new TableDomain();
                        tableDomain.setCode((i+1)+"");
                        tableDomain.setLineRange(lineRange);
                        tableDomain.setStartStation("在水一方");
                        tableDomain.setUp1("2018-12-03T05:30:00");
                        tableDomain.setDown1("2018-12-03T05:30:00");
                        tableDomain.setUp2("2018-12-03T05:30:00");
                        tableDomain.setDown2("2018-12-03T05:30:00");
                        tableDomain.setUp3("2018-12-03T05:30:00");
                        tableDomain.setDown3("2018-12-03T05:30:00");
                        tableDomain.setUp4("2018-12-03T05:30:00");
                        tableDomain.setDown4("2018-12-03T05:30:00");
                        tableDomain.setUp5("2018-12-03T05:30:00");
                        tableDomain.setDown5("2018-12-03T05:30:00");
                        tableDomain.setUp6("2018-12-03T05:30:00");
                        tableDomain.setDown6("2018-12-03T05:30:00");
                        tableDomain.setUp7("2018-12-03T05:30:00");
                        tableDomain.setDown7("2018-12-03T05:30:00");
                        tableDomainList.add(tableDomain);
                    }
                    tableList.setAaData(tableDomainList);
                    tableList.setiTotalDisplayRecords(busNum);
                    tableList.setiTotalRecords(busNum);
                    tableList.setLineNum("619");
                    tableListList.add(tableList);
                }else if(requestId.equals(Line_877)){
                    lineRange = "红旗河沟—鸳鸯轨道站";
                    for (int i = 0;i<busNum;i++){
                        TableDomain tableDomain = new TableDomain();
                        tableDomain.setCode((i+1)+"");
                        tableDomain.setLineRange(lineRange);
                        tableDomain.setStartStation("红旗河沟");
                        tableDomain.setUp1("2018-12-03T05:30:00");
                        tableDomain.setDown1("2018-12-03T05:30:00");
                        tableDomain.setUp2("2018-12-03T05:30:00");
                        tableDomain.setDown2("2018-12-03T05:30:00");
                        tableDomain.setUp3("2018-12-03T05:30:00");
                        tableDomain.setDown3("2018-12-03T05:30:00");
                        tableDomain.setUp4("2018-12-03T05:30:00");
                        tableDomain.setDown4("2018-12-03T05:30:00");
                        tableDomain.setUp5("2018-12-03T05:30:00");
                        tableDomain.setDown5("2018-12-03T05:30:00");
                        tableDomain.setUp6("2018-12-03T05:30:00");
                        tableDomain.setDown6("2018-12-03T05:30:00");
                        tableDomain.setUp7("2018-12-03T05:30:00");
                        tableDomain.setDown7("2018-12-03T05:30:00");
                        tableDomainList.add(tableDomain);
                    }
                    tableList.setAaData(tableDomainList);
                    tableList.setiTotalDisplayRecords(busNum);
                    tableList.setiTotalRecords(busNum);
                    tableList.setLineNum("877");
                    tableListList.add(tableList);
                }

            }else{
                lineRange = "在水一方--轨道嘉州恒大中渝广场";
                TableList tableList = new TableList();
                List<TableDomain> tableDomainList = new ArrayList<>();
                for (int i = 0;i<Line_619_count;i++){
                    TableDomain tableDomain = new TableDomain();
                    tableDomain.setCode((i+1)+"");
                    tableDomain.setLineRange(lineRange);
                    tableDomain.setStartStation("在水一方");
                    tableDomain.setUp1("2018-12-03T05:30:00");
                    tableDomain.setDown1("2018-12-03T05:30:00");
                    tableDomain.setUp2("2018-12-03T05:30:00");
                    tableDomain.setDown2("2018-12-03T05:30:00");
                    tableDomain.setUp3("2018-12-03T05:30:00");
                    tableDomain.setDown3("2018-12-03T05:30:00");
                    tableDomain.setUp4("2018-12-03T05:30:00");
                    tableDomain.setDown4("2018-12-03T05:30:00");
                    tableDomain.setUp5("2018-12-03T05:30:00");
                    tableDomain.setDown5("2018-12-03T05:30:00");
                    tableDomain.setUp6("2018-12-03T05:30:00");
                    tableDomain.setDown6("2018-12-03T05:30:00");
                    tableDomain.setUp7("2018-12-03T05:30:00");
                    tableDomain.setDown7("2018-12-03T05:30:00");
                    tableDomainList.add(tableDomain);
                }
                tableList.setAaData(tableDomainList);
                tableList.setiTotalDisplayRecords(Line_619_count);
                tableList.setiTotalRecords(Line_619_count);
                tableList.setLineNum("619");

                tableListList.add(tableList);

                lineRange = "红旗河沟—鸳鸯轨道站";
                tableList = new TableList();
                tableDomainList = new ArrayList<>();
                for (int i = 0;i<Line_877_count;i++){
                    TableDomain tableDomain = new TableDomain();
                    tableDomain.setCode((i+1)+"");
                    tableDomain.setLineRange(lineRange);
                    tableDomain.setStartStation("红旗河沟");
                    tableDomain.setUp1("2018-12-03T05:30:00");
                    tableDomain.setDown1("2018-12-03T05:30:00");
                    tableDomain.setUp2("2018-12-03T05:30:00");
                    tableDomain.setDown2("2018-12-03T05:30:00");
                    tableDomain.setUp3("2018-12-03T05:30:00");
                    tableDomain.setDown3("2018-12-03T05:30:00");
                    tableDomain.setUp4("2018-12-03T05:30:00");
                    tableDomain.setDown4("2018-12-03T05:30:00");
                    tableDomain.setUp5("2018-12-03T05:30:00");
                    tableDomain.setDown5("2018-12-03T05:30:00");
                    tableDomain.setUp6("2018-12-03T05:30:00");
                    tableDomain.setDown6("2018-12-03T05:30:00");
                    tableDomain.setUp7("2018-12-03T05:30:00");
                    tableDomain.setDown7("2018-12-03T05:30:00");
                    tableDomainList.add(tableDomain);
                }
                tableList.setAaData(tableDomainList);
                tableList.setiTotalDisplayRecords(Line_877_count);
                tableList.setiTotalRecords(Line_877_count);
                tableList.setLineNum("877");

                tableListList.add(tableList);
            }

            return tableListList;


//            List<RouteScheduleDto> routeScheduleDtoList = new ArrayList<>();
//            if(requestId.equals(LineAll)){
//                RouteScheduleDto routeScheduleDto_619 = new RouteScheduleDto();
//                routeScheduleDto_619.setId("619");
//                List<String> departureTimes = new ArrayList<>();
//                departureTimes.add("2018-12-03T05:30:00");
//                departureTimes.add("2018-12-03T06:30:00");
//                departureTimes.add("2018-12-03T07:30:00");
//                departureTimes.add("2018-12-03T08:30:00");
//                departureTimes.add("2018-12-03T09:30:00");
//                departureTimes.add("2018-12-03T10:30:00");
//                departureTimes.add("2018-12-03T11:30:00");
//                departureTimes.add("2018-12-03T12:30:00");
//                departureTimes.add("2018-12-03T13:30:00");
//                departureTimes.add("2018-12-03T14:30:00");
//                departureTimes.add("2018-12-03T15:30:00");
//                departureTimes.add("2018-12-03T16:30:00");
//                departureTimes.add("2018-12-03T17:30:00");
//                departureTimes.add("2018-12-03T18:30:00");
//                routeScheduleDto_619.setDepartureTimes(departureTimes);
//                routeScheduleDtoList.add(routeScheduleDto_619);
//
//                RouteScheduleDto routeScheduleDto_877 = new RouteScheduleDto();
//                routeScheduleDto_877.setId("877");
//                List<String> departureTimes_1 = new ArrayList<>();
//                departureTimes_1.add("2018-12-03T05:30:00");
//                departureTimes_1.add("2018-12-03T06:30:00");
//                departureTimes_1.add("2018-12-03T07:30:00");
//                departureTimes_1.add("2018-12-03T08:30:00");
//                departureTimes_1.add("2018-12-03T09:30:00");
//                departureTimes_1.add("2018-12-03T10:30:00");
//                departureTimes_1.add("2018-12-03T11:30:00");
//                departureTimes_1.add("2018-12-03T12:30:00");
//                departureTimes_1.add("2018-12-03T13:30:00");
//                departureTimes_1.add("2018-12-03T14:30:00");
//                departureTimes_1.add("2018-12-03T15:30:00");
//                departureTimes_1.add("2018-12-03T16:30:00");
//                departureTimes_1.add("2018-12-03T17:30:00");
//                departureTimes_1.add("2018-12-03T18:30:00");
//                routeScheduleDto_877.setDepartureTimes(departureTimes_1);
//                routeScheduleDtoList.add(routeScheduleDto_877);
//            }else if(requestId.equals(Line_619)){
//                RouteScheduleDto routeScheduleDto = new RouteScheduleDto();
//                routeScheduleDto.setId("619");
//                List<String> departureTimes = new ArrayList<>();
//                departureTimes.add("2018-12-03T05:30:00");
//                departureTimes.add("2018-12-03T06:30:00");
//                departureTimes.add("2018-12-03T07:30:00");
//                departureTimes.add("2018-12-03T08:30:00");
//                departureTimes.add("2018-12-03T09:30:00");
//                departureTimes.add("2018-12-03T10:30:00");
//                departureTimes.add("2018-12-03T11:30:00");
//                departureTimes.add("2018-12-03T12:30:00");
//                departureTimes.add("2018-12-03T13:30:00");
//                departureTimes.add("2018-12-03T14:30:00");
//                departureTimes.add("2018-12-03T15:30:00");
//                departureTimes.add("2018-12-03T16:30:00");
//                departureTimes.add("2018-12-03T17:30:00");
//                departureTimes.add("2018-12-03T18:30:00");
//                routeScheduleDto.setDepartureTimes(departureTimes);
//                routeScheduleDtoList.add(routeScheduleDto);
//            }else if(requestId.equals(Line_877)){
//                RouteScheduleDto routeScheduleDto = new RouteScheduleDto();
//                routeScheduleDto.setId("817");
//                List<String> departureTimes = new ArrayList<>();
//                departureTimes.add("2018-12-03T05:30:00");
//                departureTimes.add("2018-12-03T06:30:00");
//                departureTimes.add("2018-12-03T07:30:00");
//                departureTimes.add("2018-12-03T08:30:00");
//                departureTimes.add("2018-12-03T09:30:00");
//                departureTimes.add("2018-12-03T10:30:00");
//                departureTimes.add("2018-12-03T11:30:00");
//                departureTimes.add("2018-12-03T12:30:00");
//                departureTimes.add("2018-12-03T13:30:00");
//                departureTimes.add("2018-12-03T14:30:00");
//                departureTimes.add("2018-12-03T15:30:00");
//                departureTimes.add("2018-12-03T16:30:00");
//                departureTimes.add("2018-12-03T17:30:00");
//                departureTimes.add("2018-12-03T18:30:00");
//                routeScheduleDto.setDepartureTimes(departureTimes);
//                routeScheduleDtoList.add(routeScheduleDto);
//            }
//            return routeScheduleDtoList;
        }catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }


    @RequestMapping(value = "/schedule-requestsx/{requestId}",method = RequestMethod.GET)
    public Object scheduleRequestsx(@PathVariable String requestId){
        try{
            return schedule_requests(requestId);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     *根据优化结果返回具体优化事项
     * @param requestId
     */
    @RequestMapping(value = "/schedule-requestsremote/{requestId}",method = RequestMethod.GET)
    public List<TableList> scheduleRequestsRemote(@PathVariable String requestId,@RequestParam int busNum,HttpServletRequest request){
        try{

            System.out.println(">>>requestId:"+requestId);

            RequestBusLine requestBusLine = (RequestBusLine) request.getSession().getAttribute("requestBusLine");
            List<TableList> tableListList = new ArrayList<>();

            boolean hasValue = false;
            String value = null;
            String departTimeValue = null;
//            if(requestBusLine != null && requestBusLine.getObjectivesDtos() != null){
//                List<ObjectivesDto> objectivesDtos = requestBusLine.getObjectivesDtos();
//                if(requestBusLine.getLineNum().equals("619") &&
//                        objectivesDtos.get(0).getValue().equals("0.32") &&
//                        objectivesDtos.get(1).getValue().equals("0.34") &&
//                        objectivesDtos.get(2).getValue().equals("0.34")){
//                    hasValue = true;
//                    departTimeValue = "{\"code\":200,\"message\":\"Request successfully completed!\",\"data\":[{\"id\":\"619\",\"departureTimesUp\":[\"2018-11-28 05:50:00.0\",\"2018-11-28 05:55:00.0\",\"2018-11-28 05:59:00.0\",\"2018-11-28 06:08:00.0\",\"2018-11-28 06:17:00.0\",\"2018-11-28 06:19:00.0\",\"2018-11-28 06:21:00.0\",\"2018-11-28 06:24:00.0\",\"2018-11-28 06:33:00.0\",\"2018-11-28 06:41:00.0\",\"2018-11-28 06:50:00.0\",\"2018-11-28 06:53:00.0\",\"2018-11-28 06:55:00.0\",\"2018-11-28 06:57:00.0\",\"2018-11-28 07:02:00.0\",\"2018-11-28 07:09:00.0\",\"2018-11-28 07:14:00.0\",\"2018-11-28 07:19:00.0\",\"2018-11-28 07:23:00.0\",\"2018-11-28 07:27:00.0\",\"2018-11-28 07:34:00.0\",\"2018-11-28 07:40:00.0\",\"2018-11-28 07:43:00.0\",\"2018-11-28 07:51:00.0\",\"2018-11-28 07:56:00.0\",\"2018-11-28 08:03:00.0\",\"2018-11-28 08:09:00.0\",\"2018-11-28 08:16:00.0\",\"2018-11-28 08:23:00.0\",\"2018-11-28 08:31:00.0\",\"2018-11-28 08:35:00.0\",\"2018-11-28 08:42:00.0\",\"2018-11-28 08:44:00.0\",\"2018-11-28 08:46:00.0\",\"2018-11-28 08:52:00.0\",\"2018-11-28 08:56:00.0\",\"2018-11-28 08:58:00.0\",\"2018-11-28 09:05:00.0\",\"2018-11-28 09:12:00.0\",\"2018-11-28 09:15:00.0\",\"2018-11-28 09:24:00.0\",\"2018-11-28 09:28:00.0\",\"2018-11-28 09:30:00.0\",\"2018-11-28 09:32:00.0\",\"2018-11-28 09:41:00.0\",\"2018-11-28 09:46:00.0\",\"2018-11-28 09:50:00.0\",\"2018-11-28 09:53:00.0\",\"2018-11-28 09:58:00.0\",\"2018-11-28 10:04:00.0\",\"2018-11-28 10:06:00.0\",\"2018-11-28 10:15:00.0\",\"2018-11-28 10:23:00.0\",\"2018-11-28 10:29:00.0\",\"2018-11-28 10:34:00.0\",\"2018-11-28 10:42:00.0\",\"2018-11-28 10:46:00.0\",\"2018-11-28 10:52:00.0\",\"2018-11-28 10:55:00.0\",\"2018-11-28 10:59:00.0\",\"2018-11-28 11:01:00.0\",\"2018-11-28 11:09:00.0\",\"2018-11-28 11:17:00.0\",\"2018-11-28 11:20:00.0\",\"2018-11-28 11:28:00.0\",\"2018-11-28 11:32:00.0\",\"2018-11-28 11:39:00.0\",\"2018-11-28 11:44:00.0\",\"2018-11-28 11:48:00.0\",\"2018-11-28 11:56:00.0\",\"2018-11-28 12:04:00.0\",\"2018-11-28 12:13:00.0\",\"2018-11-28 12:20:00.0\",\"2018-11-28 12:29:00.0\",\"2018-11-28 12:31:00.0\",\"2018-11-28 12:34:00.0\",\"2018-11-28 12:36:00.0\",\"2018-11-28 12:38:00.0\",\"2018-11-28 12:41:00.0\",\"2018-11-28 12:47:00.0\",\"2018-11-28 12:49:00.0\",\"2018-11-28 12:54:00.0\",\"2018-11-28 13:02:00.0\",\"2018-11-28 13:06:00.0\",\"2018-11-28 13:12:00.0\",\"2018-11-28 13:21:00.0\",\"2018-11-28 13:28:00.0\",\"2018-11-28 13:33:00.0\",\"2018-11-28 13:39:00.0\",\"2018-11-28 13:44:00.0\",\"2018-11-28 13:50:00.0\",\"2018-11-28 13:54:00.0\",\"2018-11-28 13:59:00.0\",\"2018-11-28 14:04:00.0\",\"2018-11-28 14:06:00.0\",\"2018-11-28 14:12:00.0\",\"2018-11-28 14:18:00.0\",\"2018-11-28 14:21:00.0\",\"2018-11-28 14:30:00.0\",\"2018-11-28 14:38:00.0\",\"2018-11-28 14:44:00.0\",\"2018-11-28 14:46:00.0\",\"2018-11-28 14:55:00.0\",\"2018-11-28 14:57:00.0\",\"2018-11-28 14:59:00.0\",\"2018-11-28 15:02:00.0\",\"2018-11-28 15:04:00.0\",\"2018-11-28 15:10:00.0\",\"2018-11-28 15:12:00.0\",\"2018-11-28 15:19:00.0\",\"2018-11-28 15:28:00.0\",\"2018-11-28 15:30:00.0\",\"2018-11-28 15:34:00.0\",\"2018-11-28 15:37:00.0\",\"2018-11-28 15:46:00.0\",\"2018-11-28 15:54:00.0\",\"2018-11-28 15:57:00.0\",\"2018-11-28 16:00:00.0\",\"2018-11-28 16:09:00.0\",\"2018-11-28 16:17:00.0\",\"2018-11-28 16:19:00.0\",\"2018-11-28 16:26:00.0\",\"2018-11-28 16:30:00.0\",\"2018-11-28 16:35:00.0\",\"2018-11-28 16:41:00.0\",\"2018-11-28 16:45:00.0\",\"2018-11-28 16:50:00.0\",\"2018-11-28 16:58:00.0\",\"2018-11-28 17:02:00.0\",\"2018-11-28 17:10:00.0\",\"2018-11-28 17:19:00.0\",\"2018-11-28 17:25:00.0\",\"2018-11-28 17:33:00.0\",\"2018-11-28 17:42:00.0\",\"2018-11-28 17:50:00.0\",\"2018-11-28 17:53:00.0\",\"2018-11-28 17:56:00.0\",\"2018-11-28 18:04:00.0\",\"2018-11-28 18:08:00.0\",\"2018-11-28 18:14:00.0\",\"2018-11-28 18:22:00.0\",\"2018-11-28 18:28:00.0\",\"2018-11-28 18:32:00.0\",\"2018-11-28 18:39:00.0\",\"2018-11-28 18:44:00.0\",\"2018-11-28 18:48:00.0\",\"2018-11-28 18:52:00.0\",\"2018-11-28 18:56:00.0\",\"2018-11-28 19:00:00.0\",\"2018-11-28 20:30:00.0\"],\"departureTimesDown\":[\"2018-11-28 05:50:00.0\",\"2018-11-28 06:09:00.0\",\"2018-11-28 06:28:00.0\",\"2018-11-28 07:39:00.0\",\"2018-11-28 07:49:00.0\",\"2018-11-28 07:50:00.0\",\"2018-11-28 07:54:00.0\",\"2018-11-28 07:56:00.0\",\"2018-11-28 08:16:00.0\",\"2018-11-28 08:20:00.0\",\"2018-11-28 08:21:00.0\",\"2018-11-28 08:46:00.0\",\"2018-11-28 09:05:00.0\",\"2018-11-28 09:08:00.0\",\"2018-11-28 09:08:00.0\",\"2018-11-28 09:10:00.0\",\"2018-11-28 09:11:00.0\",\"2018-11-28 09:22:00.0\",\"2018-11-28 09:25:00.0\",\"2018-11-28 09:27:00.0\",\"2018-11-28 09:31:00.0\",\"2018-11-28 09:38:00.0\",\"2018-11-28 09:39:00.0\",\"2018-11-28 09:47:00.0\",\"2018-11-28 10:04:00.0\",\"2018-11-28 10:09:00.0\",\"2018-11-28 10:10:00.0\",\"2018-11-28 10:10:00.0\",\"2018-11-28 10:13:00.0\",\"2018-11-28 10:28:00.0\",\"2018-11-28 10:29:00.0\",\"2018-11-28 10:31:00.0\",\"2018-11-28 10:34:00.0\",\"2018-11-28 10:35:00.0\",\"2018-11-28 10:36:00.0\",\"2018-11-28 10:37:00.0\",\"2018-11-28 10:40:00.0\",\"2018-11-28 10:46:00.0\",\"2018-11-28 10:48:00.0\",\"2018-11-28 10:51:00.0\",\"2018-11-28 10:53:00.0\",\"2018-11-28 11:00:00.0\",\"2018-11-28 11:04:00.0\",\"2018-11-28 11:15:00.0\",\"2018-11-28 11:22:00.0\",\"2018-11-28 11:25:00.0\",\"2018-11-28 11:26:00.0\",\"2018-11-28 11:38:00.0\",\"2018-11-28 11:39:00.0\",\"2018-11-28 11:39:00.0\",\"2018-11-28 11:47:00.0\",\"2018-11-28 11:56:00.0\",\"2018-11-28 11:58:00.0\",\"2018-11-28 12:08:00.0\",\"2018-11-28 12:09:00.0\",\"2018-11-28 12:19:00.0\",\"2018-11-28 12:20:00.0\",\"2018-11-28 12:21:00.0\",\"2018-11-28 12:28:00.0\",\"2018-11-28 12:35:00.0\",\"2018-11-28 12:38:00.0\",\"2018-11-28 12:49:00.0\",\"2018-11-28 12:50:00.0\",\"2018-11-28 13:01:00.0\",\"2018-11-28 13:04:00.0\",\"2018-11-28 13:06:00.0\",\"2018-11-28 13:20:00.0\",\"2018-11-28 13:22:00.0\",\"2018-11-28 13:32:00.0\",\"2018-11-28 13:35:00.0\",\"2018-11-28 13:36:00.0\",\"2018-11-28 13:36:00.0\",\"2018-11-28 13:58:00.0\",\"2018-11-28 14:02:00.0\",\"2018-11-28 14:05:00.0\",\"2018-11-28 14:14:00.0\",\"2018-11-28 14:16:00.0\",\"2018-11-28 14:22:00.0\",\"2018-11-28 14:31:00.0\",\"2018-11-28 14:32:00.0\",\"2018-11-28 14:34:00.0\",\"2018-11-28 14:34:00.0\",\"2018-11-28 14:36:00.0\",\"2018-11-28 14:39:00.0\",\"2018-11-28 14:47:00.0\",\"2018-11-28 14:51:00.0\",\"2018-11-28 15:02:00.0\",\"2018-11-28 15:13:00.0\",\"2018-11-28 15:21:00.0\",\"2018-11-28 15:23:00.0\",\"2018-11-28 15:23:00.0\",\"2018-11-28 15:34:00.0\",\"2018-11-28 15:34:00.0\",\"2018-11-28 15:37:00.0\",\"2018-11-28 15:39:00.0\",\"2018-11-28 15:48:00.0\",\"2018-11-28 15:59:00.0\",\"2018-11-28 16:01:00.0\",\"2018-11-28 16:04:00.0\",\"2018-11-28 16:07:00.0\",\"2018-11-28 16:08:00.0\",\"2018-11-28 16:18:00.0\",\"2018-11-28 16:24:00.0\",\"2018-11-28 16:32:00.0\",\"2018-11-28 16:34:00.0\",\"2018-11-28 16:38:00.0\",\"2018-11-28 16:43:00.0\",\"2018-11-28 16:46:00.0\",\"2018-11-28 16:58:00.0\",\"2018-11-28 16:59:00.0\",\"2018-11-28 17:00:00.0\",\"2018-11-28 17:10:00.0\",\"2018-11-28 17:13:00.0\",\"2018-11-28 17:23:00.0\",\"2018-11-28 17:28:00.0\",\"2018-11-28 17:28:00.0\",\"2018-11-28 17:32:00.0\",\"2018-11-28 17:34:00.0\",\"2018-11-28 17:43:00.0\",\"2018-11-28 17:44:00.0\",\"2018-11-28 17:45:00.0\",\"2018-11-28 17:53:00.0\",\"2018-11-28 18:03:00.0\",\"2018-11-28 18:21:00.0\",\"2018-11-28 18:24:00.0\",\"2018-11-28 18:30:00.0\",\"2018-11-28 18:32:00.0\",\"2018-11-28 19:04:00.0\",\"2018-11-28 19:06:00.0\",\"2018-11-28 19:12:00.0\",\"2018-11-28 19:20:00.0\",\"2018-11-28 19:21:00.0\",\"2018-11-28 19:27:00.0\",\"2018-11-28 19:34:00.0\",\"2018-11-28 19:38:00.0\",\"2018-11-28 19:56:00.0\",\"2018-11-28 20:04:00.0\",\"2018-11-28 20:12:00.0\",\"2018-11-28 20:15:00.0\",\"2018-11-28 20:21:00.0\",\"2018-11-28 20:21:00.0\",\"2018-11-28 20:21:00.0\",\"2018-11-28 20:30:00.0\"]}]}";
//                    value = "{\"code\":200,\"message\":\"Request successfully completed!\",\"data\":[{\"busId\":\"83262\",\"departureTime\":\"2018-11-28 05:50:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83263\",\"departureTime\":\"2018-11-28 05:55:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83264\",\"departureTime\":\"2018-11-28 05:59:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83265\",\"departureTime\":\"2018-11-28 06:08:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83266\",\"departureTime\":\"2018-11-28 06:17:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83267\",\"departureTime\":\"2018-11-28 06:19:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83268\",\"departureTime\":\"2018-11-28 06:21:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83269\",\"departureTime\":\"2018-11-28 06:24:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83270\",\"departureTime\":\"2018-11-28 06:33:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83271\",\"departureTime\":\"2018-11-28 06:41:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83273\",\"departureTime\":\"2018-11-28 06:50:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83274\",\"departureTime\":\"2018-11-28 06:53:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83275\",\"departureTime\":\"2018-11-28 06:55:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83276\",\"departureTime\":\"2018-11-28 06:57:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83277\",\"departureTime\":\"2018-11-28 07:02:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83278\",\"departureTime\":\"2018-11-28 07:09:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83279\",\"departureTime\":\"2018-11-28 07:14:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83280\",\"departureTime\":\"2018-11-28 07:19:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83281\",\"departureTime\":\"2018-11-28 07:23:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83282\",\"departureTime\":\"2018-11-28 07:27:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83284\",\"departureTime\":\"2018-11-28 07:34:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83285\",\"departureTime\":\"2018-11-28 07:40:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83286\",\"departureTime\":\"2018-11-28 07:43:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83287\",\"departureTime\":\"2018-11-28 07:51:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83298\",\"departureTime\":\"2018-11-28 07:56:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83299\",\"departureTime\":\"2018-11-28 08:03:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83272\",\"departureTime\":\"2018-11-28 08:09:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83300\",\"departureTime\":\"2018-11-28 08:16:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83283\",\"departureTime\":\"2018-11-28 08:23:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83301\",\"departureTime\":\"2018-11-28 08:31:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83302\",\"departureTime\":\"2018-11-28 08:35:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83305\",\"departureTime\":\"2018-11-28 08:42:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83303\",\"departureTime\":\"2018-11-28 08:44:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83304\",\"departureTime\":\"2018-11-28 08:46:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83306\",\"departureTime\":\"2018-11-28 08:52:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83307\",\"departureTime\":\"2018-11-28 08:56:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83308\",\"departureTime\":\"2018-11-28 08:58:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83309\",\"departureTime\":\"2018-11-28 09:05:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83310\",\"departureTime\":\"2018-11-28 09:12:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83311\",\"departureTime\":\"2018-11-28 09:15:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83312\",\"departureTime\":\"2018-11-28 09:24:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83313\",\"departureTime\":\"2018-11-28 09:28:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83314\",\"departureTime\":\"2018-11-28 09:30:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83315\",\"departureTime\":\"2018-11-28 09:32:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83316\",\"departureTime\":\"2018-11-28 09:41:00.0\",\"direction\":0,\"shift\":1},{\"busId\":\"83278\",\"departureTime\":\"2018-11-28 09:46:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83279\",\"departureTime\":\"2018-11-28 09:50:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83280\",\"departureTime\":\"2018-11-28 09:53:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83281\",\"departureTime\":\"2018-11-28 09:58:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83282\",\"departureTime\":\"2018-11-28 10:04:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83284\",\"departureTime\":\"2018-11-28 10:06:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83285\",\"departureTime\":\"2018-11-28 10:15:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83286\",\"departureTime\":\"2018-11-28 10:23:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83287\",\"departureTime\":\"2018-11-28 10:29:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83298\",\"departureTime\":\"2018-11-28 10:34:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83299\",\"departureTime\":\"2018-11-28 10:42:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83315\",\"departureTime\":\"2018-11-28 10:46:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83272\",\"departureTime\":\"2018-11-28 10:52:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83300\",\"departureTime\":\"2018-11-28 10:55:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83316\",\"departureTime\":\"2018-11-28 10:59:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83283\",\"departureTime\":\"2018-11-28 11:01:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83301\",\"departureTime\":\"2018-11-28 11:09:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83302\",\"departureTime\":\"2018-11-28 11:17:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83305\",\"departureTime\":\"2018-11-28 11:20:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83303\",\"departureTime\":\"2018-11-28 11:28:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83310\",\"departureTime\":\"2018-11-28 11:32:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83311\",\"departureTime\":\"2018-11-28 11:39:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83312\",\"departureTime\":\"2018-11-28 11:44:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83313\",\"departureTime\":\"2018-11-28 11:48:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83314\",\"departureTime\":\"2018-11-28 11:56:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83304\",\"departureTime\":\"2018-11-28 12:04:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83306\",\"departureTime\":\"2018-11-28 12:13:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83307\",\"departureTime\":\"2018-11-28 12:20:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83308\",\"departureTime\":\"2018-11-28 12:29:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83309\",\"departureTime\":\"2018-11-28 12:31:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83267\",\"departureTime\":\"2018-11-28 12:34:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83268\",\"departureTime\":\"2018-11-28 12:36:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83269\",\"departureTime\":\"2018-11-28 12:38:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83270\",\"departureTime\":\"2018-11-28 12:41:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83271\",\"departureTime\":\"2018-11-28 12:47:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83282\",\"departureTime\":\"2018-11-28 12:49:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83284\",\"departureTime\":\"2018-11-28 12:54:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83298\",\"departureTime\":\"2018-11-28 13:02:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83285\",\"departureTime\":\"2018-11-28 13:06:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83286\",\"departureTime\":\"2018-11-28 13:12:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83287\",\"departureTime\":\"2018-11-28 13:21:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83299\",\"departureTime\":\"2018-11-28 13:28:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83315\",\"departureTime\":\"2018-11-28 13:33:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83272\",\"departureTime\":\"2018-11-28 13:39:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83300\",\"departureTime\":\"2018-11-28 13:44:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83316\",\"departureTime\":\"2018-11-28 13:50:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83283\",\"departureTime\":\"2018-11-28 13:54:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83301\",\"departureTime\":\"2018-11-28 13:59:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83302\",\"departureTime\":\"2018-11-28 14:04:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83305\",\"departureTime\":\"2018-11-28 14:06:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83303\",\"departureTime\":\"2018-11-28 14:12:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83278\",\"departureTime\":\"2018-11-28 14:18:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83279\",\"departureTime\":\"2018-11-28 14:21:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83280\",\"departureTime\":\"2018-11-28 14:30:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83281\",\"departureTime\":\"2018-11-28 14:38:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83313\",\"departureTime\":\"2018-11-28 14:44:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83314\",\"departureTime\":\"2018-11-28 14:46:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83304\",\"departureTime\":\"2018-11-28 14:55:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83306\",\"departureTime\":\"2018-11-28 14:57:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83307\",\"departureTime\":\"2018-11-28 14:59:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83312\",\"departureTime\":\"2018-11-28 15:02:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83262\",\"departureTime\":\"2018-11-28 15:04:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83308\",\"departureTime\":\"2018-11-28 15:10:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83273\",\"departureTime\":\"2018-11-28 15:12:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83263\",\"departureTime\":\"2018-11-28 15:19:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83264\",\"departureTime\":\"2018-11-28 15:28:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83274\",\"departureTime\":\"2018-11-28 15:30:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83311\",\"departureTime\":\"2018-11-28 15:34:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83265\",\"departureTime\":\"2018-11-28 15:37:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83275\",\"departureTime\":\"2018-11-28 15:46:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83276\",\"departureTime\":\"2018-11-28 15:54:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83266\",\"departureTime\":\"2018-11-28 15:57:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83277\",\"departureTime\":\"2018-11-28 16:00:00.0\",\"direction\":0,\"shift\":2},{\"busId\":\"83309\",\"departureTime\":\"2018-11-28 16:09:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83310\",\"departureTime\":\"2018-11-28 16:17:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83267\",\"departureTime\":\"2018-11-28 16:19:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83268\",\"departureTime\":\"2018-11-28 16:26:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83269\",\"departureTime\":\"2018-11-28 16:30:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83270\",\"departureTime\":\"2018-11-28 16:35:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83271\",\"departureTime\":\"2018-11-28 16:41:00.0\",\"direction\":0,\"shift\":3},{\"busId\":\"83282\",\"departureTime\":\"2018-11-28 16:45:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83284\",\"departureTime\":\"2018-11-28 16:50:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83298\",\"departureTime\":\"2018-11-28 16:58:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83285\",\"departureTime\":\"2018-11-28 17:02:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83286\",\"departureTime\":\"2018-11-28 17:10:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83287\",\"departureTime\":\"2018-11-28 17:19:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83299\",\"departureTime\":\"2018-11-28 17:25:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83315\",\"departureTime\":\"2018-11-28 17:33:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83272\",\"departureTime\":\"2018-11-28 17:42:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83300\",\"departureTime\":\"2018-11-28 17:50:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83316\",\"departureTime\":\"2018-11-28 17:53:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83283\",\"departureTime\":\"2018-11-28 17:56:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83301\",\"departureTime\":\"2018-11-28 18:04:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83302\",\"departureTime\":\"2018-11-28 18:08:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83305\",\"departureTime\":\"2018-11-28 18:14:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83303\",\"departureTime\":\"2018-11-28 18:22:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83278\",\"departureTime\":\"2018-11-28 18:28:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83279\",\"departureTime\":\"2018-11-28 18:32:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83280\",\"departureTime\":\"2018-11-28 18:39:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83281\",\"departureTime\":\"2018-11-28 18:44:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83313\",\"departureTime\":\"2018-11-28 18:48:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83314\",\"departureTime\":\"2018-11-28 18:52:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83304\",\"departureTime\":\"2018-11-28 18:56:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83306\",\"departureTime\":\"2018-11-28 19:00:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83307\",\"departureTime\":\"2018-11-28 20:30:00.0\",\"direction\":0,\"shift\":4},{\"busId\":\"83272\",\"departureTime\":\"2018-11-28 05:50:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83283\",\"departureTime\":\"2018-11-28 06:09:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83305\",\"departureTime\":\"2018-11-28 06:28:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83262\",\"departureTime\":\"2018-11-28 07:39:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83263\",\"departureTime\":\"2018-11-28 07:49:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83265\",\"departureTime\":\"2018-11-28 07:50:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83264\",\"departureTime\":\"2018-11-28 07:54:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83266\",\"departureTime\":\"2018-11-28 07:56:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83268\",\"departureTime\":\"2018-11-28 08:16:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83269\",\"departureTime\":\"2018-11-28 08:20:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83267\",\"departureTime\":\"2018-11-28 08:21:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83270\",\"departureTime\":\"2018-11-28 08:46:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83273\",\"departureTime\":\"2018-11-28 09:05:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83274\",\"departureTime\":\"2018-11-28 09:08:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83271\",\"departureTime\":\"2018-11-28 09:08:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83275\",\"departureTime\":\"2018-11-28 09:10:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83276\",\"departureTime\":\"2018-11-28 09:11:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83277\",\"departureTime\":\"2018-11-28 09:22:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83280\",\"departureTime\":\"2018-11-28 09:25:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83278\",\"departureTime\":\"2018-11-28 09:27:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83279\",\"departureTime\":\"2018-11-28 09:31:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83281\",\"departureTime\":\"2018-11-28 09:38:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83284\",\"departureTime\":\"2018-11-28 09:39:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83282\",\"departureTime\":\"2018-11-28 09:47:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83285\",\"departureTime\":\"2018-11-28 10:04:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83287\",\"departureTime\":\"2018-11-28 10:09:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83299\",\"departureTime\":\"2018-11-28 10:10:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83298\",\"departureTime\":\"2018-11-28 10:10:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83286\",\"departureTime\":\"2018-11-28 10:13:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83300\",\"departureTime\":\"2018-11-28 10:28:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83272\",\"departureTime\":\"2018-11-28 10:29:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83283\",\"departureTime\":\"2018-11-28 10:31:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83302\",\"departureTime\":\"2018-11-28 10:34:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83301\",\"departureTime\":\"2018-11-28 10:35:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83308\",\"departureTime\":\"2018-11-28 10:36:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83307\",\"departureTime\":\"2018-11-28 10:37:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83305\",\"departureTime\":\"2018-11-28 10:40:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83304\",\"departureTime\":\"2018-11-28 10:46:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83306\",\"departureTime\":\"2018-11-28 10:48:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83303\",\"departureTime\":\"2018-11-28 10:51:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83310\",\"departureTime\":\"2018-11-28 10:53:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83309\",\"departureTime\":\"2018-11-28 11:00:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83311\",\"departureTime\":\"2018-11-28 11:04:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83312\",\"departureTime\":\"2018-11-28 11:15:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83314\",\"departureTime\":\"2018-11-28 11:22:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83315\",\"departureTime\":\"2018-11-28 11:25:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83313\",\"departureTime\":\"2018-11-28 11:26:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83278\",\"departureTime\":\"2018-11-28 11:38:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83316\",\"departureTime\":\"2018-11-28 11:39:00.0\",\"direction\":1,\"shift\":1},{\"busId\":\"83279\",\"departureTime\":\"2018-11-28 11:39:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83280\",\"departureTime\":\"2018-11-28 11:47:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83282\",\"departureTime\":\"2018-11-28 11:56:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83281\",\"departureTime\":\"2018-11-28 11:58:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83285\",\"departureTime\":\"2018-11-28 12:08:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83284\",\"departureTime\":\"2018-11-28 12:09:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83286\",\"departureTime\":\"2018-11-28 12:19:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83298\",\"departureTime\":\"2018-11-28 12:20:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83287\",\"departureTime\":\"2018-11-28 12:21:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83299\",\"departureTime\":\"2018-11-28 12:28:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83300\",\"departureTime\":\"2018-11-28 12:35:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83272\",\"departureTime\":\"2018-11-28 12:38:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83316\",\"departureTime\":\"2018-11-28 12:49:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83315\",\"departureTime\":\"2018-11-28 12:50:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83283\",\"departureTime\":\"2018-11-28 13:01:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83302\",\"departureTime\":\"2018-11-28 13:04:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83301\",\"departureTime\":\"2018-11-28 13:06:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83303\",\"departureTime\":\"2018-11-28 13:20:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83310\",\"departureTime\":\"2018-11-28 13:22:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83305\",\"departureTime\":\"2018-11-28 13:32:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83313\",\"departureTime\":\"2018-11-28 13:35:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83312\",\"departureTime\":\"2018-11-28 13:36:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83311\",\"departureTime\":\"2018-11-28 13:36:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83304\",\"departureTime\":\"2018-11-28 13:58:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83314\",\"departureTime\":\"2018-11-28 14:02:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83306\",\"departureTime\":\"2018-11-28 14:05:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83307\",\"departureTime\":\"2018-11-28 14:14:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83308\",\"departureTime\":\"2018-11-28 14:16:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83267\",\"departureTime\":\"2018-11-28 14:22:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83269\",\"departureTime\":\"2018-11-28 14:31:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83270\",\"departureTime\":\"2018-11-28 14:32:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83309\",\"departureTime\":\"2018-11-28 14:34:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83271\",\"departureTime\":\"2018-11-28 14:34:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83268\",\"departureTime\":\"2018-11-28 14:36:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83282\",\"departureTime\":\"2018-11-28 14:39:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83284\",\"departureTime\":\"2018-11-28 14:47:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83298\",\"departureTime\":\"2018-11-28 14:51:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83285\",\"departureTime\":\"2018-11-28 15:02:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83286\",\"departureTime\":\"2018-11-28 15:13:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83315\",\"departureTime\":\"2018-11-28 15:21:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83299\",\"departureTime\":\"2018-11-28 15:23:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83287\",\"departureTime\":\"2018-11-28 15:23:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83272\",\"departureTime\":\"2018-11-28 15:34:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83300\",\"departureTime\":\"2018-11-28 15:34:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83316\",\"departureTime\":\"2018-11-28 15:37:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83301\",\"departureTime\":\"2018-11-28 15:39:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83283\",\"departureTime\":\"2018-11-28 15:48:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83302\",\"departureTime\":\"2018-11-28 15:59:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83278\",\"departureTime\":\"2018-11-28 16:01:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83305\",\"departureTime\":\"2018-11-28 16:04:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83279\",\"departureTime\":\"2018-11-28 16:07:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83303\",\"departureTime\":\"2018-11-28 16:08:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83281\",\"departureTime\":\"2018-11-28 16:18:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83280\",\"departureTime\":\"2018-11-28 16:24:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83313\",\"departureTime\":\"2018-11-28 16:32:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83314\",\"departureTime\":\"2018-11-28 16:34:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83307\",\"departureTime\":\"2018-11-28 16:38:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83304\",\"departureTime\":\"2018-11-28 16:43:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83306\",\"departureTime\":\"2018-11-28 16:46:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83262\",\"departureTime\":\"2018-11-28 16:58:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83312\",\"departureTime\":\"2018-11-28 16:59:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83308\",\"departureTime\":\"2018-11-28 17:00:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83263\",\"departureTime\":\"2018-11-28 17:10:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83273\",\"departureTime\":\"2018-11-28 17:13:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83311\",\"departureTime\":\"2018-11-28 17:23:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83274\",\"departureTime\":\"2018-11-28 17:28:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83264\",\"departureTime\":\"2018-11-28 17:28:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83275\",\"departureTime\":\"2018-11-28 17:32:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83276\",\"departureTime\":\"2018-11-28 17:34:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83309\",\"departureTime\":\"2018-11-28 17:43:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83265\",\"departureTime\":\"2018-11-28 17:44:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83277\",\"departureTime\":\"2018-11-28 17:45:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83266\",\"departureTime\":\"2018-11-28 17:53:00.0\",\"direction\":1,\"shift\":2},{\"busId\":\"83310\",\"departureTime\":\"2018-11-28 18:03:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83268\",\"departureTime\":\"2018-11-28 18:21:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83267\",\"departureTime\":\"2018-11-28 18:24:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83269\",\"departureTime\":\"2018-11-28 18:30:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83270\",\"departureTime\":\"2018-11-28 18:32:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83284\",\"departureTime\":\"2018-11-28 19:04:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83271\",\"departureTime\":\"2018-11-28 19:06:00.0\",\"direction\":1,\"shift\":3},{\"busId\":\"83282\",\"departureTime\":\"2018-11-28 19:12:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83298\",\"departureTime\":\"2018-11-28 19:20:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83285\",\"departureTime\":\"2018-11-28 19:21:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83286\",\"departureTime\":\"2018-11-28 19:27:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83299\",\"departureTime\":\"2018-11-28 19:34:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83287\",\"departureTime\":\"2018-11-28 19:38:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83315\",\"departureTime\":\"2018-11-28 19:56:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83272\",\"departureTime\":\"2018-11-28 20:04:00.0\",\"direction\":1,\"shift\":5},{\"busId\":\"83300\",\"departureTime\":\"2018-11-28 20:12:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83301\",\"departureTime\":\"2018-11-28 20:15:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83316\",\"departureTime\":\"2018-11-28 20:21:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83305\",\"departureTime\":\"2018-11-28 20:21:00.0\",\"direction\":1,\"shift\":5},{\"busId\":\"83302\",\"departureTime\":\"2018-11-28 20:21:00.0\",\"direction\":1,\"shift\":4},{\"busId\":\"83283\",\"departureTime\":\"2018-11-28 20:30:00.0\",\"direction\":1,\"shift\":5}]}";
//                }
//                else if(requestBusLine.getLineNum().equals("619") &&
//                        objectivesDtos.get(0).getValue().equals("0.10") &&
//                        objectivesDtos.get(1).getValue().equals("0.10") &&
//                        objectivesDtos.get(2).getValue().equals("0.80")){
//                    hasValue = true;
//                    value = "[{\"id\":\"Bus #24\",\"departureTimes\":[\"2019-01-09 18:18:00.0\",\"2019-01-10 01:12:00.0\"]},{\"id\":\"Bus #23\",\"departureTimes\":[\"2019-01-09 18:03:00.0\",\"2019-01-10 01:12:00.0\"]},{\"id\":\"Bus #26\",\"departureTimes\":[\"2019-01-09 18:41:00.0\",\"2019-01-10 01:45:00.0\"]},{\"id\":\"Bus #25\",\"departureTimes\":[\"2019-01-09 18:27:00.0\",\"2019-01-10 01:26:00.0\"]},{\"id\":\"Bus #42\",\"departureTimes\":[\"2019-01-09 20:58:00.0\",\"2019-01-10 03:54:00.0\"]},{\"id\":\"Bus #20\",\"departureTimes\":[\"2019-01-09 17:29:00.0\",\"2019-01-10 00:32:00.0\"]},{\"id\":\"Bus #41\",\"departureTimes\":[\"2019-01-09 20:52:00.0\",\"2019-01-10 03:36:00.0\"]},{\"id\":\"Bus #44\",\"departureTimes\":[\"2019-01-09 21:21:00.0\",\"2019-01-10 04:18:00.0\"]},{\"id\":\"Bus #22\",\"departureTimes\":[\"2019-01-09 17:51:00.0\",\"2019-01-10 01:07:00.0\"]},{\"id\":\"Bus #43\",\"departureTimes\":[\"2019-01-09 21:06:00.0\",\"2019-01-10 04:04:00.0\"]},{\"id\":\"Bus #21\",\"departureTimes\":[\"2019-01-09 17:32:00.0\",\"2019-01-10 00:48:00.0\"]},{\"id\":\"Bus #28\",\"departureTimes\":[\"2019-01-09 18:45:00.0\",\"2019-01-10 01:54:00.0\"]},{\"id\":\"Bus #27\",\"departureTimes\":[\"2019-01-09 18:41:00.0\",\"2019-01-10 01:48:00.0\"]},{\"id\":\"Bus #29\",\"departureTimes\":[\"2019-01-09 18:53:00.0\",\"2019-01-10 02:02:00.0\"]},{\"id\":\"Bus #35\",\"departureTimes\":[\"2019-01-09 19:57:00.0\",\"2019-01-10 02:44:00.0\"]},{\"id\":\"Bus #13\",\"departureTimes\":[\"2019-01-09 16:43:00.0\",\"2019-01-09 23:37:00.0\"]},{\"id\":\"Bus #34\",\"departureTimes\":[\"2019-01-09 19:45:00.0\",\"2019-01-10 02:39:00.0\"]},{\"id\":\"Bus #12\",\"departureTimes\":[\"2019-01-09 16:27:00.0\",\"2019-01-09 23:22:00.0\"]},{\"id\":\"Bus #37\",\"departureTimes\":[\"2019-01-09 20:19:00.0\",\"2019-01-10 03:04:00.0\"]},{\"id\":\"Bus #15\",\"departureTimes\":[\"2019-01-09 16:54:00.0\",\"2019-01-09 23:45:00.0\"]},{\"id\":\"Bus #36\",\"departureTimes\":[\"2019-01-09 20:00:00.0\",\"2019-01-10 02:56:00.0\"]},{\"id\":\"Bus #14\",\"departureTimes\":[\"2019-01-09 16:54:00.0\",\"2019-01-09 23:43:00.0\"]},{\"id\":\"Bus #31\",\"departureTimes\":[\"2019-01-09 19:05:00.0\",\"2019-01-10 02:22:00.0\"]},{\"id\":\"Bus #30\",\"departureTimes\":[\"2019-01-09 19:04:00.0\",\"2019-01-10 02:10:00.0\"]},{\"id\":\"Bus #33\",\"departureTimes\":[\"2019-01-09 19:30:00.0\",\"2019-01-10 02:37:00.0\"]},{\"id\":\"Bus #11\",\"departureTimes\":[\"2019-01-09 16:25:00.0\",\"2019-01-09 23:19:00.0\"]},{\"id\":\"Bus #32\",\"departureTimes\":[\"2019-01-09 19:11:00.0\",\"2019-01-10 02:26:00.0\"]},{\"id\":\"Bus #10\",\"departureTimes\":[\"2019-01-09 16:11:00.0\",\"2019-01-09 23:01:00.0\"]},{\"id\":\"Bus #0\",\"departureTimes\":[\"2019-01-09 14:00:00.0\",\"2019-01-09 21:33:00.0\",\"2019-01-10 04:22:00.0\"]},{\"id\":\"Bus #2\",\"departureTimes\":[\"2019-01-09 14:26:00.0\",\"2019-01-09 21:40:00.0\",\"2019-01-10 04:42:00.0\"]},{\"id\":\"Bus #1\",\"departureTimes\":[\"2019-01-09 14:10:00.0\",\"2019-01-09 21:36:00.0\",\"2019-01-10 04:39:00.0\"]},{\"id\":\"Bus #39\",\"departureTimes\":[\"2019-01-09 20:38:00.0\",\"2019-01-10 03:21:00.0\"]},{\"id\":\"Bus #17\",\"departureTimes\":[\"2019-01-09 17:19:00.0\",\"2019-01-10 00:07:00.0\"]},{\"id\":\"Bus #38\",\"departureTimes\":[\"2019-01-09 20:24:00.0\",\"2019-01-10 03:19:00.0\"]},{\"id\":\"Bus #16\",\"departureTimes\":[\"2019-01-09 17:13:00.0\",\"2019-01-09 23:56:00.0\"]},{\"id\":\"Bus #19\",\"departureTimes\":[\"2019-01-09 17:28:00.0\",\"2019-01-10 00:27:00.0\"]},{\"id\":\"Bus #18\",\"departureTimes\":[\"2019-01-09 17:20:00.0\",\"2019-01-10 00:23:00.0\"]},{\"id\":\"Bus #8\",\"departureTimes\":[\"2019-01-09 15:40:00.0\",\"2019-01-09 22:42:00.0\",\"2019-01-10 05:40:00.0\"]},{\"id\":\"Bus #7\",\"departureTimes\":[\"2019-01-09 15:21:00.0\",\"2019-01-09 22:35:00.0\",\"2019-01-10 05:32:00.0\"]},{\"id\":\"Bus #9\",\"departureTimes\":[\"2019-01-09 15:57:00.0\",\"2019-01-09 22:56:00.0\"]},{\"id\":\"Bus #4\",\"departureTimes\":[\"2019-01-09 14:53:00.0\",\"2019-01-09 22:00:00.0\",\"2019-01-10 05:08:00.0\"]},{\"id\":\"Bus #3\",\"departureTimes\":[\"2019-01-09 14:44:00.0\",\"2019-01-09 21:47:00.0\",\"2019-01-10 04:58:00.0\"]},{\"id\":\"Bus #6\",\"departureTimes\":[\"2019-01-09 15:21:00.0\",\"2019-01-09 22:16:00.0\",\"2019-01-10 05:28:00.0\"]},{\"id\":\"Bus #5\",\"departureTimes\":[\"2019-01-09 15:02:00.0\",\"2019-01-09 22:06:00.0\",\"2019-01-10 05:18:00.0\"]},{\"id\":\"Bus #40\",\"departureTimes\":[\"2019-01-09 20:52:00.0\",\"2019-01-10 03:33:00.0\"]}]";
//                    departTimeValue = "[{\"id\":\"619\",\"departureTimes\":[\"2018-12-28 06:00:00.0\",\"2018-12-28 06:04:00.0\",\"2018-12-28 06:12:00.0\",\"2018-12-28 06:15:00.0\",\"2018-12-2807:22:00.0\",\"2018-12-2807:26:00.0\",\"2018-12-2807:33:00.0\",\"2018-12-2812:16:00.0\",\"2018-12-2812:45:00.0\",\"2018-12-2820:52:00.0\"]} ] ";
//                }
//                else if(requestBusLine.getLineNum().equals("619") &&
//                        objectivesDtos.get(0).getValue().equals("0.70") &&
//                        objectivesDtos.get(1).getValue().equals("0.20") &&
//                        objectivesDtos.get(2).getValue().equals("0.10")){
//                    hasValue = true;
//                    value = "[{\"id\":\"Bus #24\",\"departureTimes\":[\"2019-01-09 17:49:00.0\",\"2019-01-10 01:39:00.0\"]},{\"id\":\"Bus #23\",\"departureTimes\":[\"2019-01-09 17:47:00.0\",\"2019-01-10 01:33:00.0\"]},{\"id\":\"Bus #26\",\"departureTimes\":[\"2019-01-09 18:03:00.0\",\"2019-01-10 02:00:00.0\"]},{\"id\":\"Bus #25\",\"departureTimes\":[\"2019-01-09 17:55:00.0\",\"2019-01-10 01:56:00.0\"]},{\"id\":\"Bus #42\",\"departureTimes\":[\"2019-01-09 20:34:00.0\",\"2019-01-10 04:43:00.0\"]},{\"id\":\"Bus #20\",\"departureTimes\":[\"2019-01-09 17:35:00.0\",\"2019-01-10 00:55:00.0\"]},{\"id\":\"Bus #41\",\"departureTimes\":[\"2019-01-09 20:28:00.0\",\"2019-01-10 04:34:00.0\"]},{\"id\":\"Bus #44\",\"departureTimes\":[\"2019-01-09 20:54:00.0\",\"2019-01-10 05:02:00.0\"]},{\"id\":\"Bus #22\",\"departureTimes\":[\"2019-01-09 17:44:00.0\",\"2019-01-10 01:23:00.0\"]},{\"id\":\"Bus #43\",\"departureTimes\":[\"2019-01-09 20:40:00.0\",\"2019-01-10 04:52:00.0\"]},{\"id\":\"Bus #21\",\"departureTimes\":[\"2019-01-09 17:43:00.0\",\"2019-01-10 01:05:00.0\"]},{\"id\":\"Bus #28\",\"departureTimes\":[\"2019-01-09 18:33:00.0\",\"2019-01-10 02:26:00.0\"]},{\"id\":\"Bus #27\",\"departureTimes\":[\"2019-01-09 18:21:00.0\",\"2019-01-10 02:18:00.0\"]},{\"id\":\"Bus #29\",\"departureTimes\":[\"2019-01-09 18:48:00.0\",\"2019-01-10 02:44:00.0\"]},{\"id\":\"Bus #35\",\"departureTimes\":[\"2019-01-09 19:58:00.0\",\"2019-01-10 03:19:00.0\"]},{\"id\":\"Bus #13\",\"departureTimes\":[\"2019-01-09 16:11:00.0\",\"2019-01-09 23:26:00.0\"]},{\"id\":\"Bus #34\",\"departureTimes\":[\"2019-01-09 19:44:00.0\",\"2019-01-10 03:11:00.0\"]},{\"id\":\"Bus #12\",\"departureTimes\":[\"2019-01-09 15:57:00.0\",\"2019-01-09 23:25:00.0\"]},{\"id\":\"Bus #37\",\"departureTimes\":[\"2019-01-09 20:18:00.0\",\"2019-01-10 03:37:00.0\"]},{\"id\":\"Bus #15\",\"departureTimes\":[\"2019-01-09 16:35:00.0\",\"2019-01-09 23:54:00.0\"]},{\"id\":\"Bus #36\",\"departureTimes\":[\"2019-01-09 20:17:00.0\",\"2019-01-10 03:21:00.0\"]},{\"id\":\"Bus #14\",\"departureTimes\":[\"2019-01-09 16:17:00.0\",\"2019-01-09 23:38:00.0\"]},{\"id\":\"Bus #31\",\"departureTimes\":[\"2019-01-09 19:09:00.0\",\"2019-01-10 02:49:00.0\"]},{\"id\":\"Bus #30\",\"departureTimes\":[\"2019-01-09 18:58:00.0\",\"2019-01-10 02:44:00.0\"]},{\"id\":\"Bus #33\",\"departureTimes\":[\"2019-01-09 19:38:00.0\",\"2019-01-10 03:05:00.0\"]},{\"id\":\"Bus #11\",\"departureTimes\":[\"2019-01-09 15:42:00.0\",\"2019-01-09 23:09:00.0\"]},{\"id\":\"Bus #32\",\"departureTimes\":[\"2019-01-09 19:27:00.0\",\"2019-01-10 02:58:00.0\"]},{\"id\":\"Bus #10\",\"departureTimes\":[\"2019-01-09 15:23:00.0\",\"2019-01-09 22:53:00.0\"]},{\"id\":\"Bus #0\",\"departureTimes\":[\"2019-01-09 14:00:00.0\",\"2019-01-09 21:03:00.0\",\"2019-01-10 05:12:00.0\"]},{\"id\":\"Bus #2\",\"departureTimes\":[\"2019-01-09 14:24:00.0\",\"2019-01-09 21:24:00.0\",\"2019-01-10 05:32:00.0\"]},{\"id\":\"Bus #1\",\"departureTimes\":[\"2019-01-09 14:13:00.0\",\"2019-01-09 21:22:00.0\",\"2019-01-10 05:22:00.0\"]},{\"id\":\"Bus #39\",\"departureTimes\":[\"2019-01-09 20:20:00.0\",\"2019-01-10 04:11:00.0\"]},{\"id\":\"Bus #17\",\"departureTimes\":[\"2019-01-09 17:00:00.0\",\"2019-01-10 00:24:00.0\"]},{\"id\":\"Bus #38\",\"departureTimes\":[\"2019-01-09 20:18:00.0\",\"2019-01-10 03:53:00.0\"]},{\"id\":\"Bus #16\",\"departureTimes\":[\"2019-01-09 16:45:00.0\",\"2019-01-10 00:08:00.0\"]},{\"id\":\"Bus #19\",\"departureTimes\":[\"2019-01-09 17:26:00.0\",\"2019-01-10 00:51:00.0\"]},{\"id\":\"Bus #18\",\"departureTimes\":[\"2019-01-09 17:07:00.0\",\"2019-01-10 00:32:00.0\"]},{\"id\":\"Bus #8\",\"departureTimes\":[\"2019-01-09 15:05:00.0\",\"2019-01-09 22:28:00.0\"]},{\"id\":\"Bus #7\",\"departureTimes\":[\"2019-01-09 14:55:00.0\",\"2019-01-09 22:15:00.0\"]},{\"id\":\"Bus #9\",\"departureTimes\":[\"2019-01-09 15:16:00.0\",\"2019-01-09 22:34:00.0\"]},{\"id\":\"Bus #4\",\"departureTimes\":[\"2019-01-09 14:33:00.0\",\"2019-01-09 21:31:00.0\"]},{\"id\":\"Bus #3\",\"departureTimes\":[\"2019-01-09 14:29:00.0\",\"2019-01-09 21:27:00.0\",\"2019-01-10 05:40:00.0\"]},{\"id\":\"Bus #6\",\"departureTimes\":[\"2019-01-09 14:53:00.0\",\"2019-01-09 22:08:00.0\"]},{\"id\":\"Bus #5\",\"departureTimes\":[\"2019-01-09 14:38:00.0\",\"2019-01-09 21:49:00.0\"]},{\"id\":\"Bus #40\",\"departureTimes\":[\"2019-01-09 20:23:00.0\",\"2019-01-10 04:30:00.0\"]}]";
//                    departTimeValue = "[{\"id\":\"619\",\"departureTimes\":[\"2018-12-28 06:00:00.0\",\"2018-12-28 06:04:00.0\",\"2018-12-28 06:12:00.0\",\"2018-12-28 06:15:00.0\",\"2018-12-2807:22:00.0\",\"2018-12-2807:26:00.0\",\"2018-12-2807:33:00.0\",\"2018-12-2812:16:00.0\",\"2018-12-2812:45:00.0\",\"2018-12-2820:52:00.0\"]} ] ";
//                } else if(requestBusLine.getLineNum().equals("619") &&
//                        objectivesDtos.get(0).getValue().equals("0.25") &&
//                        objectivesDtos.get(1).getValue().equals("0.50") &&
//                        objectivesDtos.get(2).getValue().equals("0.25")){
//                    hasValue = true;
//                    value = "[{\"id\":\"Bus #24\",\"departureTimes\":[\"2019-01-09 17:36:00.0\",\"2019-01-10 01:13:00.0\"]},{\"id\":\"Bus #23\",\"departureTimes\":[\"2019-01-09 17:20:00.0\",\"2019-01-10 01:11:00.0\"]},{\"id\":\"Bus #26\",\"departureTimes\":[\"2019-01-09 18:03:00.0\",\"2019-01-10 01:43:00.0\"]},{\"id\":\"Bus #25\",\"departureTimes\":[\"2019-01-09 17:44:00.0\",\"2019-01-10 01:28:00.0\"]},{\"id\":\"Bus #42\",\"departureTimes\":[\"2019-01-09 21:11:00.0\",\"2019-01-10 04:19:00.0\"]},{\"id\":\"Bus #20\",\"departureTimes\":[\"2019-01-09 16:54:00.0\",\"2019-01-10 00:35:00.0\"]},{\"id\":\"Bus #41\",\"departureTimes\":[\"2019-01-09 20:54:00.0\",\"2019-01-10 04:12:00.0\"]},{\"id\":\"Bus #44\",\"departureTimes\":[\"2019-01-09 21:21:00.0\",\"2019-01-10 04:27:00.0\"]},{\"id\":\"Bus #22\",\"departureTimes\":[\"2019-01-09 17:16:00.0\",\"2019-01-10 01:09:00.0\"]},{\"id\":\"Bus #43\",\"departureTimes\":[\"2019-01-09 21:18:00.0\",\"2019-01-10 04:25:00.0\"]},{\"id\":\"Bus #21\",\"departureTimes\":[\"2019-01-09 17:03:00.0\",\"2019-01-10 00:54:00.0\"]},{\"id\":\"Bus #28\",\"departureTimes\":[\"2019-01-09 18:33:00.0\",\"2019-01-10 02:00:00.0\"]},{\"id\":\"Bus #27\",\"departureTimes\":[\"2019-01-09 18:22:00.0\",\"2019-01-10 01:59:00.0\"]},{\"id\":\"Bus #29\",\"departureTimes\":[\"2019-01-09 18:47:00.0\",\"2019-01-10 02:18:00.0\"]},{\"id\":\"Bus #35\",\"departureTimes\":[\"2019-01-09 20:06:00.0\",\"2019-01-10 03:26:00.0\"]},{\"id\":\"Bus #13\",\"departureTimes\":[\"2019-01-09 15:55:00.0\",\"2019-01-09 23:21:00.0\"]},{\"id\":\"Bus #34\",\"departureTimes\":[\"2019-01-09 19:49:00.0\",\"2019-01-10 03:09:00.0\"]},{\"id\":\"Bus #12\",\"departureTimes\":[\"2019-01-09 15:37:00.0\",\"2019-01-09 23:02:00.0\"]},{\"id\":\"Bus #37\",\"departureTimes\":[\"2019-01-09 20:19:00.0\",\"2019-01-10 03:46:00.0\"]},{\"id\":\"Bus #15\",\"departureTimes\":[\"2019-01-09 16:03:00.0\",\"2019-01-09 23:31:00.0\"]},{\"id\":\"Bus #36\",\"departureTimes\":[\"2019-01-09 20:07:00.0\",\"2019-01-10 03:27:00.0\"]},{\"id\":\"Bus #14\",\"departureTimes\":[\"2019-01-09 16:02:00.0\",\"2019-01-09 23:22:00.0\"]},{\"id\":\"Bus #31\",\"departureTimes\":[\"2019-01-09 19:18:00.0\",\"2019-01-10 02:50:00.0\"]},{\"id\":\"Bus #30\",\"departureTimes\":[\"2019-01-09 19:01:00.0\",\"2019-01-10 02:32:00.0\"]},{\"id\":\"Bus #33\",\"departureTimes\":[\"2019-01-09 19:44:00.0\",\"2019-01-10 03:08:00.0\"]},{\"id\":\"Bus #11\",\"departureTimes\":[\"2019-01-09 15:27:00.0\",\"2019-01-09 23:01:00.0\"]},{\"id\":\"Bus #32\",\"departureTimes\":[\"2019-01-09 19:29:00.0\",\"2019-01-10 03:01:00.0\"]},{\"id\":\"Bus #10\",\"departureTimes\":[\"2019-01-09 15:18:00.0\",\"2019-01-09 23:01:00.0\"]},{\"id\":\"Bus #0\",\"departureTimes\":[\"2019-01-09 14:00:00.0\",\"2019-01-09 21:35:00.0\",\"2019-01-10 04:43:00.0\"]},{\"id\":\"Bus #2\",\"departureTimes\":[\"2019-01-09 14:18:00.0\",\"2019-01-09 21:51:00.0\",\"2019-01-10 04:57:00.0\"]},{\"id\":\"Bus #1\",\"departureTimes\":[\"2019-01-09 14:14:00.0\",\"2019-01-09 21:41:00.0\",\"2019-01-10 04:52:00.0\"]},{\"id\":\"Bus #39\",\"departureTimes\":[\"2019-01-09 20:40:00.0\",\"2019-01-10 04:02:00.0\"]},{\"id\":\"Bus #17\",\"departureTimes\":[\"2019-01-09 16:27:00.0\",\"2019-01-09 23:59:00.0\"]},{\"id\":\"Bus #38\",\"departureTimes\":[\"2019-01-09 20:32:00.0\",\"2019-01-10 03:55:00.0\"]},{\"id\":\"Bus #16\",\"departureTimes\":[\"2019-01-09 16:13:00.0\",\"2019-01-09 23:41:00.0\"]},{\"id\":\"Bus #19\",\"departureTimes\":[\"2019-01-09 16:37:00.0\",\"2019-01-10 00:19:00.0\"]},{\"id\":\"Bus #18\",\"departureTimes\":[\"2019-01-09 16:37:00.0\",\"2019-01-10 00:15:00.0\"]},{\"id\":\"Bus #8\",\"departureTimes\":[\"2019-01-09 15:07:00.0\",\"2019-01-09 22:37:00.0\"]},{\"id\":\"Bus #7\",\"departureTimes\":[\"2019-01-09 15:01:00.0\",\"2019-01-09 22:36:00.0\",\"2019-01-10 05:40:00.0\"]},{\"id\":\"Bus #9\",\"departureTimes\":[\"2019-01-09 15:12:00.0\",\"2019-01-09 22:49:00.0\"]},{\"id\":\"Bus #4\",\"departureTimes\":[\"2019-01-09 14:25:00.0\",\"2019-01-09 22:11:00.0\",\"2019-01-10 05:17:00.0\"]},{\"id\":\"Bus #3\",\"departureTimes\":[\"2019-01-09 14:20:00.0\",\"2019-01-09 22:09:00.0\",\"2019-01-10 05:07:00.0\"]},{\"id\":\"Bus #6\",\"departureTimes\":[\"2019-01-09 14:44:00.0\",\"2019-01-09 22:33:00.0\",\"2019-01-10 05:37:00.0\"]},{\"id\":\"Bus #5\",\"departureTimes\":[\"2019-01-09 14:36:00.0\",\"2019-01-09 22:27:00.0\",\"2019-01-10 05:27:00.0\"]},{\"id\":\"Bus #40\",\"departureTimes\":[\"2019-01-09 20:44:00.0\",\"2019-01-10 04:11:00.0\"]}]";
//                    departTimeValue = "[{\"id\":\"619\",\"departureTimes\":[\"2018-12-28 06:00:00.0\",\"2018-12-28 06:04:00.0\",\"2018-12-28 06:12:00.0\",\"2018-12-28 06:15:00.0\",\"2018-12-2807:22:00.0\",\"2018-12-2807:26:00.0\",\"2018-12-2807:33:00.0\",\"2018-12-2812:16:00.0\",\"2018-12-2812:45:00.0\",\"2018-12-2820:52:00.0\"]} ] ";
//                }
//                else if(requestBusLine.getLineNum().equals("877") &&
//                        objectivesDtos.get(0).getValue().equals("0.32") &&
//                        objectivesDtos.get(1).getValue().equals("0.34") &&
//                        objectivesDtos.get(2).getValue().equals("0.34")){
//                    hasValue = true;
//                    value = "[{\"id\":\"Bus #24\",\"departureTimes\":[\"2019-01-09 18:32:00.0\",\"2019-01-09 23:43:00.0\",\"2019-01-10 05:06:00.0\"]},{\"id\":\"Bus #23\",\"departureTimes\":[\"2019-01-09 18:21:00.0\",\"2019-01-09 23:28:00.0\",\"2019-01-10 04:56:00.0\"]},{\"id\":\"Bus #26\",\"departureTimes\":[\"2019-01-09 18:50:00.0\",\"2019-01-09 23:56:00.0\",\"2019-01-10 05:26:00.0\"]},{\"id\":\"Bus #25\",\"departureTimes\":[\"2019-01-09 18:48:00.0\",\"2019-01-09 23:43:00.0\",\"2019-01-10 05:16:00.0\"]},{\"id\":\"Bus #20\",\"departureTimes\":[\"2019-01-09 17:57:00.0\",\"2019-01-09 23:06:00.0\",\"2019-01-10 04:42:00.0\"]},{\"id\":\"Bus #22\",\"departureTimes\":[\"2019-01-09 18:19:00.0\",\"2019-01-09 23:28:00.0\",\"2019-01-10 04:46:00.0\"]},{\"id\":\"Bus #21\",\"departureTimes\":[\"2019-01-09 18:08:00.0\",\"2019-01-09 23:09:00.0\",\"2019-01-10 04:42:00.0\"]},{\"id\":\"Bus #28\",\"departureTimes\":[\"2019-01-09 19:18:00.0\",\"2019-01-10 00:24:00.0\"]},{\"id\":\"Bus #27\",\"departureTimes\":[\"2019-01-09 19:03:00.0\",\"2019-01-10 00:14:00.0\",\"2019-01-10 05:36:00.0\"]},{\"id\":\"Bus #29\",\"departureTimes\":[\"2019-01-09 19:22:00.0\",\"2019-01-10 00:29:00.0\"]},{\"id\":\"Bus #13\",\"departureTimes\":[\"2019-01-09 16:44:00.0\",\"2019-01-09 22:16:00.0\",\"2019-01-10 03:45:00.0\"]},{\"id\":\"Bus #34\",\"departureTimes\":[\"2019-01-09 20:16:00.0\",\"2019-01-10 01:21:00.0\"]},{\"id\":\"Bus #12\",\"departureTimes\":[\"2019-01-09 16:43:00.0\",\"2019-01-09 22:13:00.0\",\"2019-01-10 03:39:00.0\"]},{\"id\":\"Bus #15\",\"departureTimes\":[\"2019-01-09 17:06:00.0\",\"2019-01-09 22:40:00.0\",\"2019-01-10 04:02:00.0\"]},{\"id\":\"Bus #14\",\"departureTimes\":[\"2019-01-09 17:00:00.0\",\"2019-01-09 22:23:00.0\",\"2019-01-10 03:47:00.0\"]},{\"id\":\"Bus #31\",\"departureTimes\":[\"2019-01-09 19:45:00.0\",\"2019-01-10 00:37:00.0\"]},{\"id\":\"Bus #30\",\"departureTimes\":[\"2019-01-09 19:32:00.0\",\"2019-01-10 00:29:00.0\"]},{\"id\":\"Bus #33\",\"departureTimes\":[\"2019-01-09 20:13:00.0\",\"2019-01-10 01:13:00.0\"]},{\"id\":\"Bus #11\",\"departureTimes\":[\"2019-01-09 16:34:00.0\",\"2019-01-09 22:00:00.0\",\"2019-01-10 03:28:00.0\"]},{\"id\":\"Bus #32\",\"departureTimes\":[\"2019-01-09 19:59:00.0\",\"2019-01-10 00:55:00.0\"]},{\"id\":\"Bus #10\",\"departureTimes\":[\"2019-01-09 16:21:00.0\",\"2019-01-09 21:58:00.0\",\"2019-01-10 03:11:00.0\"]},{\"id\":\"Bus #0\",\"departureTimes\":[\"2019-01-09 14:00:00.0\",\"2019-01-09 20:32:00.0\",\"2019-01-10 01:30:00.0\"]},{\"id\":\"Bus #2\",\"departureTimes\":[\"2019-01-09 14:37:00.0\",\"2019-01-09 20:40:00.0\",\"2019-01-10 01:47:00.0\"]},{\"id\":\"Bus #1\",\"departureTimes\":[\"2019-01-09 14:18:00.0\",\"2019-01-09 20:36:00.0\",\"2019-01-10 01:33:00.0\"]},{\"id\":\"Bus #17\",\"departureTimes\":[\"2019-01-09 17:27:00.0\",\"2019-01-09 22:47:00.0\",\"2019-01-10 04:18:00.0\"]},{\"id\":\"Bus #16\",\"departureTimes\":[\"2019-01-09 17:22:00.0\",\"2019-01-09 22:46:00.0\",\"2019-01-10 04:18:00.0\"]},{\"id\":\"Bus #19\",\"departureTimes\":[\"2019-01-09 17:52:00.0\",\"2019-01-09 23:05:00.0\",\"2019-01-10 04:37:00.0\"]},{\"id\":\"Bus #18\",\"departureTimes\":[\"2019-01-09 17:36:00.0\",\"2019-01-09 22:54:00.0\",\"2019-01-10 04:35:00.0\"]},{\"id\":\"Bus #8\",\"departureTimes\":[\"2019-01-09 16:08:00.0\",\"2019-01-09 21:44:00.0\",\"2019-01-10 02:54:00.0\"]},{\"id\":\"Bus #7\",\"departureTimes\":[\"2019-01-09 15:50:00.0\",\"2019-01-09 21:41:00.0\",\"2019-01-10 02:50:00.0\"]},{\"id\":\"Bus #9\",\"departureTimes\":[\"2019-01-09 16:14:00.0\",\"2019-01-09 21:55:00.0\",\"2019-01-10 03:03:00.0\"]},{\"id\":\"Bus #4\",\"departureTimes\":[\"2019-01-09 15:12:00.0\",\"2019-01-09 20:51:00.0\",\"2019-01-10 02:16:00.0\"]},{\"id\":\"Bus #3\",\"departureTimes\":[\"2019-01-09 14:55:00.0\",\"2019-01-09 20:46:00.0\",\"2019-01-10 02:00:00.0\"]},{\"id\":\"Bus #6\",\"departureTimes\":[\"2019-01-09 15:39:00.0\",\"2019-01-09 21:22:00.0\",\"2019-01-10 02:43:00.0\"]},{\"id\":\"Bus #5\",\"departureTimes\":[\"2019-01-09 15:30:00.0\",\"2019-01-09 21:03:00.0\",\"2019-01-10 02:34:00.0\"]}]";
//                    departTimeValue = "[{\"id\":\"877\",\"departureTimes\":[\"2018-12-28 06:00:00.0\",\"2018-12-28 06:04:00.0\",\"2018-12-28 06:12:00.0\",\"2018-12-28 06:15:00.0\",\"2018-12-2807:22:00.0\",\"2018-12-2807:26:00.0\",\"2018-12-2807:33:00.0\",\"2018-12-2812:16:00.0\",\"2018-12-2812:45:00.0\",\"2018-12-2820:52:00.0\"]} ] ";
//                }
//                else if(requestBusLine.getLineNum().equals("877") &&
//                        objectivesDtos.get(0).getValue().equals("0.10") &&
//                        objectivesDtos.get(1).getValue().equals("0.10") &&
//                        objectivesDtos.get(2).getValue().equals("0.80")){
//                    hasValue = true;
//                    value = "[{\"id\":\"Bus #24\",\"departureTimes\":[\"2019-01-09 17:56:00.0\",\"2019-01-09 23:31:00.0\",\"2019-01-10 05:06:00.0\"]},{\"id\":\"Bus #23\",\"departureTimes\":[\"2019-01-09 17:49:00.0\",\"2019-01-09 23:21:00.0\",\"2019-01-10 04:56:00.0\"]},{\"id\":\"Bus #26\",\"departureTimes\":[\"2019-01-09 18:15:00.0\",\"2019-01-10 00:04:00.0\",\"2019-01-10 05:26:00.0\"]},{\"id\":\"Bus #25\",\"departureTimes\":[\"2019-01-09 18:06:00.0\",\"2019-01-09 23:50:00.0\",\"2019-01-10 05:16:00.0\"]},{\"id\":\"Bus #20\",\"departureTimes\":[\"2019-01-09 17:16:00.0\",\"2019-01-09 22:56:00.0\",\"2019-01-10 04:23:00.0\"]},{\"id\":\"Bus #22\",\"departureTimes\":[\"2019-01-09 17:41:00.0\",\"2019-01-09 23:11:00.0\",\"2019-01-10 04:39:00.0\"]},{\"id\":\"Bus #21\",\"departureTimes\":[\"2019-01-09 17:30:00.0\",\"2019-01-09 22:57:00.0\",\"2019-01-10 04:35:00.0\"]},{\"id\":\"Bus #28\",\"departureTimes\":[\"2019-01-09 18:34:00.0\",\"2019-01-10 00:20:00.0\",\"2019-01-10 05:40:00.0\"]},{\"id\":\"Bus #27\",\"departureTimes\":[\"2019-01-09 18:17:00.0\",\"2019-01-10 00:05:00.0\",\"2019-01-10 05:36:00.0\"]},{\"id\":\"Bus #29\",\"departureTimes\":[\"2019-01-09 18:40:00.0\",\"2019-01-10 00:32:00.0\"]},{\"id\":\"Bus #13\",\"departureTimes\":[\"2019-01-09 16:06:00.0\",\"2019-01-09 21:56:00.0\",\"2019-01-10 03:20:00.0\"]},{\"id\":\"Bus #34\",\"departureTimes\":[\"2019-01-09 19:37:00.0\",\"2019-01-10 01:30:00.0\"]},{\"id\":\"Bus #12\",\"departureTimes\":[\"2019-01-09 16:02:00.0\",\"2019-01-09 21:43:00.0\",\"2019-01-10 03:08:00.0\"]},{\"id\":\"Bus #15\",\"departureTimes\":[\"2019-01-09 16:19:00.0\",\"2019-01-09 22:14:00.0\",\"2019-01-10 03:36:00.0\"]},{\"id\":\"Bus #14\",\"departureTimes\":[\"2019-01-09 16:10:00.0\",\"2019-01-09 22:00:00.0\",\"2019-01-10 03:32:00.0\"]},{\"id\":\"Bus #31\",\"departureTimes\":[\"2019-01-09 19:02:00.0\",\"2019-01-10 00:56:00.0\"]},{\"id\":\"Bus #30\",\"departureTimes\":[\"2019-01-09 18:58:00.0\",\"2019-01-10 00:37:00.0\"]},{\"id\":\"Bus #33\",\"departureTimes\":[\"2019-01-09 19:29:00.0\",\"2019-01-10 01:15:00.0\"]},{\"id\":\"Bus #11\",\"departureTimes\":[\"2019-01-09 15:48:00.0\",\"2019-01-09 21:35:00.0\",\"2019-01-10 02:59:00.0\"]},{\"id\":\"Bus #32\",\"departureTimes\":[\"2019-01-09 19:14:00.0\",\"2019-01-10 01:13:00.0\"]},{\"id\":\"Bus #10\",\"departureTimes\":[\"2019-01-09 15:29:00.0\",\"2019-01-09 21:19:00.0\",\"2019-01-10 02:51:00.0\"]},{\"id\":\"Bus #0\",\"departureTimes\":[\"2019-01-09 14:00:00.0\",\"2019-01-09 19:41:00.0\",\"2019-01-10 01:49:00.0\"]},{\"id\":\"Bus #2\",\"departureTimes\":[\"2019-01-09 14:22:00.0\",\"2019-01-09 19:51:00.0\",\"2019-01-10 01:57:00.0\"]},{\"id\":\"Bus #1\",\"departureTimes\":[\"2019-01-09 14:05:00.0\",\"2019-01-09 19:44:00.0\",\"2019-01-10 01:49:00.0\"]},{\"id\":\"Bus #17\",\"departureTimes\":[\"2019-01-09 16:37:00.0\",\"2019-01-09 22:35:00.0\",\"2019-01-10 03:58:00.0\"]},{\"id\":\"Bus #16\",\"departureTimes\":[\"2019-01-09 16:29:00.0\",\"2019-01-09 22:26:00.0\",\"2019-01-10 03:50:00.0\"]},{\"id\":\"Bus #19\",\"departureTimes\":[\"2019-01-09 17:04:00.0\",\"2019-01-09 22:41:00.0\",\"2019-01-10 04:12:00.0\"]},{\"id\":\"Bus #18\",\"departureTimes\":[\"2019-01-09 16:47:00.0\",\"2019-01-09 22:35:00.0\",\"2019-01-10 04:08:00.0\"]},{\"id\":\"Bus #8\",\"departureTimes\":[\"2019-01-09 15:08:00.0\",\"2019-01-09 20:56:00.0\",\"2019-01-10 02:40:00.0\"]},{\"id\":\"Bus #7\",\"departureTimes\":[\"2019-01-09 14:57:00.0\",\"2019-01-09 20:38:00.0\",\"2019-01-10 02:40:00.0\"]},{\"id\":\"Bus #9\",\"departureTimes\":[\"2019-01-09 15:15:00.0\",\"2019-01-09 21:13:00.0\",\"2019-01-10 02:40:00.0\"]},{\"id\":\"Bus #4\",\"departureTimes\":[\"2019-01-09 14:47:00.0\",\"2019-01-09 20:01:00.0\",\"2019-01-10 02:10:00.0\"]},{\"id\":\"Bus #3\",\"departureTimes\":[\"2019-01-09 14:32:00.0\",\"2019-01-09 19:52:00.0\",\"2019-01-10 02:00:00.0\"]},{\"id\":\"Bus #6\",\"departureTimes\":[\"2019-01-09 14:56:00.0\",\"2019-01-09 20:31:00.0\",\"2019-01-10 02:30:00.0\"]},{\"id\":\"Bus #5\",\"departureTimes\":[\"2019-01-09 14:49:00.0\",\"2019-01-09 20:19:00.0\",\"2019-01-10 02:14:00.0\"]}]";
//                    departTimeValue = "[{\"id\":\"877\",\"departureTimes\":[\"2018-12-28 06:00:00.0\",\"2018-12-28 06:04:00.0\",\"2018-12-28 06:12:00.0\",\"2018-12-28 06:15:00.0\",\"2018-12-2807:22:00.0\",\"2018-12-2807:26:00.0\",\"2018-12-2807:33:00.0\",\"2018-12-2812:16:00.0\",\"2018-12-2812:45:00.0\",\"2018-12-2820:52:00.0\"]} ] ";
//                }
//                else if(requestBusLine.getLineNum().equals("877") &&
//                        objectivesDtos.get(0).getValue().equals("0.70") &&
//                        objectivesDtos.get(1).getValue().equals("0.20") &&
//                        objectivesDtos.get(2).getValue().equals("0.10")){
//                    hasValue = true;
//                    value = "[{\"id\":\"Bus #24\",\"departureTimes\":[\"2019-01-09 18:23:00.0\",\"2019-01-09 23:47:00.0\",\"2019-01-10 04:30:00.0\"]},{\"id\":\"Bus #23\",\"departureTimes\":[\"2019-01-09 18:15:00.0\",\"2019-01-09 23:29:00.0\",\"2019-01-10 04:22:00.0\"]},{\"id\":\"Bus #26\",\"departureTimes\":[\"2019-01-09 18:38:00.0\",\"2019-01-09 23:53:00.0\",\"2019-01-10 04:44:00.0\"]},{\"id\":\"Bus #25\",\"departureTimes\":[\"2019-01-09 18:34:00.0\",\"2019-01-09 23:50:00.0\",\"2019-01-10 04:30:00.0\"]},{\"id\":\"Bus #20\",\"departureTimes\":[\"2019-01-09 17:23:00.0\",\"2019-01-09 23:15:00.0\",\"2019-01-10 03:57:00.0\"]},{\"id\":\"Bus #22\",\"departureTimes\":[\"2019-01-09 17:56:00.0\",\"2019-01-09 23:26:00.0\",\"2019-01-10 04:11:00.0\"]},{\"id\":\"Bus #21\",\"departureTimes\":[\"2019-01-09 17:39:00.0\",\"2019-01-09 23:16:00.0\",\"2019-01-10 04:07:00.0\"]},{\"id\":\"Bus #28\",\"departureTimes\":[\"2019-01-09 19:08:00.0\",\"2019-01-10 00:02:00.0\",\"2019-01-10 05:04:00.0\"]},{\"id\":\"Bus #27\",\"departureTimes\":[\"2019-01-09 18:51:00.0\",\"2019-01-09 23:59:00.0\",\"2019-01-10 04:54:00.0\"]},{\"id\":\"Bus #29\",\"departureTimes\":[\"2019-01-09 19:20:00.0\",\"2019-01-10 00:21:00.0\",\"2019-01-10 05:14:00.0\"]},{\"id\":\"Bus #13\",\"departureTimes\":[\"2019-01-09 15:52:00.0\",\"2019-01-09 22:11:00.0\",\"2019-01-10 03:05:00.0\"]},{\"id\":\"Bus #34\",\"departureTimes\":[\"2019-01-09 19:59:00.0\",\"2019-01-10 01:13:00.0\"]},{\"id\":\"Bus #12\",\"departureTimes\":[\"2019-01-09 15:48:00.0\",\"2019-01-09 22:05:00.0\",\"2019-01-10 02:59:00.0\"]},{\"id\":\"Bus #15\",\"departureTimes\":[\"2019-01-09 16:18:00.0\",\"2019-01-09 22:30:00.0\",\"2019-01-10 03:20:00.0\"]},{\"id\":\"Bus #14\",\"departureTimes\":[\"2019-01-09 16:10:00.0\",\"2019-01-09 22:13:00.0\",\"2019-01-10 03:07:00.0\"]},{\"id\":\"Bus #31\",\"departureTimes\":[\"2019-01-09 19:46:00.0\",\"2019-01-10 00:41:00.0\",\"2019-01-10 05:34:00.0\"]},{\"id\":\"Bus #30\",\"departureTimes\":[\"2019-01-09 19:28:00.0\",\"2019-01-10 00:23:00.0\",\"2019-01-10 05:24:00.0\"]},{\"id\":\"Bus #33\",\"departureTimes\":[\"2019-01-09 19:59:00.0\",\"2019-01-10 01:09:00.0\"]},{\"id\":\"Bus #11\",\"departureTimes\":[\"2019-01-09 15:36:00.0\",\"2019-01-09 22:00:00.0\",\"2019-01-10 02:54:00.0\"]},{\"id\":\"Bus #32\",\"departureTimes\":[\"2019-01-09 19:48:00.0\",\"2019-01-10 00:56:00.0\"]},{\"id\":\"Bus #10\",\"departureTimes\":[\"2019-01-09 15:36:00.0\",\"2019-01-09 21:49:00.0\",\"2019-01-10 02:37:00.0\"]},{\"id\":\"Bus #0\",\"departureTimes\":[\"2019-01-09 14:00:00.0\",\"2019-01-09 19:59:00.0\",\"2019-01-10 01:15:00.0\"]},{\"id\":\"Bus #2\",\"departureTimes\":[\"2019-01-09 14:04:00.0\",\"2019-01-09 20:28:00.0\",\"2019-01-10 01:19:00.0\"]},{\"id\":\"Bus #1\",\"departureTimes\":[\"2019-01-09 14:00:00.0\",\"2019-01-09 20:14:00.0\",\"2019-01-10 01:15:00.0\"]},{\"id\":\"Bus #17\",\"departureTimes\":[\"2019-01-09 16:52:00.0\",\"2019-01-09 22:50:00.0\",\"2019-01-10 03:25:00.0\"]},{\"id\":\"Bus #16\",\"departureTimes\":[\"2019-01-09 16:34:00.0\",\"2019-01-09 22:31:00.0\",\"2019-01-10 03:22:00.0\"]},{\"id\":\"Bus #19\",\"departureTimes\":[\"2019-01-09 17:13:00.0\",\"2019-01-09 23:07:00.0\",\"2019-01-10 03:43:00.0\"]},{\"id\":\"Bus #18\",\"departureTimes\":[\"2019-01-09 16:56:00.0\",\"2019-01-09 22:53:00.0\",\"2019-01-10 03:27:00.0\"]},{\"id\":\"Bus #8\",\"departureTimes\":[\"2019-01-09 15:03:00.0\",\"2019-01-09 21:29:00.0\",\"2019-01-10 02:25:00.0\"]},{\"id\":\"Bus #7\",\"departureTimes\":[\"2019-01-09 15:01:00.0\",\"2019-01-09 21:18:00.0\",\"2019-01-10 02:12:00.0\"]},{\"id\":\"Bus #9\",\"departureTimes\":[\"2019-01-09 15:18:00.0\",\"2019-01-09 21:48:00.0\",\"2019-01-10 02:25:00.0\"]},{\"id\":\"Bus #4\",\"departureTimes\":[\"2019-01-09 14:20:00.0\",\"2019-01-09 20:54:00.0\",\"2019-01-10 01:41:00.0\"]},{\"id\":\"Bus #3\",\"departureTimes\":[\"2019-01-09 14:06:00.0\",\"2019-01-09 20:44:00.0\",\"2019-01-10 01:28:00.0\"]},{\"id\":\"Bus #6\",\"departureTimes\":[\"2019-01-09 14:48:00.0\",\"2019-01-09 21:04:00.0\",\"2019-01-10 02:04:00.0\"]},{\"id\":\"Bus #5\",\"departureTimes\":[\"2019-01-09 14:29:00.0\",\"2019-01-09 21:00:00.0\",\"2019-01-10 01:52:00.0\"]}]";
//                    departTimeValue = "[{\"id\":\"877\",\"departureTimes\":[\"2018-12-28 06:00:00.0\",\"2018-12-28 06:04:00.0\",\"2018-12-28 06:12:00.0\",\"2018-12-28 06:15:00.0\",\"2018-12-2807:22:00.0\",\"2018-12-2807:26:00.0\",\"2018-12-2807:33:00.0\",\"2018-12-2812:16:00.0\",\"2018-12-2812:45:00.0\",\"2018-12-2820:52:00.0\"]} ] ";
//                }else if(requestBusLine.getLineNum().equals("877") &&
//                        objectivesDtos.get(0).getValue().equals("0.25") &&
//                        objectivesDtos.get(1).getValue().equals("0.50") &&
//                        objectivesDtos.get(2).getValue().equals("0.25")){
//                    hasValue = true;
//                    value = "[{\"id\":\"Bus #24\",\"departureTimes\":[\"2019-01-09 17:18:00.0\",\"2019-01-09 23:02:00.0\",\"2019-01-10 04:43:00.0\"]},{\"id\":\"Bus #23\",\"departureTimes\":[\"2019-01-09 17:17:00.0\",\"2019-01-09 22:51:00.0\",\"2019-01-10 04:35:00.0\"]},{\"id\":\"Bus #26\",\"departureTimes\":[\"2019-01-09 17:36:00.0\",\"2019-01-09 23:38:00.0\",\"2019-01-10 04:58:00.0\"]},{\"id\":\"Bus #25\",\"departureTimes\":[\"2019-01-09 17:36:00.0\",\"2019-01-09 23:21:00.0\",\"2019-01-10 04:48:00.0\"]},{\"id\":\"Bus #20\",\"departureTimes\":[\"2019-01-09 16:53:00.0\",\"2019-01-09 22:32:00.0\",\"2019-01-10 04:14:00.0\"]},{\"id\":\"Bus #22\",\"departureTimes\":[\"2019-01-09 17:00:00.0\",\"2019-01-09 22:44:00.0\",\"2019-01-10 04:28:00.0\"]},{\"id\":\"Bus #21\",\"departureTimes\":[\"2019-01-09 16:55:00.0\",\"2019-01-09 22:36:00.0\",\"2019-01-10 04:24:00.0\"]},{\"id\":\"Bus #28\",\"departureTimes\":[\"2019-01-09 18:02:00.0\",\"2019-01-10 00:05:00.0\",\"2019-01-10 05:18:00.0\"]},{\"id\":\"Bus #27\",\"departureTimes\":[\"2019-01-09 17:53:00.0\",\"2019-01-09 23:52:00.0\",\"2019-01-10 05:08:00.0\"]},{\"id\":\"Bus #29\",\"departureTimes\":[\"2019-01-09 18:08:00.0\",\"2019-01-10 00:21:00.0\",\"2019-01-10 05:28:00.0\"]},{\"id\":\"Bus #13\",\"departureTimes\":[\"2019-01-09 15:51:00.0\",\"2019-01-09 21:17:00.0\",\"2019-01-10 02:54:00.0\"]},{\"id\":\"Bus #34\",\"departureTimes\":[\"2019-01-09 18:43:00.0\",\"2019-01-10 01:05:00.0\"]},{\"id\":\"Bus #12\",\"departureTimes\":[\"2019-01-09 15:46:00.0\",\"2019-01-09 21:08:00.0\",\"2019-01-10 02:54:00.0\"]},{\"id\":\"Bus #15\",\"departureTimes\":[\"2019-01-09 16:20:00.0\",\"2019-01-09 21:34:00.0\",\"2019-01-10 03:04:00.0\"]},{\"id\":\"Bus #14\",\"departureTimes\":[\"2019-01-09 16:03:00.0\",\"2019-01-09 21:23:00.0\",\"2019-01-10 03:01:00.0\"]},{\"id\":\"Bus #31\",\"departureTimes\":[\"2019-01-09 18:21:00.0\",\"2019-01-10 00:40:00.0\"]},{\"id\":\"Bus #30\",\"departureTimes\":[\"2019-01-09 18:15:00.0\",\"2019-01-10 00:31:00.0\",\"2019-01-10 05:30:00.0\"]},{\"id\":\"Bus #33\",\"departureTimes\":[\"2019-01-09 18:40:00.0\",\"2019-01-10 00:55:00.0\"]},{\"id\":\"Bus #11\",\"departureTimes\":[\"2019-01-09 15:42:00.0\",\"2019-01-09 20:56:00.0\",\"2019-01-10 02:54:00.0\"]},{\"id\":\"Bus #32\",\"departureTimes\":[\"2019-01-09 18:29:00.0\",\"2019-01-10 00:43:00.0\"]},{\"id\":\"Bus #10\",\"departureTimes\":[\"2019-01-09 15:27:00.0\",\"2019-01-09 20:56:00.0\",\"2019-01-10 02:48:00.0\"]},{\"id\":\"Bus #0\",\"departureTimes\":[\"2019-01-09 14:00:00.0\",\"2019-01-09 18:53:00.0\",\"2019-01-10 01:10:00.0\"]},{\"id\":\"Bus #2\",\"departureTimes\":[\"2019-01-09 14:18:00.0\",\"2019-01-09 19:12:00.0\",\"2019-01-10 01:34:00.0\"]},{\"id\":\"Bus #1\",\"departureTimes\":[\"2019-01-09 14:03:00.0\",\"2019-01-09 18:54:00.0\",\"2019-01-10 01:15:00.0\"]},{\"id\":\"Bus #17\",\"departureTimes\":[\"2019-01-09 16:33:00.0\",\"2019-01-09 21:54:00.0\",\"2019-01-10 03:41:00.0\"]},{\"id\":\"Bus #16\",\"departureTimes\":[\"2019-01-09 16:31:00.0\",\"2019-01-09 21:46:00.0\",\"2019-01-10 03:22:00.0\"]},{\"id\":\"Bus #19\",\"departureTimes\":[\"2019-01-09 16:52:00.0\",\"2019-01-09 22:20:00.0\",\"2019-01-10 04:07:00.0\"]},{\"id\":\"Bus #18\",\"departureTimes\":[\"2019-01-09 16:35:00.0\",\"2019-01-09 22:08:00.0\",\"2019-01-10 03:58:00.0\"]},{\"id\":\"Bus #8\",\"departureTimes\":[\"2019-01-09 15:06:00.0\",\"2019-01-09 20:26:00.0\",\"2019-01-10 02:26:00.0\"]},{\"id\":\"Bus #7\",\"departureTimes\":[\"2019-01-09 14:59:00.0\",\"2019-01-09 20:14:00.0\",\"2019-01-10 02:16:00.0\"]},{\"id\":\"Bus #9\",\"departureTimes\":[\"2019-01-09 15:08:00.0\",\"2019-01-09 20:43:00.0\",\"2019-01-10 02:41:00.0\"]},{\"id\":\"Bus #4\",\"departureTimes\":[\"2019-01-09 14:37:00.0\",\"2019-01-09 19:43:00.0\",\"2019-01-10 01:52:00.0\"]},{\"id\":\"Bus #3\",\"departureTimes\":[\"2019-01-09 14:18:00.0\",\"2019-01-09 19:29:00.0\",\"2019-01-10 01:34:00.0\"]},{\"id\":\"Bus #6\",\"departureTimes\":[\"2019-01-09 14:44:00.0\",\"2019-01-09 20:03:00.0\",\"2019-01-10 02:12:00.0\"]},{\"id\":\"Bus #5\",\"departureTimes\":[\"2019-01-09 14:38:00.0\",\"2019-01-09 19:58:00.0\",\"2019-01-10 02:08:00.0\"]}]";
//                    departTimeValue = "[{\"id\":\"877\",\"departureTimes\":[\"2018-12-28 06:00:00.0\",\"2018-12-28 06:04:00.0\",\"2018-12-28 06:12:00.0\",\"2018-12-28 06:15:00.0\",\"2018-12-2807:22:00.0\",\"2018-12-2807:26:00.0\",\"2018-12-2807:33:00.0\",\"2018-12-2812:16:00.0\",\"2018-12-2812:45:00.0\",\"2018-12-2820:52:00.0\"]} ] ";
//                }
//            }

            String result = null;
            if(hasValue){
                result = departTimeValue;
            }else{
                result = schedule_requests(requestId);
//                while (result.trim().length()==0){
//                    try {
//                        Thread.sleep(500);
//                        result = schedule_requests(requestId);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
            System.out.println(">>>result:"+result);
            JSONObject jsonObject = JSONObject.fromObject(result);

            while(jsonObject.get("data").equals("null")){
                if(jsonObject.get("code").equals(500)){
                    break;
                }
                result = schedule_requests(requestId);
                jsonObject = JSONObject.fromObject(result);
                //5分钟
                Thread.sleep(300000);
                //30秒
//                 Thread.sleep(30000);
            }

            if(!jsonObject.get("code").equals(200)){
                TableList tableList = new TableList();
                tableList.setCode(jsonObject.getInt("code"));
                tableList.setError(jsonObject.getString("message"));
                tableListList.add(tableList);
            }else{
                JSONArray array = JSONArray.fromObject(jsonObject.get("data"));
                List<SchedulesResult> schedulesResultList = JSONArray.toList(array, new SchedulesResult(), new JsonConfig());
//                schedulesResultList.get(0).setId(requestBusLine.getLineNum());
//            getDepartTimes(requestId, tableListList, hasValue, value, schedulesResultList);
                getDepartTimesNew(requestId, tableListList, hasValue, value, schedulesResultList);
            }
            return tableListList;
        }catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 获取公交车发班信息
     * @param requestId
     * @param tableListList
     * @param hasValue
     * @param value
     * @param schedulesResultList
     * @throws ParseException
     */
    private void getDepartTimes(@PathVariable String requestId, List<TableList> tableListList, boolean hasValue, String value, List<SchedulesResult> schedulesResultList) throws ParseException {
        JSONArray array;
        if(schedulesResultList != null){

            SchedulesResult schedulesResult_v = schedulesResultList.get(0);
            String lineRange = "";
            String StartStation = "";
            String busList = null;
            if(hasValue){
                busList = value;
            }else{
                busList = schedule_requests_bus(requestId);
            }
            System.out.println(">>>busList:"+busList);

            JSONObject jsonObject = JSONObject.fromObject(busList);
            TableList tableList = new TableList();
            List<TableDomain> tableDomainList = new ArrayList<>();
            if(jsonObject.getInt("code") != 200){
                tableList.setCode(jsonObject.getInt("code"));
                tableList.setError(jsonObject.getString("message"));
            }else{
                array = JSONArray.fromObject(jsonObject.get("data"));
                schedulesResultList = JSONArray.toList(array, new SchedulesResult(), new JsonConfig());
                //排序
                Collections.sort(schedulesResultList,new SchedulesResult.Str2IntComparator(true));
                for (SchedulesResult schedulesResult:schedulesResultList) {
                    TableDomain tableDomain = new TableDomain();
                    tableDomain.setCode(schedulesResult.getId());
                    if(schedulesResult_v.getId().equals("619")){
                        lineRange = "观音桥东环路--空港新城";
                        StartStation = "观音桥东环路";
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==1){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                        }
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==2){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp2(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(1),"yyyy-MM-dd HH:mm:ss",0));
                        }
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==3){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp2(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(1),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp3(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(2),"yyyy-MM-dd HH:mm:ss",0));
                        }
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==4){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp2(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(1),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp3(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(2),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp4(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(3),"yyyy-MM-dd HH:mm:ss",0));
                        }
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==5){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp2(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(1),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp3(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(2),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp4(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(3),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp5(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(4),"yyyy-MM-dd HH:mm:ss",0));
                        }
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==6){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp2(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(1),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp3(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(2),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp4(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(3),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp5(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(4),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp6(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(5),"yyyy-MM-dd HH:mm:ss",0));
                        }
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==7){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp2(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(1),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp3(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(2),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp4(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(3),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp5(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(4),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp6(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(5),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp7(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(6),"yyyy-MM-dd HH:mm:ss",0));
                        }
                    }else if(schedulesResult_v.getId().equals("877")){
                        lineRange = "空港新城—红旗河沟枢纽站";
                        StartStation = "空港新城";
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==1){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                        }
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==2){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp2(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(1),"yyyy-MM-dd HH:mm:ss",0));
                        }
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==3){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp2(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(1),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp3(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(2),"yyyy-MM-dd HH:mm:ss",0));
                        }
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==4){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp2(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(1),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp3(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(2),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp4(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(3),"yyyy-MM-dd HH:mm:ss",0));
                        }
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==5){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp2(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(1),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp3(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(2),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp4(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(3),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp5(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(4),"yyyy-MM-dd HH:mm:ss",0));
                        }
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==6){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp2(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(1),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp3(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(2),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp4(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(3),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp5(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(4),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp6(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(5),"yyyy-MM-dd HH:mm:ss",0));
                        }
                        if(schedulesResult.getDepartureTimes() != null && schedulesResult.getDepartureTimes().size() ==7){
                            tableDomain.setUp1(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(0),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp2(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(1),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp3(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(2),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp4(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(3),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp5(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(4),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp6(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(5),"yyyy-MM-dd HH:mm:ss",0));
                            tableDomain.setUp7(TimeUtils.UTCToCST(schedulesResult.getDepartureTimes().get(6),"yyyy-MM-dd HH:mm:ss",0));
                        }
                    }
                    tableDomain.setLineRange(lineRange);
                    tableDomain.setStartStation(StartStation);
                    tableDomainList.add(tableDomain);

                }
            }


            tableList.setAaData(tableDomainList);
            tableList.setiTotalDisplayRecords(schedulesResultList.size());
            tableList.setiTotalRecords(schedulesResultList.size());
            tableList.setLineNum(schedulesResult_v.getId());
            tableListList.add(tableList);
        }
    }


    /**
     * 获取公交车发班信息 新接口
     * @param requestId
     * @param tableListList
     * @param hasValue
     * @param value
     * @param schedulesResultList
     * @throws ParseException
     */
    int perfectTotal = 0;
    //总车辆数
    int total_bus_num = 0;
    private void getDepartTimesNew(@PathVariable String requestId, List<TableList> tableListList, boolean hasValue, String value, List<SchedulesResult> schedulesResultList) throws ParseException, InterruptedException {
        JSONArray array;
        if(schedulesResultList != null){

//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SchedulesResult schedulesResult_v = schedulesResultList.get(0);
            String lineRange = "";
            String StartStation = "";
            String busList = null;
            String startDate = null;
            String endDate = null;
            if(hasValue){
                busList = value;
            }else{
                busList = schedule_requests_bus(requestId);
            }
            System.out.println(">>>busList:"+busList);

            JSONObject jsonObject = JSONObject.fromObject(busList);


//            while (jsonObject.get("data").equals("null")){
//                busList = schedule_requests_bus(requestId);
//                jsonObject = JSONObject.fromObject(busList);
//                Thread.sleep(500);
//                System.out.println("-------------------获取排班信息......-----------------------");
//            }

            //每辆公交车运行的趟数
            Map<String,Integer> busrun_count = new HashMap();

            TableList tableList = new TableList();
            List<TableDomain> tableDomainList = new ArrayList<>();
            if(jsonObject.getInt("code") != 200){
                tableList.setCode(jsonObject.getInt("code"));
                tableList.setError(jsonObject.getString("message"));
            }else{
                System.out.println(jsonObject.get("data"));
                array = JSONArray.fromObject(jsonObject.get("data"));
                List<Bus> busList1 = JSONArray.toList(array, new Bus(), new JsonConfig());
                //排序
//            Collections.sort(schedulesResultList,new SchedulesResult.Str2IntComparator(true));
                //筛选公交车编码
                Set<String> busid_set = new HashSet<>();
                for (Bus bus:busList1) {
                    busid_set.add(bus.getBusId());
                }

                //将busId 与公交信息进行对应
                Map<String,List<Bus>> map_bus_list = new HashMap<>();
                for (String busid: busid_set) {
                    if(map_bus_list.get(busid) == null){
                        map_bus_list.put(busid,new ArrayList<Bus>());
                    }
                    for (Bus bus:busList1) {
                        if(bus.getBusId().equals(busid)){
                            map_bus_list.get(busid).add(bus);
                        }
                    }
                }

                Map<String,Object[]> map_bb = new HashMap<>();
                List<Bus> busList2 = null;
                List<Bus> bus_shift;
                Object[] obj = null;
                int range_count = 0;
                Set<String> busids = map_bus_list.keySet();
                for (String busid:busids) {
                    busList2 = map_bus_list.get(busid);
                    //将公交信息按照发车班次倒序排
                    Collections.sort(busList2,new Bus.ShiftComparator(true));

//                if(busList2.get(0).getShift() / 2 > 0){
//                    range_count = busList2.get(0).getShift() / 2+1;
//                }else{
//                    range_count = busList2.get(0).getShift() / 2;
//                }
                    range_count = busList2.get(0).getShift();

                    obj = new Object[range_count];

                    int max_index = 0;
                    //升序排序
                    Collections.sort(busList2,new Bus.ShiftComparator(false));
                    for (int i = 0; i < range_count; i++) {
                        bus_shift = new ArrayList<>();
                        for (Bus bus:busList2) {
                            if(bus.getShift() == i+1){
                                bus_shift.add(bus);
                            }
                        }
                        //max_index = (i+1)*2;
                        obj[i] = bus_shift;
                    }
                    map_bb.put(busid,obj);
                }


                busids = map_bb.keySet();
                int busCountSn = 0;
                for (String busid:busids) {

                    busCountSn++;

                    TableDomain tableDomain = new TableDomain();
                    tableDomain.setCode(busid);
                    tableDomain.setSn(busCountSn+"");

                    if(schedulesResult_v.getId().equals("619")) {
//                        lineRange = "观音桥东环路--空港新城";
//                        StartStation = "观音桥东环路";

                        lineRange = "空港新城--观音桥东环路";
                        StartStation = "空港新城";
                    }else if(schedulesResult_v.getId().equals("877")){
                        lineRange = "空港新城—红旗河沟枢纽站";
                        StartStation = "空港新城";
                    }

                    busrun_count.put(busid,0);

                    Object[] obj_bus_list = map_bb.get(busid);
                    boolean isempty = false;
                    for (int i = 0; i < obj_bus_list.length; i++) {
                        List<Bus> buses = (List<Bus>) obj_bus_list[i];

                        //圈1
                        if(i == 0){
                            if(buses.size() >=2){

                                Date date1 = sdf.parse(buses.get(0).getDepartureTime());
                                Date date2 = sdf.parse(buses.get(1).getDepartureTime());
                                int compareTo = date1.compareTo(date2);
                                System.out.println("compare"+compareTo);
                                if(compareTo > 0){
                                    if(buses.get(0).getShift() == buses.get(1).getShift()){
                                        if(schedulesResult_v.getId().equals("619")) {
//                                            lineRange = "空港新城--观音桥东环路";
//                                            StartStation = "空港新城";

                                            lineRange = "观音桥东环路--空港新城";
                                            StartStation = "观音桥东环路";
                                        }else if(schedulesResult_v.getId().equals("877")){
                                            lineRange = "红旗河沟枢纽站--空港新城";
                                            StartStation = "红旗河沟枢纽站";
                                        }

                                        tableDomain.setUp1(buses.get(1).getDepartureTime().substring(11));
                                        tableDomain.setDown1(buses.get(0).getDepartureTime().substring(11));
                                    }else{
                                        isempty = true;
                                        tableDomain.setDown1(buses.get(1).getDepartureTime().substring(11));
                                    }
                                }else{
                                    tableDomain.setUp1(buses.get(0).getDepartureTime().substring(11));
                                    tableDomain.setDown1(buses.get(1).getDepartureTime().substring(11));
                                }
//                            tableDomain.setUp1(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
//                            tableDomain.setDown1(TimeUtils.UTCToCST(buses.get(1).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));

                                busrun_count.put(busid,busrun_count.get(busid)+2);

                            }else if(buses.size() >=1){
//                            tableDomain.setUp1(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
                                if(buses.get(0).getDirection() == 0){
                                    tableDomain.setUp1(buses.get(0).getDepartureTime().substring(11));
                                }else{
                                    if(isempty){
                                        tableDomain.setUp1(((List<Bus>) obj_bus_list[i-1]).get(0).getDepartureTime().substring(11));
                                    }
                                    tableDomain.setDown1(buses.get(0).getDepartureTime().substring(11));
                                }

                                busrun_count.put(busid,busrun_count.get(busid)+1);
                            }
                        }

                        //圈2
                        if(i == 1){
                            if(buses.size() >=2){
//                            tableDomain.setUp2(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
//                            tableDomain.setDown2(TimeUtils.UTCToCST(buses.get(1).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
                                if(isempty){
                                    tableDomain.setUp2(((List<Bus>) obj_bus_list[i-1]).get(0).getDepartureTime().substring(11));
                                    tableDomain.setDown2(buses.get(1).getDepartureTime().substring(11));
                                }else{
                                    Date date1 = sdf.parse(buses.get(0).getDepartureTime());
                                    Date date2 = sdf.parse(buses.get(1).getDepartureTime());
                                    int compareTo = date1.compareTo(date2);
                                    if(compareTo > 0){
                                        tableDomain.setUp2(buses.get(1).getDepartureTime().substring(11));
                                        tableDomain.setDown2(buses.get(0).getDepartureTime().substring(11));
                                    }else{
                                        tableDomain.setUp2(buses.get(0).getDepartureTime().substring(11));
                                        tableDomain.setDown2(buses.get(1).getDepartureTime().substring(11));
                                    }
                                }

                                busrun_count.put(busid,busrun_count.get(busid)+2);
                            }else if(buses.size() >=1){
//                            tableDomain.setUp2(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
                                if(buses.get(0).getDirection() == 0){
                                    tableDomain.setUp2(buses.get(0).getDepartureTime().substring(11));
                                }else{
                                    if(isempty){
                                        tableDomain.setUp2(((List<Bus>) obj_bus_list[i-1]).get(0).getDepartureTime().substring(11));
                                    }
                                    tableDomain.setDown2(buses.get(0).getDepartureTime().substring(11));
                                }
                                busrun_count.put(busid,busrun_count.get(busid)+1);
                            }
                        }

                        //圈3
                        if(i == 2){
                            if(buses.size() >=2){
//                            tableDomain.setUp3(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
//                            tableDomain.setDown3(TimeUtils.UTCToCST(buses.get(1).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
                                if(isempty){
                                    tableDomain.setUp3(((List<Bus>) obj_bus_list[i-1]).get(0).getDepartureTime().substring(11));
                                    tableDomain.setDown3(buses.get(1).getDepartureTime().substring(11));
                                }else{
                                    Date date1 = sdf.parse(buses.get(0).getDepartureTime());
                                    Date date2 = sdf.parse(buses.get(1).getDepartureTime());
                                    int compareTo = date1.compareTo(date2);
                                    if(compareTo > 0){
                                        tableDomain.setUp3(buses.get(1).getDepartureTime().substring(11));
                                        tableDomain.setDown3(buses.get(0).getDepartureTime().substring(11));
                                    }else{
                                        tableDomain.setUp3(buses.get(0).getDepartureTime().substring(11));
                                        tableDomain.setDown3(buses.get(1).getDepartureTime().substring(11));
                                    }
                                }

                                busrun_count.put(busid,busrun_count.get(busid)+2);
                            }else if(buses.size() >=1){
//                            tableDomain.setUp3(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
                                if(buses.get(0).getDirection() == 0){
                                    tableDomain.setUp3(buses.get(0).getDepartureTime().substring(11));
                                }else{
                                    if(isempty){
                                        tableDomain.setUp3(((List<Bus>) obj_bus_list[i-1]).get(0).getDepartureTime().substring(11));
                                    }
                                    tableDomain.setDown3(buses.get(0).getDepartureTime().substring(11));
                                }
                                busrun_count.put(busid,busrun_count.get(busid)+1);
                            }
                        }

                        //圈4
                        if(i == 3){
                            if(buses.size() >=2){
//                            tableDomain.setUp4(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
//                            tableDomain.setDown4(TimeUtils.UTCToCST(buses.get(1).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
                                if(isempty){
                                    tableDomain.setUp4(((List<Bus>) obj_bus_list[i-1]).get(0).getDepartureTime().substring(11));
                                    tableDomain.setDown4(buses.get(1).getDepartureTime().substring(11));
                                }else{
                                    Date date1 = sdf.parse(buses.get(0).getDepartureTime());
                                    Date date2 = sdf.parse(buses.get(1).getDepartureTime());
                                    int compareTo = date1.compareTo(date2);
                                    if(compareTo > 0){
                                        tableDomain.setUp4(buses.get(1).getDepartureTime().substring(11));
                                        tableDomain.setDown4(buses.get(0).getDepartureTime().substring(11));
                                    }else{
                                        tableDomain.setUp4(buses.get(0).getDepartureTime().substring(11));
                                        tableDomain.setDown4(buses.get(1).getDepartureTime().substring(11));
                                    }
                                }

                                busrun_count.put(busid,busrun_count.get(busid)+2);
                            }else if(buses.size() >=1){
//                            tableDomain.setUp4(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
                                if(buses.get(0).getDirection() == 0){
                                    tableDomain.setUp4(buses.get(0).getDepartureTime().substring(11));
                                }else{
                                    if(isempty){
                                        tableDomain.setUp4(((List<Bus>) obj_bus_list[i-1]).get(0).getDepartureTime().substring(11));
                                    }
                                    tableDomain.setDown4(buses.get(0).getDepartureTime().substring(11));
                                }

                                busrun_count.put(busid,busrun_count.get(busid)+1);
                            }
                        }

                        //圈5
                        if(i == 4){
                            if(buses.size() >=2){
//                            tableDomain.setUp5(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
//                            tableDomain.setDown5(TimeUtils.UTCToCST(buses.get(1).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
                                if(isempty){
                                    tableDomain.setUp5(((List<Bus>) obj_bus_list[i-1]).get(0).getDepartureTime().substring(11));
                                    tableDomain.setDown5(buses.get(1).getDepartureTime().substring(11));
                                }else{
                                    Date date1 = sdf.parse(buses.get(0).getDepartureTime());
                                    Date date2 = sdf.parse(buses.get(1).getDepartureTime());
                                    int compareTo = date1.compareTo(date2);
                                    if(compareTo > 0){
                                        tableDomain.setUp5(buses.get(1).getDepartureTime().substring(11));
                                        tableDomain.setDown5(buses.get(0).getDepartureTime().substring(11));
                                    }else{
                                        tableDomain.setUp5(buses.get(0).getDepartureTime().substring(11));
                                        tableDomain.setDown5(buses.get(1).getDepartureTime().substring(11));
                                    }
                                }

                                busrun_count.put(busid,busrun_count.get(busid)+2);
                            }else if(buses.size() >=1){
//                            tableDomain.setUp5(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
                                if(buses.get(0).getDirection() == 0){
                                    tableDomain.setUp5(buses.get(0).getDepartureTime().substring(11));
                                }else{
                                    if(isempty){
                                        tableDomain.setUp5(((List<Bus>) obj_bus_list[i-1]).get(0).getDepartureTime().substring(11));
                                    }
                                    tableDomain.setDown5(buses.get(0).getDepartureTime().substring(11));
                                }

                                busrun_count.put(busid,busrun_count.get(busid)+1);
                            }
                        }

                        //圈6
                        if(i == 5){
                            if(buses.size() >=2){
//                            tableDomain.setUp6(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
//                            tableDomain.setDown6(TimeUtils.UTCToCST(buses.get(1).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
                                if(isempty){
                                    tableDomain.setUp6(((List<Bus>) obj_bus_list[i-1]).get(0).getDepartureTime().substring(11));
                                    tableDomain.setDown6(buses.get(1).getDepartureTime().substring(11));
                                }else{
                                    Date date1 = sdf.parse(buses.get(0).getDepartureTime());
                                    Date date2 = sdf.parse(buses.get(1).getDepartureTime());
                                    int compareTo = date1.compareTo(date2);
                                    if(compareTo > 0){
                                        tableDomain.setUp6(buses.get(1).getDepartureTime().substring(11));
                                        tableDomain.setDown6(buses.get(0).getDepartureTime().substring(11));
                                    }else{
                                        tableDomain.setUp6(buses.get(0).getDepartureTime().substring(11));
                                        tableDomain.setDown6(buses.get(1).getDepartureTime().substring(11));
                                    }
                                }

                                busrun_count.put(busid,busrun_count.get(busid)+2);
                            }else if(buses.size() >=1){
//                            tableDomain.setUp6(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
                                if(buses.get(0).getDirection() == 0){
                                    tableDomain.setUp6(buses.get(0).getDepartureTime().substring(11));
                                }else{
                                    if(isempty){
                                        tableDomain.setUp6(((List<Bus>) obj_bus_list[i-1]).get(0).getDepartureTime().substring(11));
                                    }
                                    tableDomain.setDown6(buses.get(0).getDepartureTime().substring(11));
                                }

                                busrun_count.put(busid,busrun_count.get(busid)+1);
                            }
                        }

                        //圈7
                        if(i == 6){
                            if(buses.size() >=2){
//                            tableDomain.setUp7(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
//                            tableDomain.setDown7(TimeUtils.UTCToCST(buses.get(1).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
                                if(isempty){
                                    tableDomain.setUp7(((List<Bus>) obj_bus_list[i-1]).get(0).getDepartureTime().substring(11));
                                    tableDomain.setDown7(buses.get(1).getDepartureTime().substring(11));
                                }else{
                                    Date date1 = sdf.parse(buses.get(0).getDepartureTime());
                                    Date date2 = sdf.parse(buses.get(1).getDepartureTime());
                                    int compareTo = date1.compareTo(date2);
                                    if(compareTo > 0){
                                        tableDomain.setUp7(buses.get(1).getDepartureTime().substring(11));
                                        tableDomain.setDown7(buses.get(0).getDepartureTime().substring(11));
                                    }else{
                                        tableDomain.setUp7(buses.get(0).getDepartureTime().substring(11));
                                        tableDomain.setDown7(buses.get(1).getDepartureTime().substring(11));
                                    }
                                }

                                busrun_count.put(busid,busrun_count.get(busid)+2);
                            }else if(buses.size() >=1){
//                            tableDomain.setUp7(TimeUtils.UTCToCST(buses.get(0).getDepartureTime(),"yyyy-MM-dd HH:mm:ss",0));
                                if(buses.get(0).getDirection() == 0){
                                    tableDomain.setUp7(buses.get(0).getDepartureTime().substring(11));
                                }else{
                                    if(isempty){
                                        tableDomain.setUp7(((List<Bus>) obj_bus_list[i-1]).get(0).getDepartureTime().substring(11));
                                    }
                                    tableDomain.setDown7(buses.get(0).getDepartureTime().substring(11));
                                }

                                busrun_count.put(busid,busrun_count.get(busid)+1);
                            }
                        }
                    }
                    tableDomain.setLineRange(lineRange);
                    tableDomain.setStartStation(StartStation);
                    tableDomainList.add(tableDomain);

                }


            }

//            int max = 4;
//
//            //总车辆数
//            total_bus_num = tableDomainList.size();
//
//            int busrun_has_six = 0;
//            Set<String> busrun_count_keys = busrun_count.keySet();
//            Map<String,Integer> busrun_count_les_six = new HashMap<>();
//            for (String busrun_key: busrun_count_keys) {
//                if(busrun_count.get(busrun_key) >=max){
//                    busrun_has_six++;
//                }else{
//                    busrun_count_les_six.put(busrun_key,busrun_count.get(busrun_key));
//                }
//            }
//
//            //进行优化
//            if(Double.parseDouble(busrun_has_six+"")/Double.parseDouble(total_bus_num+"") < 0.5){
//                BusRunMerge(max, busrun_count_les_six,0,busrun_has_six);
//            }else{
//                perfectTotal = total_bus_num;
//            }

            //计算最佳车辆数
            try {
                RequestBusLine requestBusLine = (RequestBusLine) request.getSession().getAttribute("requestBusLine");
                List<ObjectivesDto> objectivesDtoList = requestBusLine.getObjectivesDtos();
                if(objectivesDtoList != null && objectivesDtoList.size() >0){

                    if(requestBusLine.getLineNum().equals("619")){
                        startDate = sdf_ymd.format(new Date())+" 5:50:00";
                        endDate = sdf_ymd.format(new Date())+" 20:30:00";
                    }else if(requestBusLine.getLineNum().equals("877")){
                        startDate = sdf_ymd.format(new Date())+" 6:30:00";
                        endDate = sdf_ymd.format(new Date())+" 19:30:00";
                    }

                    int ts = 6;
                    int hours = DateUtil.getDiffHoures(sdf.parse(startDate),sdf.parse(endDate));
                    System.out.println(hours);
                    NumberFormat nf = NumberFormat.getNumberInstance();
                    //保留整数
                    nf.setMaximumFractionDigits(0);
                    //如果不需要四舍五入，可以使用RoundingMode.DOWN
                    nf.setRoundingMode(RoundingMode.UP);
                    String result = nf.format(hours * 60 / (2+(requestBusLine.getMaxDispatchGap()+requestBusLine.getMinDispatchGap())/6)
                            /ts * Math.sqrt(((getObjectivesDtoValue(objectivesDtoList,"WAITING_TIME")+getObjectivesDtoValue(objectivesDtoList,"LOAD_FACTOR")+1)/(getObjectivesDtoValue(objectivesDtoList,"OPERATION_COST")+1))));
                    System.out.println(result);
                    perfectTotal = Integer.parseInt(result);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            tableList.setPerfectTotal(perfectTotal);

            tableList.setAaData(tableDomainList);
            tableList.setiTotalDisplayRecords(schedulesResultList.size());
            tableList.setiTotalRecords(schedulesResultList.size());
            tableList.setLineNum(schedulesResult_v.getId());
            tableList.setCode(200);
            tableListList.add(tableList);
        }
    }


    private double getObjectivesDtoValue(List<ObjectivesDto> objectivesDtoList,String key){
        double a = 0;
        for (ObjectivesDto objectivesDto : objectivesDtoList) {
            if(objectivesDto.getObjectiveType().equals(key)){
                Double.parseDouble(objectivesDto.getValue());
                break;
            }
        }
        return a;
    }

    /**
     * 将少于最大趟数的车辆合并成为最大车辆
     * @param max 最大趟数
     * @param busrun_count_les_six 少于最大趟数的车辆
     * @param busrun_index 趟数增加的量
     */
    private void BusRunMerge(int max, Map<String, Integer> busrun_count_les_six,int busrun_index,int busrun_has_six) {
        //趟数未满6趟的 进行升倒叙排
        Map<String,Integer> busrun_count_les_six_max = MapSort.sortMapByValue(busrun_count_les_six,true);
        Set<String> les_keys = busrun_count_les_six_max.keySet();
        //趟数最大值
        int les_max = busrun_count_les_six_max.get(les_keys.iterator().next());
        Set<String> max_lesssss = new HashSet<>();
        //趟数最大值的车辆数
        int les_max_count = 0;
        for (String les_keyy: les_keys) {
            if(busrun_count_les_six_max.get(les_keyy) == les_max){
                les_max_count++;
                max_lesssss.add(les_keyy);
            }
        }

        //趟数最小值
        int les_min = max -les_max;
        les_min += busrun_index;

        boolean isFinish = false;
        //趟数最小值的车辆数
        Set<String> min_lesssss = new HashSet<>();
        int les_min_count = 0;
        for (String les_keyy: les_keys) {
            if(busrun_count_les_six_max.get(les_keyy) == les_min){
                les_min_count++;
                min_lesssss.add(les_keyy);
            }
        }

        if(les_min_count == 0){
            //没有匹配的最小车辆
            busrun_index++;
            BusRunMerge(max,busrun_count_les_six,busrun_index,busrun_has_six);
        }else{

            if(les_max == les_min){
                int megetcount = les_max_count / (les_max/(max -les_max));
                busrun_has_six += (max_lesssss.size()-megetcount);
                perfectTotal = busrun_has_six;

            }else{

                //合并个数
                int megetcount = les_min*les_min_count/(max -les_max);
                if(megetcount > les_max_count){
                    int indexsaved = 0;
                    int saved_sum = megetcount-les_max_count;
                    int saved_count = 0;
                    if(saved_sum % les_min != 0){
                        saved_count = saved_sum / les_min+1;
                    }else{
                        saved_count = saved_sum / les_min;
                    }

                    //移除最大
                    for (String max_les: max_lesssss) {
                        busrun_count_les_six.remove(max_les);
                    }

                    //移除最小
                    for (String min_les: min_lesssss) {
                        if(indexsaved > saved_count){
                            busrun_count_les_six.remove(min_les);
                        }
                        indexsaved++;
                    }

                    busrun_has_six += max_lesssss.size();
                    perfectTotal = busrun_has_six;
                }else if(megetcount < les_max_count){
                    int indexsaved = 0;
                    //移除最大
                    for (String max_les: max_lesssss) {
                        if(indexsaved < megetcount){
                            busrun_count_les_six.remove(max_les);
                        }
                        indexsaved++;
                    }
                    //移除最小
                    for (String min_les: min_lesssss) {
                        busrun_count_les_six.remove(min_les);
                    }

                    busrun_has_six += megetcount;
                    perfectTotal = busrun_has_six;
                }else if(megetcount == les_max_count){
                    //移除最大
                    for (String max_les: max_lesssss) {
                            busrun_count_les_six.remove(max_les);
                    }
                    //移除最小
                    for (String min_les: min_lesssss) {
                        busrun_count_les_six.remove(min_les);
                    }

                    busrun_has_six += (busrun_count_les_six_max.size()-les_min_count);
                    perfectTotal = busrun_has_six;
                }
                if(busrun_count_les_six.size() > 0){
                    if(Double.parseDouble(perfectTotal+"")/Double.parseDouble(total_bus_num+"") < 0.5){
                        busrun_index = 0;
                        BusRunMerge(max,busrun_count_les_six,busrun_index,busrun_has_six);
                    }else{
                        perfectTotal+= busrun_count_les_six.size();
                    }
                }
            }
        }
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     * @throws Exception
     */
    private static String doPost(String url, String param,String postType)
    {
        PrintWriter out = null;
        OutputStreamWriter osw;
        BufferedReader in = null;
        String result = "";
        try
        {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod(postType);
//		conn.setRequestProperty("Content-Type",
//				"application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Type",
                    " application/json; encoding=utf-8");
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);

            if (param != null && !param.trim().equals(""))
            {
                // 获取URLConnection对象对应的输出流
//			out = new PrintWriter(conn.getOutputStream());
                //设置字符格式
//			out.format("utf-8", null);
                // 发送请求参数
//			out.print(param);
                // flush输出流的缓冲
//			out.flush();

                osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
                osw.write(param.toString().toCharArray(), 0, param.toString().length());
                osw.flush();
                osw.close();

            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
            System.out.println("response:"+result);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    private static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            if(param != null && !param.isEmpty()){
                urlNameString = url + "?" + param;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println("response:" + result);
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 根据权重获取请求值
     * @param requestBusLine
     */
    private String schedules(RequestBusLine requestBusLine){

        StringBuilder sb = new StringBuilder();
        List<ObjectivesDto> objectivesDtoList = requestBusLine.getObjectivesDtos();
        sb.append(requestBusLine.getLineNum())
                .append(requestBusLine.getNumberOfBuses())
                .append(requestBusLine.getMinDispatchGap())
                .append(requestBusLine.getMaxDispatchGap())
                .append(requestBusLine.getNumberOfUplinkBuses())
                .append(requestBusLine.getNumberOfDownlinkBuses())
                .append(requestBusLine.getStartTime())
                .append(requestBusLine.getEndTime())
                .append(objectivesDtoList.get(0).getValue())
                .append(objectivesDtoList.get(1).getValue())
                .append(objectivesDtoList.get(2).getValue());

        String requestId = null;

        //通过请求参数查询request 是否已经保存
        BusDepartRequest busDepartRequest_response = busDepartRequestDao.getRequestByRequestParamet(sb.toString());
        if(busDepartRequest_response == null || busDepartRequest_response.getRequestId().isEmpty()){
            String str="http://localhost:8080/v1/schedules/"+requestBusLine.getLineNum();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("numberOfBuses",requestBusLine.getNumberOfBuses());
            jsonObject.put("minDispatchGap",requestBusLine.getMinDispatchGap());
            jsonObject.put("maxDispatchGap",requestBusLine.getMaxDispatchGap());
            jsonObject.put("numberOfUplinkBuses",requestBusLine.getNumberOfUplinkBuses());
            jsonObject.put("numberOfDownlinkBuses",requestBusLine.getNumberOfDownlinkBuses());
            jsonObject.put("startTime",requestBusLine.getStartTime());
            jsonObject.put("endTime",requestBusLine.getEndTime());

            JSONArray jsonArray = new JSONArray();

            for (ObjectivesDto objectivesDto: requestBusLine.getObjectivesDtos()) {
                JSONObject jsonObject_objectives = new JSONObject();
                jsonObject_objectives.put("objectiveType",objectivesDto.getObjectiveType());
                jsonObject_objectives.put("weight",objectivesDto.getValue());
                jsonArray.add(jsonObject_objectives);
            }

            jsonObject.put("objectives",jsonArray);
            System.out.println(">>>schedules:"+jsonObject.toString());
            requestId = doPost(str,jsonObject.toString(),"POST");
            System.out.println(">>>requestId:"+requestId);


            //保存请求参数与requestId 的对应关系
            BusDepartRequest busDepartRequest = new BusDepartRequest();
            busDepartRequest.setRequestParamet(sb.toString());
            busDepartRequest.setRequestId(requestId);
            busDepartRequestDao.save(busDepartRequest);
        }else{
            requestId = busDepartRequest_response.getRequestId();
        }

        return requestId;
    }


    /**
     * 根据请求值获取发车时间信息
     */
    private static String schedule_requests(String requestId){
        String str="http://localhost:8080/v1/schedule-requests/"+requestId;
        return sendGet(str,null);
    }

    /**
     * 获取发车时间列表
     */
    private static String  schedule_requests_bus(String requestId){
        String str="http://localhost:8080/v1/schedule-requests/"+requestId+"/by-bus";
        return sendGet(str,null);
    }


    /**
     * 根据公交线路好获取线路信息
     * @param route
     * @return
     */
    private static String  analysis_route(String route){
        String str="http://localhost:7070/v1/analysis/route?route="+route;
        return sendGet(str,null);
    }

    /**
     *根据线路、日期、时间模式，查询发车班次和客流量
     * @param route
     * @param data
     * @param resolution
     * @return
     */
    public static String  analysis_agg(String route,String data,String resolution){
        String str="http://127.0.0.1:7070/v1/analysis/agg?route="+route+"&date="+data+"&resolution="+resolution;
        return sendGet(str,null);
    }


    /**
     * 根据线路和时间统计总的发车数量和刷卡数量
     * @param route
     * @param data
     * @param days
     * @return
     */
    private static String  analysis_agg_d(String route,String data,int days){
        String str="http://127.0.0.1:7070/v1/analysis/agg-d?route="+route+"&date="+data+"&days="+days;
        return sendGet(str,null);
    }

    public static void main(String[] org){
        schedule_requests("575");
    }

}
