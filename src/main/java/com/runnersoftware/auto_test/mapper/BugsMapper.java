package com.runnersoftware.auto_test.mapper;

import com.runnersoftware.auto_test.model.Bugs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Bugs)表数据库访问层
 *
 * @author
 * @since 2021-05-08 14:47:29
 */
@Mapper
@Repository
public interface BugsMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param bugId 主键
     * @return 实例对象
     */
    Bugs queryById(Object bugId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Bugs> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param bugs 实例对象
     * @return 对象列表
     */
    List<Bugs> queryAll(Bugs bugs);

    /**
     * 新增数据
     *
     * @param bugs 实例对象
     * @return 影响行数
     */
    int insert(Bugs bugs);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Bugs> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Bugs> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Bugs> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Bugs> entities);

    /**
     * 修改数据
     *
     * @param bugs 实例对象
     * @return 影响行数
     */
    int update(Bugs bugs);

    /**
     * 通过主键删除数据
     *
     * @param bugId 主键
     * @return 影响行数
     */
    int deleteById(Object bugId);

}

