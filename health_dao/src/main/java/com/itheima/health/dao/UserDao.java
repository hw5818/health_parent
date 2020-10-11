package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: No Description
 * User: Eric
 */
public interface UserDao {

    /**
     * 获取用户的角色权限信息
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 分页条件查询用户
     * @param queryString
     * @return
     */
    Page<User> findByCondition(String queryString);

    /**
     * 添加用户
     * @param user
     */
    void add(User user);

    /**
     * 添加用户与角色的关系
     * @param userId
     * @param roleId
     */
    void addUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

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
     */
    void update(User user);

    /**
     * 删除旧关系
     * @param id
     */
    void deleteUserRole(Integer id);

    /**
     * 通过id删除用户
     * @param id
     */
    void deleteById(int id);
}
