package com.runnersoftware.auto_test.service.Impl;

import com.runnersoftware.auto_test.model.User;
import com.runnersoftware.auto_test.mapper.UserMapper;
import com.runnersoftware.auto_test.service.UserService;
import com.runnersoftware.auto_test.utils.ManagerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;


/**
 * (User)表服务实现类
 *
 * @author
 * @since 2021-05-12 12:48:40
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        user.setCreateTime(new Date());
        if (!StringUtils.isEmpty(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        this.userMapper.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        if (!StringUtils.isEmpty(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        this.userMapper.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userMapper.deleteById(id) > 0;
    }


    /**
     * 通过主键删除数据
     *
     * @param params 分页参数
     * @return 分页结果集
     */
    @Override
    public Map<String, Object> findAllByPage(Map<String, Object> params) {
        Map<String, Object> map = new HashMap<String, Object>(3);
        Page<User> page = PageHelper.startPage(Integer.parseInt(params.get("pageNum").toString()), Integer.parseInt(params.get("pageSize").toString()));
        List<User> models = userMapper.queryAll((User) params.get("entity"));
        map.put("rows", models);
        map.put("count", page.getTotal());
        return map;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(s);
        if (!ManagerProperties.USERNAME.equals(s) && user == null){
           throw new UsernameNotFoundException("用户不存在");
        }
        if (ManagerProperties.USERNAME.equals(s)){
            return new org.springframework.security.core.userdetails.User(ManagerProperties.USERNAME, ManagerProperties.PASSWORD, Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        }

    }
}
