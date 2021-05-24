package com.runnersoftware.auto_test.mapper;

import com.runnersoftware.auto_test.model.Acceptance;
import com.runnersoftware.auto_test.model.vo.AcceptanceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Acceptance)表数据库访问层
 *
 * @author
 * @since 2021-05-24 11:06:33
 */
@Mapper
@Repository
public interface AcceptanceMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Acceptance queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Acceptance> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param acceptance 实例对象
     * @return 对象列表
     */
    List<Acceptance> queryAll(Acceptance acceptance);

    List<AcceptanceVo> queryAllVo(AcceptanceVo acceptanceVo);

    /**
     * 新增数据
     *
     * @param acceptance 实例对象
     * @return 影响行数
     */
    int insert(Acceptance acceptance);


    /**
     * 修改数据
     *
     * @param acceptance 实例对象
     * @return 影响行数
     */
    int update(Acceptance acceptance);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

