package com.runnersoftware.auto_test.service.Impl;

import com.runnersoftware.auto_test.model.Bugs;
import com.runnersoftware.auto_test.mapper.BugsMapper;
import com.runnersoftware.auto_test.service.BugsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



/**
 * (Bugs)表服务实现类
 *
 * @author
 * @since 2021-05-08 14:47:29
 */
@Service("bugsService")
public class BugsServiceImpl implements BugsService {
    @Resource
    private BugsMapper bugsMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param bugId 主键
     * @return 实例对象
     */
    @Override
    public Bugs queryById(Object bugId) {
        return this.bugsMapper.queryById(bugId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Bugs> queryAllByLimit(int offset, int limit) {
        return this.bugsMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param bugs 实例对象
     * @return 实例对象
     */
    @Override
    public Bugs insert(Bugs bugs) {
        this.bugsMapper.insert(bugs);
        return bugs;
    }

    /**
     * 修改数据
     *
     * @param bugs 实例对象
     * @return 实例对象
     */
    @Override
    public Bugs update(Bugs bugs) {
        this.bugsMapper.update(bugs);
        return this.queryById(bugs.getBugId());
    }

    /**
     * 通过主键删除数据
     *
     * @param bugId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object bugId) {
        return this.bugsMapper.deleteById(bugId) > 0;
    }



}
