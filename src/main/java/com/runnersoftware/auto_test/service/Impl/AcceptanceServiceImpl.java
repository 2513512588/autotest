package com.runnersoftware.auto_test.service.Impl;

import com.runnersoftware.auto_test.model.Acceptance;
import com.runnersoftware.auto_test.mapper.AcceptanceMapper;
import com.runnersoftware.auto_test.model.vo.AcceptanceVo;
import com.runnersoftware.auto_test.service.AcceptanceService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


/**
 * (Acceptance)表服务实现类
 *
 * @author
 * @since 2021-05-24 11:06:33
 */
@Service("acceptanceService")
public class AcceptanceServiceImpl implements AcceptanceService {
    @Resource
    private AcceptanceMapper acceptanceMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Acceptance queryById(Integer id) {
        return this.acceptanceMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Acceptance> queryAllByLimit(int offset, int limit) {
        return this.acceptanceMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param acceptance 实例对象
     * @return 实例对象
     */
    @Override
    public Acceptance insert(Acceptance acceptance) {
        this.acceptanceMapper.insert(acceptance.setCreateTime(new Date()));
        return acceptance;
    }

    /**
     * 修改数据
     *
     * @param acceptance 实例对象
     * @return 实例对象
     */
    @Override
    public Acceptance update(Acceptance acceptance) {
        this.acceptanceMapper.update(acceptance);
        return this.queryById(acceptance.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.acceptanceMapper.deleteById(id) > 0;
    }


    /**
     * 通过主键删除数据
     *
     * @param params 分页参数
     * @return 分页结果集
     */
    @Override
    public Map<String, Object> findAllByPage(Map<String, Object> params) {
        Map<String, Object> map = new HashMap<>(3);
        Page<AcceptanceVo> page = PageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<AcceptanceVo> models = acceptanceMapper.queryAllVo((AcceptanceVo) params.get("entity"));
        map.put("rows", models);
        map.put("count", page.getTotal());
        return map;
    }
}
