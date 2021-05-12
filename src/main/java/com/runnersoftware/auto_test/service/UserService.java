package com.runnersoftware.auto_test.service;

import com.runnersoftware.auto_test.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

/**
 * (User)表服务接口
 *
 * @author
 * @since 2021-05-12 12:48:40
 */
public interface UserService extends UserDetailsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 分页查询所有数据
     *
     * @param params 分页信息及查询条件
     * @return 分页结果集
     */
    Map<String, Object> findAllByPage(Map<String, Object> params);

}
