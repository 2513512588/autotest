package com.runnersoftware.auto_test.controller;

import com.runnersoftware.auto_test.service.BugsService;
import com.runnersoftware.auto_test.utils.R;
import org.springframework.web.bind.annotation.*;
import com.runnersoftware.auto_test.model.Bugs;




import javax.annotation.Resource;

/**
 * (Bugs)表控制层
 *
 * @author
 * @since 2021-05-08 14:47:30
 */
@RestController
@RequestMapping("bugs")
public class BugsController {
    /**
     * 服务对象
     */
    @Resource
    private BugsService bugsService;




    @PostMapping("/query/{id}")
    public R findById(@PathVariable String id) {
        return R.ok().data("model", bugsService.queryById(id));
    }


    @PostMapping("/create")
    public R insertModel(Bugs bugs) {
        bugsService.insert(bugs);
        return R.ok();
    }


    @GetMapping("/remove/{id}")
    public R removeModel(@PathVariable String id) {
        return R.auto(bugsService.deleteById(id));
    }


    @PostMapping("/update")
    public R updateModel(Bugs bugs) {
        bugsService.update(bugs);
        return R.ok();
    }

}
