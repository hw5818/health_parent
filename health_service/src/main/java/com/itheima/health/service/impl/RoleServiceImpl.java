package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.HealthException;
import com.itheima.health.dao.RoleDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Role;
import com.itheima.health.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author FangJunJie
 * @Date 2020/10/9 11:26
 */
@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    /**
     * 查询角色id列表
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /**
     * 分页条件查询角色
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Role> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 有查询条件，拼接% 模糊查询
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        Page<Role> page = roleDao.findByCondition(queryPageBean.getQueryString());

        return new PageResult<Role>(page.getTotal(),page.getResult());
    }

    /**
     * 添加角色
     * @param role
     * @param permissionIds
     * @param menuIds
     * @return
     */
    @Override
    public Integer add(Role role, Integer[] permissionIds, Integer[] menuIds) {
        // 先添加角色
        roleDao.add(role);
        // 获取新增的角色的id
        Integer roleId = role.getId();
        // 遍历选中的权限id,
        if(null != permissionIds){
            for (Integer permissionId : permissionIds) {
                //添加角色与权限的关系
                roleDao.addRolePermission(roleId,permissionId);
            }
        }
        // 遍历选中的菜单id,
        if(null != menuIds){
            for (Integer menuId : menuIds) {
                //添加角色与菜单的关系
                roleDao.addRoleMenu(roleId,menuId);
            }
        }
        return roleId;
    }

    /**
     * 通过id查询角色信息
     * @param id
     * @return
     */
    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    /**
     * 查询选中的权限id集合
     * @param id
     * @return
     */
    @Override
    public List<Integer> findPermissionIdsByRoleId(int id) {
        return roleDao.findPermissionIdsByRoleId(id);
    }

    /**
     * 查询选中的菜单id集合
     * @param id
     * @return
     */
    @Override
    public List<Integer> findMenuIdsByRoleId(int id) {
        return roleDao.findMenuIdsByRoleId(id);
    }

    /**
     * 更新角色信息
     * @param role
     * @param permissionIds
     * @param menuIds
     */
    @Override
    public void update(Role role, Integer[] permissionIds, Integer[] menuIds) {
        // 先更新角色
        roleDao.update(role);
        // 删除角色与权限的关系
        roleDao.deleteRolePermission(role.getId());
        // 删除角色与菜单的关系
        roleDao.deleteRoleMenu(role.getId());
        // 添加新关系
        if(null != permissionIds){
            for (Integer roleId : permissionIds) {
                roleDao.addRolePermission(role.getId(), roleId);
            }
        }
        // 添加新关系
        if(null != menuIds){
            for (Integer roleId : menuIds) {
                roleDao.addRoleMenu(role.getId(), roleId);
            }
        }
    }

    /**
     * 通过id删除角色
     * @param id
     */
    @Override
    public void deleteById(int id) {
        // 判断 是否被用户拥有
        int count = roleDao.findCountByRoleId(id);
        // 使用了则报错
        if(count > 0) {
            throw new HealthException("该角色已经被用户使用，不能删除!");
        }
        // 没使用
        // 删除角色与权限的关系
        roleDao.deleteRolePermission(id);
        // 删除角色与菜单的关系
        roleDao.deleteRoleMenu(id);
        // 再删除用户
        roleDao.deleteById(id);
    }
}
