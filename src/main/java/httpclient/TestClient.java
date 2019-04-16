package httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class TestClient {


    public static String doGet(String url, Map<String, String> parms, Map<String, String> headers){

        //创建HttpClient对象
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //创建请求响应接收对象HttpResponse
        CloseableHttpResponse closeableHttpResponse = null;


        try {
            //创建请求的uri
            URIBuilder uriBuilder = new URIBuilder(url);
            //添加请求参数
            if(parms!=null){
                for(String key:parms.keySet()){
                    uriBuilder.addParameter(key,parms.get(key));
                }
            }

            URI uri = uriBuilder.build();

            //创建Http Get 请求
            HttpGet httpGet = new HttpGet(uri);

            //添加请求头
            for(String key:headers.keySet()){
                httpGet.addHeader(key,headers.get(key));
            }

            //执行请求
            closeableHttpResponse = closeableHttpClient.execute(httpGet);

            //请求成功返回内容
            String resaultString = "";

            //判断是否请求成功
            if(closeableHttpResponse.getStatusLine().getStatusCode()==200){
                resaultString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
            }

            //打印返回结果
            System.out.println(resaultString);

            return resaultString;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }


    }


    public static void main(String[] args) {
        Map<String ,String > parms = new HashMap<String, String>();
        Map<String,String> headers = new HashMap<String, String>();
//        map.put("1","tom1");
//        map.put("2","tom2");
//        map.put("3","tom3");
//        map.put("4","tom4");
//        map.put("5","tom5");

//        for(String num:map.keySet()){
//            System.out.print(num+":");
//            System.out.println(map.get(num));
//
//        }
        System.out.println(doGet("https://www.baidu.com",parms,headers));







    }


}
