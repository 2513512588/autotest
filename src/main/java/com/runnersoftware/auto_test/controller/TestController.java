package com.runnersoftware.auto_test.controller;

import com.runnersoftware.auto_test.model.Bugs;
import com.runnersoftware.auto_test.service.BugsService;
import com.runnersoftware.auto_test.service.ExecuteCmdService;
import com.runnersoftware.auto_test.service.TestService;
import com.runnersoftware.auto_test.utils.HttpUtil;
import com.runnersoftware.auto_test.utils.R;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private BugsService bugsService;

    @Autowired
    private ExecuteCmdService executeCmdService;

    @Value("${jmeter.path}")
    private String jmeterPath;

    @RequestMapping("/execute")
    public R executeTest(@RequestParam Map<String, String> map ) throws IOException {
        //String method = map.get("method");
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
            return R.error().message("?????????????????????" + expect + ", ?????????" + result);
        }
    }

    @RequestMapping("/unitTest")
    public R executeUnitTest(@RequestBody List<Map<String, Object>> params) {
        List<String> result = new ArrayList<>();
        for (Map<String, Object> map : params) {
            String args =  map.get("args").toString();
            args = args.replace("???", ",");
            Object[] split = args.split(",");
            try {
                testService.comile(map.get("expect"), map.get("code").toString(), split);
            } catch (Exception e) {
                e.printStackTrace();
                Bugs bugs = Bugs.buildDefault(e.getMessage(), map.get("code").toString());
                bugsService.insert(bugs);
                result.add(e.getMessage());
            }
        }
        if (result.size() > 0){
            return R.ok().message(StringUtils.join(result, "<br />"));
        }
        return R.ok().message("????????????");
    }

    @RequestMapping("/bugzilla")
    public String bugzilla() throws IOException {
        HttpGet get = HttpUtil.build().doGet("http://runnersoftware10.cn.utools.club");
        HttpResponse response = HttpUtil.build().execute(get);
        return EntityUtils.toString(response.getEntity());
    }


    @RequestMapping("/jmeter")
    public R jmeter() throws Exception {
        String s = executeCmdService.execCmd("cmd /c start jmeter.bat", new File(URLDecoder.decode(jmeterPath, "UTF-8")));
        return R.ok().message(s);
    }

}
