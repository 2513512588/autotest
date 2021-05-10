package com.runnersoftware.auto_test.service;

import com.runnersoftware.auto_test.model.Bugs;

import java.util.List;
import java.util.Map;

/**
 * (Bugs)表服务接口
 *
 * @author
 * @since 2021-05-08 14:47:29
 */
public interface BugsService {

    /**
     * 通过ID查询单条数据
     *
     * @param bugId 主键
     * @return 实例对象
     */
    Bugs queryById(Object bugId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Bugs> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param bugs 实例对象
     * @return 实例对象
     */
    Bugs insert(Bugs bugs);

    /**
     * 修改数据
     *
     * @param bugs 实例对象
     * @return 实例对象
     */
    Bugs update(Bugs bugs);

    /**
     * 通过主键删除数据
     *
     * @param bugId 主键
     * @return 是否成功
     */
    boolean deleteById(Object bugId);

}
