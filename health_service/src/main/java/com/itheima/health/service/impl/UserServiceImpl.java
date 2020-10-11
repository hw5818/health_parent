package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.HealthException;
import com.itheima.health.dao.UserDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Description: No Description
 * User:
 */
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 分页条件查询用户
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<User> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 有查询条件，拼接% 模糊查询
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        Page<User> page = userDao.findByCondition(queryPageBean.getQueryString());

        return new PageResult<User>(page.getTotal(),page.getResult());
    }

    /**
     * 获取用户的角色权限信息
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 添加用户
     * @param user
     * @param roleIds
     * @return
     */
    @Override
    @Transactional
    public Integer add(User user, Integer[] roleIds) {
        // 先添加用户
        userDao.add(user);
        // 获取新增的用户的id
        Integer userId = user.getId();
        // 遍历选中的角色id,
        if(null != roleIds){
            for (Integer roleId : roleIds) {
                //添加用户与角色的关系
                userDao.addUserRole(userId,roleId);
            }
        }
        return userId;
    }

    /**
     * 通过id查询用户信息
     * @param id
     * @return
     */
    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    /**
     * 查询选中的角色id集合
     * @param id
     * @return
     */
    @Override
    public List<Integer> findRoleIdsByUserId(int id) {
        return userDao.findRoleIdsByUserId(id);
    }

    /**
     * 更新用户
     * @param user
     * @param roleIds
     */
    @Override
    @Transactional
    public void update(User user, Integer[] roleIds) {
        // 先更新用户
        userDao.update(user);
        // 删除旧关系
        userDao.deleteUserRole(user.getId());
        // 添加新关系
        if(null != roleIds){
            for (Integer roleId : roleIds) {
                userDao.addUserRole(user.getId(), roleId);
            }
        }
    }

    /**
     * 通过id删除用户
     * @param id
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        // 先删除该用户与角色的关系
        userDao.deleteUserRole(id);
        // 再删除用户
        userDao.deleteById(id);
    }
}
