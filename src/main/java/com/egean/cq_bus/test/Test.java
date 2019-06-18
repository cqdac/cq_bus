package com.egean.cq_bus.test;

import com.egean.cq_bus.domain.SchedulesResult;
import com.egean.cq_bus.utils.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.io.*;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {


    private static final int TIMEOUT_IN_MILLIONS = 300000;


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
    public static String sendGet(String url, String param) {
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

    public static void main(String[] var){
    //根据机构编码查询区划级数
//    String str = "http://localhost:8985/CqBus/schedules/xxxxx";
//    String str = "http://localhost:8985/CqBus/schedules";
//    UUID uuid = UUID.randomUUID();
//    JSONArray jsonArray = new JSONArray();
//    JSONObject jsonObject = new JSONObject();
//    //等待时间
//    jsonObject.put("objectiveType","WAITING_TIME");
//    jsonObject.put("value", "0.33");
//    jsonArray.add(jsonObject);
//
//    jsonObject = new JSONObject();
//    //负荷系数
//    jsonObject.put("objectiveType","LOAD_FACTOR");
//    jsonObject.put("value", "0.33");
//    jsonArray.add(jsonObject);
//
//    jsonObject = new JSONObject();
//    //运营成本
//    jsonObject.put("objectiveType","OPERATION_COST");
//    jsonObject.put("value", "0.34");
//    jsonArray.add(jsonObject);
//    String json = jsonArray.toString();
//    System.out.println(json);
//    doPost(str,json,"POST");

//        List<String> list = new ArrayList<>();
//        for(int i =0;i<10;i++){
//            list.add(String.valueOf(i));
//        }
//        list.parallelStream().forEach(l ->{
//            try {
//                Thread.sleep(1000);
//                System.out.println(l+"     "+System.currentTimeMillis());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

//        String requestId = schedules();
//        System.out.println(requestId);

        String requestId = "29";
//        String result = schedule_requests(requestId);
//        while (result.trim().length()==0){
//            try {
//                Thread.sleep(500);
//                result = schedule_requests(requestId);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        schedule_requests_bus(requestId);

//        JSONArray array = JSONArray.fromObject("[{\"id\":\"Bus #24\",\"departureTimes\":[\"2018-12-28 16:58:00.0\",\"2018-12-28 22:39:00.0\"]},{\"id\":\"Bus #23\",\"departureTimes\":[\"2018-12-28 16:50:00.0\",\"2018-12-28 22:36:00.0\"]},{\"id\":\"Bus #26\",\"departureTimes\":[\"2018-12-28 17:14:00.0\",\"2018-12-28 23:10:00.0\"]},{\"id\":\"Bus #25\",\"departureTimes\":[\"2018-12-28 17:12:00.0\",\"2018-12-28 22:58:00.0\"]},{\"id\":\"Bus #42\",\"departureTimes\":[\"2018-12-28 19:02:00.0\",\"2018-12-29 01:59:00.0\"]},{\"id\":\"Bus #20\",\"departureTimes\":[\"2018-12-28 16:40:00.0\",\"2018-12-28 22:26:00.0\"]},{\"id\":\"Bus #41\",\"departureTimes\":[\"2018-12-28 18:54:00.0\",\"2018-12-29 01:46:00.0\"]},{\"id\":\"Bus #44\",\"departureTimes\":[\"2018-12-28 19:28:00.0\",\"2018-12-29 02:17:00.0\"]},{\"id\":\"Bus #22\",\"departureTimes\":[\"2018-12-28 16:50:00.0\",\"2018-12-28 22:35:00.0\"]},{\"id\":\"Bus #43\",\"departureTimes\":[\"2018-12-28 19:09:00.0\",\"2018-12-29 02:08:00.0\"]},{\"id\":\"Bus #21\",\"departureTimes\":[\"2018-12-28 16:46:00.0\",\"2018-12-28 22:31:00.0\"]},{\"id\":\"Bus #28\",\"departureTimes\":[\"2018-12-28 17:38:00.0\",\"2018-12-28 23:35:00.0\"]},{\"id\":\"Bus #27\",\"departureTimes\":[\"2018-12-28 17:23:00.0\",\"2018-12-28 23:18:00.0\"]},{\"id\":\"Bus #29\",\"departureTimes\":[\"2018-12-28 17:43:00.0\",\"2018-12-28 23:48:00.0\"]},{\"id\":\"Bus #35\",\"departureTimes\":[\"2018-12-28 18:22:00.0\",\"2018-12-29 01:15:00.0\"]},{\"id\":\"Bus #13\",\"departureTimes\":[\"2018-12-28 15:50:00.0\",\"2018-12-28 21:23:00.0\",\"2018-12-29 04:40:00.0\"]},{\"id\":\"Bus #34\",\"departureTimes\":[\"2018-12-28 18:15:00.0\",\"2018-12-29 00:59:00.0\"]},{\"id\":\"Bus #12\",\"departureTimes\":[\"2018-12-28 15:49:00.0\",\"2018-12-28 21:14:00.0\",\"2018-12-29 04:25:00.0\"]},{\"id\":\"Bus #37\",\"departureTimes\":[\"2018-12-28 18:31:00.0\",\"2018-12-29 01:32:00.0\"]},{\"id\":\"Bus #15\",\"departureTimes\":[\"2018-12-28 15:56:00.0\",\"2018-12-28 21:32:00.0\"]},{\"id\":\"Bus #36\",\"departureTimes\":[\"2018-12-28 18:31:00.0\",\"2018-12-29 01:20:00.0\"]},{\"id\":\"Bus #14\",\"departureTimes\":[\"2018-12-28 15:53:00.0\",\"2018-12-28 21:29:00.0\",\"2018-12-29 04:52:00.0\"]},{\"id\":\"Bus #31\",\"departureTimes\":[\"2018-12-28 17:52:00.0\",\"2018-12-29 00:26:00.0\"]},{\"id\":\"Bus #30\",\"departureTimes\":[\"2018-12-28 17:50:00.0\",\"2018-12-29 00:07:00.0\"]},{\"id\":\"Bus #33\",\"departureTimes\":[\"2018-12-28 18:04:00.0\",\"2018-12-29 00:49:00.0\"]},{\"id\":\"Bus #11\",\"departureTimes\":[\"2018-12-28 15:40:00.0\",\"2018-12-28 21:10:00.0\",\"2018-12-29 04:15:00.0\"]},{\"id\":\"Bus #32\",\"departureTimes\":[\"2018-12-28 18:02:00.0\",\"2018-12-29 00:34:00.0\"]},{\"id\":\"Bus #10\",\"departureTimes\":[\"2018-12-28 15:33:00.0\",\"2018-12-28 21:04:00.0\",\"2018-12-29 03:57:00.0\"]},{\"id\":\"Bus #0\",\"departureTimes\":[\"2018-12-28 14:00:00.0\",\"2018-12-28 19:36:00.0\",\"2018-12-29 02:22:00.0\"]},{\"id\":\"Bus #2\",\"departureTimes\":[\"2018-12-28 14:27:00.0\",\"2018-12-28 19:44:00.0\",\"2018-12-29 02:33:00.0\"]},{\"id\":\"Bus #1\",\"departureTimes\":[\"2018-12-28 14:08:00.0\",\"2018-12-28 19:42:00.0\",\"2018-12-29 02:33:00.0\"]},{\"id\":\"Bus #39\",\"departureTimes\":[\"2018-12-28 18:48:00.0\",\"2018-12-29 01:41:00.0\"]},{\"id\":\"Bus #17\",\"departureTimes\":[\"2018-12-28 16:06:00.0\",\"2018-12-28 21:54:00.0\"]},{\"id\":\"Bus #38\",\"departureTimes\":[\"2018-12-28 18:40:00.0\",\"2018-12-29 01:35:00.0\"]},{\"id\":\"Bus #16\",\"departureTimes\":[\"2018-12-28 16:05:00.0\",\"2018-12-28 21:44:00.0\"]},{\"id\":\"Bus #19\",\"departureTimes\":[\"2018-12-28 16:24:00.0\",\"2018-12-28 22:11:00.0\"]},{\"id\":\"Bus #18\",\"departureTimes\":[\"2018-12-28 16:24:00.0\",\"2018-12-28 21:56:00.0\"]},{\"id\":\"Bus #8\",\"departureTimes\":[\"2018-12-28 15:21:00.0\",\"2018-12-28 20:34:00.0\",\"2018-12-29 03:42:00.0\"]},{\"id\":\"Bus #7\",\"departureTimes\":[\"2018-12-28 15:05:00.0\",\"2018-12-28 20:22:00.0\",\"2018-12-29 03:42:00.0\"]},{\"id\":\"Bus #9\",\"departureTimes\":[\"2018-12-28 15:29:00.0\",\"2018-12-28 20:48:00.0\",\"2018-12-29 03:50:00.0\"]},{\"id\":\"Bus #4\",\"departureTimes\":[\"2018-12-28 14:42:00.0\",\"2018-12-28 19:48:00.0\",\"2018-12-29 02:50:00.0\"]},{\"id\":\"Bus #3\",\"departureTimes\":[\"2018-12-28 14:28:00.0\",\"2018-12-28 19:46:00.0\",\"2018-12-29 02:47:00.0\"]},{\"id\":\"Bus #6\",\"departureTimes\":[\"2018-12-28 14:49:00.0\",\"2018-12-28 20:19:00.0\",\"2018-12-29 03:25:00.0\"]},{\"id\":\"Bus #5\",\"departureTimes\":[\"2018-12-28 14:48:00.0\",\"2018-12-28 20:04:00.0\",\"2018-12-29 03:06:00.0\"]},{\"id\":\"Bus #40\",\"departureTimes\":[\"2018-12-28 18:52:00.0\",\"2018-12-29 01:46:00.0\"]}]");
//        List<SchedulesResult> schedulesResultList = JSONArray.toList(array, new SchedulesResult(), new JsonConfig());
//        Collections.sort(schedulesResultList,new SchedulesResult.Str2IntComparator(true));
//        for (SchedulesResult schedulesResult:schedulesResultList) {
//            System.out.println("busId:"+schedulesResult.getId()+">>"+Arrays.toString(schedulesResult.getDepartureTimes().toArray())+">>"+schedulesResult.getDepartureTimes().size());
//        }
//        System.out.println(3/2);
//
//        double min = 0.8;//最小值
//        double max = 1.2;//总和
//        int scl =  1;//小数最大位数
//        int pow = (int) Math.pow(10, scl);//指定小数位
//        double one = Math.floor((Math.random() * (max - min) + min) * pow) / pow;
//
//        System.out.println(pow);
//        System.out.println(one);
//
//        System.out.println(Double.parseDouble("891")/Double.parseDouble("990"));
//
//        System.out.println(6/3);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String a = "2019-03-19 5:50:00";
        String b = "2019-03-19 20:30:00";
        try {
            int ts = 6;
            int hours = DateUtil.getDiffHoures(sdf.parse(a),sdf.parse(b));
            System.out.println(hours);
            NumberFormat nf = NumberFormat.getNumberInstance();
            //保留整数
            nf.setMaximumFractionDigits(0);
            //如果不需要四舍五入，可以使用RoundingMode.DOWN
            nf.setRoundingMode(RoundingMode.UP);
            String result = nf.format(hours * 60 / (2+(20+2)/6) /ts * Math.sqrt(((0.32+0.34+1)/(0.34+1))));
            System.out.println(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据权重获取请求值
     */
    private static String schedules(){
        String str="http://localhost:8080/v1/schedules/619";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("numberOfBuses",45);
        jsonObject.put("minDispatchGap",2);
        jsonObject.put("maxDispatchGap",10);
        jsonObject.put("startTime","2018-12-28T06:00:00");
        jsonObject.put("endTime","2018-12-28T21:00:00");

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject_objectives = new JSONObject();
        jsonObject_objectives.put("objectiveType","WAITING_TIME");
        jsonObject_objectives.put("weight",0.3);
        jsonArray.add(jsonObject_objectives);

        jsonObject_objectives = new JSONObject();
        jsonObject_objectives.put("objectiveType","LOAD_FACTOR");
        jsonObject_objectives.put("weight",0.2);
        jsonArray.add(jsonObject_objectives);

        jsonObject_objectives = new JSONObject();
        jsonObject_objectives.put("objectiveType","OPERATION_COST");
        jsonObject_objectives.put("weight",0.5);
        jsonArray.add(jsonObject_objectives);

        jsonObject.put("objectives",jsonArray);
        return doPost(str,jsonObject.toString(),"POST");
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
}
