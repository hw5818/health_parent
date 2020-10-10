package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Permission;

/**
 * @program: health_parent
 * @description:
 * @author: hw
 * @create: 2020-10-09 14:16
 **/
public interface PermissionDao {
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
