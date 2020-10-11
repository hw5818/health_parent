package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.User;

import java.util.List;

/**
 * Description: No Description
 * User: Eric
 */
public interface UserService {
    /**
     * 分页条件查询用户
     * @param queryPageBean
     * @return
     */
    PageResult<User> findPage(QueryPageBean queryPageBean);

    /**
     * 获取用户的角色权限信息
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 添加用户
     * @param user
     * @param roleIds
     * @return
     */
    Integer add(User user, Integer[] roleIds);

    /**
     * 通过id查询用户信息
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * 查询选中的角色id集合
     * @param id
     * @return
     */
    List<Integer> findRoleIdsByUserId(int id);

    /**
     * 更新用户
     * @param user
     * @param roleIds
     */
    void update(User user, Integer[] roleIds);

    /**
     * 通过id删除用户
     * @param id
     */
    void deleteById(int id);
}
