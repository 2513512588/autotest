package com.runnersoftware.auto_test.util;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class HttpUtil {

    private static final HttpClient CLIENT = HttpClients.createDefault();
    private static final HttpUtil HTTPCLIENT = new HttpUtil();

    private HttpUtil() {
    }

    public static HttpUtil build(){
        return HTTPCLIENT;
    }

    public HttpGet doGet(String url){
        HttpGet get = new HttpGet(url);
        get.setHeader("cookie", "d_c0=\"ACARxvn_uRGPTveGrziiyunR9IAozbghfcw=|1597320018\";");
        get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        return get;
    }

    public HttpPost doPost(String url){
        HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        return post;
    }

    public HttpResponse execute(HttpRequestBase http) throws IOException {
        return CLIENT.execute(http);
    }

}
