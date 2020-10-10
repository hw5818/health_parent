package com.itheima.health.service;

import com.itheima.health.HealthException;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Permission;

public interface PermissionService {
    //分页查询权限信息
    PageResult<Permission> findPage(QueryPageBean queryPageBean);

    //新增权限
    void add(Permission permission);

    //删除权限
    void deleteById(int id)throws HealthException;

    //编辑权限
    void update(Permission permission);

    //根据id获取权限信息
    Permission findById(int id);
}
