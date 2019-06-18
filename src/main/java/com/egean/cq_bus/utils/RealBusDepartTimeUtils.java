package com.egean.cq_bus.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RealBusDepartTimeUtils {

    private static String getToken(){
        try{
            String url = "http://183.66.65.154:7010/api/Token";
            CloseableHttpResponse response = null;
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            RequestConfig config = RequestConfig.custom()
                    .setConnectionRequestTimeout(10000).setConnectTimeout(10000)
                    .setSocketTimeout(10000).build();
            post.setConfig(config);
//            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//            nvps.add(new BasicNameValuePair("UserName", "dacuser"));
//            nvps.add(new BasicNameValuePair("Password", "123456"));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName","dacuser");
            jsonObject.put("Password","123456");
            StringEntity entity_post = new StringEntity(jsonObject.toString(),"utf-8");
            entity_post.setContentType("application/json");
            post.setEntity(entity_post);
//            post.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
            response  = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            System.out.println("content:" + content);
            EntityUtils.consume(entity);

            JSONObject jsonObject1 = JSONObject.fromObject(content);
            JSONObject result = jsonObject1.getJSONObject("data");
            return result.getString("access_token");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    private static String GetDispatchPlanByLineCodeAndDate(String token,String lineCode,String date){
        try{
            String url = "http://183.66.65.154:7010/api/Vehicle/GetDispatchPlanByLineCodeAndDate?LineCode="+lineCode+"&Date="+date ;
//                    "?LineCode=线路编号&Date=调度日期";
            CloseableHttpResponse response = null;
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet post = new HttpGet(url);
            post.setHeader("Authorization", "Bearer " + token);
            RequestConfig config = RequestConfig.custom()
                    .setConnectionRequestTimeout(10000).setConnectTimeout(10000)
                    .setSocketTimeout(10000).build();
            post.setConfig(config);
            response  = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            System.out.println("content:" + content);
            EntityUtils.consume(entity);

//            JSONObject jsonObject1 = JSONObject.fromObject(content);
//            JSONObject result = jsonObject1.getJSONObject("data");
//            return result.getString("access_token");
            return content;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    private static void exportBusPlan(String busPlan,String lineCode,String date){
        try{

            JSONObject jsonObject = JSONObject.fromObject(busPlan);
            JSONObject data = jsonObject.getJSONObject("data");

            String fileName=lineCode+"_"+date;
            XSSFWorkbook workbook = new XSSFWorkbook();
            //Create a blank sheet
            XSSFSheet spreadsheet = workbook.createSheet(fileName);

            XSSFRow row;

            JSONArray jsonArray = data.getJSONArray("vehicles");
            JSONObject vv = null;
            JSONArray plans = null;

            for (int i=0;i<jsonArray.size();i++){
                row = spreadsheet.createRow(i);
                vv = jsonArray.getJSONObject(i);
                int cell_index = 0;
                Cell cell = row.createCell(cell_index++);
                cell.setCellValue(vv.getString("vehNum"));

                plans = vv.getJSONArray("plans");
                for (Object jsonobj:plans) {
                    JSONObject plan = (JSONObject) jsonobj;
                    cell = row.createCell(cell_index++);
                    cell.setCellValue(plan.getString("upDown"));
                    cell = row.createCell(cell_index++);
                    cell.setCellValue(plan.getString("time"));
                }
            }

            FileOutputStream out = new FileOutputStream(
                    new File("d://"+fileName+".xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("导出成功！");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String access_token = getToken();
        System.out.println(access_token);
        String lineNum = "619";
        String date = "2019-04-20";
        String busPlans = GetDispatchPlanByLineCodeAndDate(access_token,lineNum,date);
        exportBusPlan(busPlans,lineNum,date);
    }



//    private String sendPost(String url,String json){
//        CloseableHttpResponse response = null;
//        try {
//            CloseableHttpClient httpClient = HttpClients.createDefault();
//            HttpPost post = new HttpPost(url);
//            post.setHeader("uname", commonUtils.getSq580Uname());
//            post.setHeader("verifycode", MD5Util.MD5(commonUtils.getSq580Des()+d.getTime(), 32));
//            post.setHeader("timestamp", d.getTime()+"");
//            RequestConfig config = RequestConfig.custom()
//                    .setConnectionRequestTimeout(10000).setConnectTimeout(10000)
//                    .setSocketTimeout(10000).build();
//            post.setConfig(config);
//            StringEntity entity_post = new StringEntity(json,"utf-8");
//            post.setEntity(entity_post);
//            response  = httpClient.execute(post);
//            HttpEntity entity = response.getEntity();
//            String content = EntityUtils.toString(entity);
//            System.out.println("content:" + content);
//            EntityUtils.consume(entity);
//            return content;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                response.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
}
