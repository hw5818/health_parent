package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Permission;

import java.util.List;

/**
 * @Author FangJunJie
 * @Date 2020/10/9 16:59
 */
public interface PermissionDao {
    /**
     * 查询权限id列表
     * @return
     */
    List<Permission> findAll();
    //分页查询权限信息
    public Page<Permission> findByCondition(String queryString);

    //新增权限
    void add(Permission permission);

    //查看权限是否被角色使用
    int findCountByRoleId(int id);

    //删除权限
    void deleteById(int id);

    //编辑权限
    void update(Permission permission);

    //根据id获取权限信息
    Permission findById(int id);
}
