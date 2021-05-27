package com.runnersoftware.auto_test.controller;

import com.alibaba.fastjson.JSONArray;
import com.runnersoftware.auto_test.model.User;
import com.runnersoftware.auto_test.service.UserService;
import com.runnersoftware.auto_test.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author
 * @since 2021-05-12 12:48:40
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


    @GetMapping("/findAll")
    public R findAll(@RequestParam(value = "page", defaultValue = "1", required = false) int pageNum,
                     @RequestParam(value = "limit", defaultValue = "10", required = false) int pageSize,
                     User user) {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("entity", user);
        return R.ok().data(userService.findAllByPage(params));
    }

    @PostMapping("/query/{id}")
    public R findById(@PathVariable Integer id) {
        return R.ok().data("model", userService.queryById(id));
    }


    @PostMapping("/create")
    public R insertModel(User user) {
        userService.insert(user);
        return R.ok();
    }


    @GetMapping("/remove/{id}")
    public R removeModel(@PathVariable Integer id) {
        return R.auto(userService.deleteById(id));
    }


    @PostMapping("/update")
    public R updateModel(User user) {
        userService.update(user);
        return R.ok();
    }

}
