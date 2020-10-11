package com.itheima.health.service.impl;/**
     * 查询权限id列表
     * @return
     */

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.HealthException;
import com.itheima.health.dao.PermissionDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Menu;
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

    //分页查询权限信息
    @Override
    public PageResult<Permission> findPage(QueryPageBean queryPageBean) {
        //Mapper接口方式的调用
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        //模糊查询、判断是否有查询条件
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        Page<Permission> page = permissionDao.findByCondition(queryPageBean.getQueryString());
        PageResult<Permission> pageResult = new PageResult<Permission>(page.getTotal(), page.getResult());
        return pageResult;
	}
	/**
     * 查询权限id列表
     * @return
     */
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    //新增权限
    @Override
    public void add(Permission permission) {
        permissionDao.add(permission);
    }

    @Override
    public void deleteById(int id)throws HealthException {
        //判断权限是否被角色使用
        int cnt = permissionDao.findCountByRoleId(id);

        if (cnt>0) {
            //被使用、不能删除.  抛出自定义异常
            throw new HealthException("权限正被角色使用、不能删除");
        }

        //执行到此、说明没被使用
        permissionDao.deleteById(id);
    }

    //编辑权限
    @Override
    public void update(Permission permission) {
        permissionDao.update(permission);
    }

    //根据id获取权限信息
    @Override
    public Permission findById(int id) {
        return permissionDao.findById(id);
    }
}
