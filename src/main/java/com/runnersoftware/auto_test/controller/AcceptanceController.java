package com.runnersoftware.auto_test.controller;

import com.runnersoftware.auto_test.model.Acceptance;
import com.runnersoftware.auto_test.model.User;
import com.runnersoftware.auto_test.model.dto.SecurityUser;
import com.runnersoftware.auto_test.model.vo.AcceptanceVo;
import com.runnersoftware.auto_test.service.AcceptanceService;
import com.runnersoftware.auto_test.utils.R;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * (Acceptance)表控制层
 *
 * @author
 * @since 2021-05-24 11:06:33
 */
@RestController
@RequestMapping("acceptance")
public class AcceptanceController {
    /**
     * 服务对象
     */
    @Resource
    private AcceptanceService acceptanceService;

    @GetMapping("/findAll")
    public R findAll(@RequestParam(value = "page", defaultValue = "1", required = false) int pageNum,
                     @RequestParam(value = "limit", defaultValue = "10", required = false) int pageSize,
                     AcceptanceVo acceptanceVo) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("entity", acceptanceVo);
        return R.ok().data(acceptanceService.findAllByPage(params));
    }

    @PostMapping("/query/{id}")
    public R findById(@PathVariable Integer id) {
        return R.ok().data("model", acceptanceService.queryById(id));
    }

    @PostMapping("/create")
    public R insertModel(Acceptance acceptance) {
        User user = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        acceptanceService.insert(acceptance.setUserId(user.getId()));
        return R.ok();
    }

    @GetMapping("/remove/{id}")
    public R removeModel(@PathVariable Integer id) {
        return R.auto(acceptanceService.deleteById(id));
    }

    @PostMapping("/update")
    public R updateModel(Acceptance acceptance) {
        acceptanceService.update(acceptance);
        return R.ok();
    }

    @PostMapping("/reply")
    public R reply(Acceptance acceptance) {
        acceptanceService.update(acceptance.setTestTime(new Date()));
        return R.ok();
    }

}
