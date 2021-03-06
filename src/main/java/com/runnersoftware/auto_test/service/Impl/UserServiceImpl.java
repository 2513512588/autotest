package com.runnersoftware.auto_test.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.runnersoftware.auto_test.mapper.UserMapper;
import com.runnersoftware.auto_test.model.User;
import com.runnersoftware.auto_test.model.dto.SecurityUser;
import com.runnersoftware.auto_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        this.userMapper.insert(user.setCreateTime(new Date()));
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
        if (!StringUtils.isEmpty(user.getPassword())) {
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
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Collection<GrantedAuthority> collection = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(user.getRole());
        for (Object o : jsonArray) {
            if (o.equals(1)) {
                collection.add(new SimpleGrantedAuthority("ROLE_UNIT"));
            } else if (o.equals(2)) {
                collection.add(new SimpleGrantedAuthority("ROLE_INTEGRATE"));
            } else if (o.equals(3)) {
                collection.add(new SimpleGrantedAuthority("ROLE_PERFORMANCE"));
            } else if (o.equals(4)) {
                collection.add(new SimpleGrantedAuthority("ROLE_ACCEPTANCE"));
            } else if (o.equals(5)) {
                collection.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
        }
        return new SecurityUser(user, collection);
    }
}
