package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.PermissionDao;
import com.itheima.health.pojo.Permission;
import com.itheima.health.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author FangJunJie
 * @Date 2020/10/9 16:58
 */
@Service(interfaceClass = PermissionService.class)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 查询权限id列表
     * @return
     */
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }
}
