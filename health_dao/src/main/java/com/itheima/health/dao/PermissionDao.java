package com.itheima.health.dao;

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
}
