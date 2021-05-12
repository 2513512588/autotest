package com.runnersoftware.auto_test.controller;

import com.runnersoftware.auto_test.utils.HttpUtil;
import com.runnersoftware.auto_test.utils.R;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/execute")
    public R executeTest(@RequestParam Map<String, String> map ) throws IOException {
        String method = map.get("method");
        String url = map.get("url");
        String params = map.get("params");
        String expect = map.get("expect");
        HttpPost httpPost = HttpUtil.build().doPost(url);
        httpPost.setEntity(new StringEntity(params));
        HttpResponse response = HttpUtil.build().execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());
        if (result.equals(expect)){
            return R.ok();
        }else {
            return R.error().message("你期望的数据是" + expect + ", 实际是" + result);
        }
    }


}
