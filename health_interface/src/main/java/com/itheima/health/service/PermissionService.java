package com.itheima.health.service;

import com.itheima.health.pojo.Permission;

import java.util.List;

/**
 * @Author FangJunJie
 * @Date 2020/10/9 16:53
 */
public interface PermissionService {
    /**
     * 查询权限id列表
     * @return
     */
    List<Permission> findAll();
}
